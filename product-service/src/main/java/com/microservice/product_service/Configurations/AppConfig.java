package com.microservice.product_service.Configurations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@Configuration
public class AppConfig {
    @Bean
    public HibernateTransactionManager transactionManager(EntityManagerFactory emf) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(emf.unwrap(SessionFactory.class));
        return transactionManager;
    }

}
