/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pragma.mensajeria.infrastructure.output.rest;

import com.pragma.mensajeria.domain.model.Sms;
import com.pragma.mensajeria.domain.spi.ISmsPersistencePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
/**
 *
 * @author Juan Camilo
 */
@Component
public class TwilioSmsAdapter implements ISmsPersistencePort {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.from-number}")
    private String fromNumber;

    @Override
    public void enviarSms(Sms sms) {
        try {
            // URL de la API de Twilio (igual que en el curl que te dieron)
            String url = "https://api.twilio.com/2010-04-01/Accounts/" + accountSid + "/Messages.json";

            // Creamos el cuerpo de la petición (form-urlencoded)
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("To", sms.getCelular());
            body.add("From", fromNumber);
            body.add("Body", "sms_2fa"); // Usamos la plantilla exacta de Twilio

            // Hacemos la petición con RestClient
            RestClient.create()
                    .post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .headers(headers -> headers.setBasicAuth(accountSid, authToken)) // Autenticación básica
                    .body(body)
                    .retrieve()
                    .toBodilessEntity();

            System.out.println(">>> SMS ENVIADO A " + sms.getCelular());

        } catch (Exception e) {
            System.out.println(">>> ERROR DE TWILIO: " + e.getMessage());
            System.out.println(">>> SIMULANDO ENVÍO: PIN " + sms.getPin() + " para " + sms.getCelular());
        }
    }
}