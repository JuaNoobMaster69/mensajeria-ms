/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pragma.mensajeria.application.usecase;

import com.pragma.mensajeria.domain.api.ISmsServicePort;
import com.pragma.mensajeria.domain.model.Sms;
import com.pragma.mensajeria.domain.spi.ISmsPersistencePort;
/**
 *
 * @author Juan Camilo
 */
public class SmsUseCase implements ISmsServicePort {

    private final ISmsPersistencePort smsPersistencePort;

    public SmsUseCase(ISmsPersistencePort smsPersistencePort) {
        this.smsPersistencePort = smsPersistencePort;
    }

    @Override
    public void enviarSms(Sms sms) {
        if (sms.getCelular() == null || sms.getCelular().isBlank()) {
            throw new RuntimeException("El celular es obligatorio");
        }
        if (sms.getPin() == null || sms.getPin().isBlank()) {
            throw new RuntimeException("El PIN es obligatorio");
        }
        smsPersistencePort.enviarSms(sms);
    }
}