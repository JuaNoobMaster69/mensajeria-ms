/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pragma.mensajeria.domain.api;

import com.pragma.mensajeria.domain.model.Sms;

/**
 *
 * @author Juan Camilo
 */
public interface ISmsServicePort {
    void enviarSms(Sms sms);
}