package com.fotova.config;

import com.drools.service.BusinessProductDroolsService;
import com.fotova.repository.role.RoleRepositoryImpl;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class ConfigBean {

    @Bean
    public KieContainer kieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }

    @Bean
    public RoleRepositoryImpl roleRepositoryImpl() {
        return new RoleRepositoryImpl();
    }

    @Bean
    public BusinessProductDroolsService businessProductDroolsService() {
        return new BusinessProductDroolsService();
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
