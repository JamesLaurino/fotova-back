package com.fotova.service.ai;


import com.fotova.constat.PromptConstant;
import com.fotova.dto.ai.AiProductInputDto;
import com.fotova.dto.ai.AiProductOutputDto;
import com.fotova.entity.LabelEntity;
import com.fotova.entity.ProductEntity;
import com.fotova.repository.label.LabelRepositoryImpl;
import com.fotova.repository.product.ProductRepositoryImpl;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class AiServiceDev implements AiService {
    @Autowired
    private ProductRepositoryImpl productRepository;

    @Autowired
    private LabelRepositoryImpl labelRepository;

    @Autowired
    private AiMapper aiMapper;

    private ChatClient chatClient;

    public AiServiceDev(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    public String translateTitleAndDescription(ProductEntity product) {
        ProductEntity productEntity = productRepository.save(product);
        AiProductInputDto aiProductInputDto = aiMapper.mapToAiProductInput(product);

        String queryUser = """
        You must return ONLY valid JSON.
        
        All fields are mandatory.
        No field can be empty.
        No field can be null.
        
        Required JSON structure:
        {
          "title_english": "",
          "title_russian": "",
          "title_french": "",
          "description_english": "",
          "description_russian": "",
          "description_french": ""
        }
        
        Instructions:
        - Translate title and description into English, Russian and French.
        - Always provide a value for every field.
        - If the text is already in that language, copy it.
        - Keep meaning and tone.
        - No explanations.
        - No extra fields.
        
        Title: %s
        Description: %s
        """.formatted(
                aiProductInputDto.getTitle(),
                aiProductInputDto.getDescription()
        );
        AiProductOutputDto aiProductOutputDto = chatClient.prompt()
                .system(PromptConstant.TRANSLATE_LABEL_SYSTEM)
                .user(queryUser)
                .call()
                .entity(AiProductOutputDto.class);

        if(aiProductOutputDto == null) {
            AiProductOutputDto aiProductOutputDtoNull = new AiProductOutputDto();
            LabelEntity labelEntity = aiMapper.mapToLabelEntity(aiProductOutputDtoNull);
            labelEntity.setProduct(productEntity);
            labelRepository.save(labelEntity);
            return "Labels translated and added successfully";
        } else {
            LabelEntity labelEntity = aiMapper.mapToLabelEntity(aiProductOutputDto);
            labelEntity.setProduct(productEntity);
            labelRepository.save(labelEntity);
            return "Labels translated and added successfully";
        }

    }
}
