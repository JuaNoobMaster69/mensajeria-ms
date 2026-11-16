/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pragma.mensajeria.infrastructure.config;

import com.pragma.mensajeria.application.usecase.SmsUseCase;
import com.pragma.mensajeria.domain.api.ISmsServicePort;
import com.pragma.mensajeria.domain.spi.ISmsPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *
 * @author Juan Camilo
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public ISmsServicePort smsServicePort(ISmsPersistencePort smsPersistencePort) {
        return new SmsUseCase(smsPersistencePort);
    }
}