/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pragma.mensajeria.infrastructure.input.rest;

import com.pragma.mensajeria.domain.api.ISmsServicePort;
import com.pragma.mensajeria.domain.model.Sms;
import com.pragma.mensajeria.infrastructure.input.rest.dto.SmsRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Juan Camilo
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    private final ISmsServicePort smsServicePort;

    public SmsController(ISmsServicePort smsServicePort) {
        this.smsServicePort = smsServicePort;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarSms(@RequestBody SmsRequestDto request) {
        Sms sms = new Sms(request.celular(), request.pin());
        smsServicePort.enviarSms(sms);
        return ResponseEntity.ok("SMS procesado");
    }
}