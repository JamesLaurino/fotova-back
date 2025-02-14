package com.fotova.config;

import com.drools.service.BusinessProductDroolsService;
import com.fotova.repository.client.ClientRepositoryImpl;
import com.fotova.repository.product.ProductRepositoryImpl;
import com.fotova.repository.role.RoleRepositoryImpl;
import com.fotova.service.DeadLetterQueueConsumer;
import com.fotova.service.RabbitMQConsumer;
import com.fotova.service.RabbitMQProducer;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {
    @Bean
    public ProductRepositoryImpl productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean
    public KieContainer kieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }

    @Bean
    public ClientRepositoryImpl clientRepositoryImpl() {
        return new ClientRepositoryImpl();
    }

    @Bean
    public RoleRepositoryImpl roleRepository() {
        return new RoleRepositoryImpl();
    }

    @Bean
    public BusinessProductDroolsService businessProductDroolsService() {
        return new BusinessProductDroolsService();
    }
}
