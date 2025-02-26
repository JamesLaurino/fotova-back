package com.fotova.config;

import com.drools.service.BusinessProductDroolsService;
import com.fotova.repository.role.RoleRepositoryImpl;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
