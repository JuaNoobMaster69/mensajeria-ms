package com.pragma.mensajeria.infrastructure.output.rest;

import com.pragma.mensajeria.domain.model.Sms;
import com.pragma.mensajeria.domain.spi.ISmsPersistencePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

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
            String url = "https://api.twilio.com/2010-04-01/Accounts/" + accountSid + "/Messages.json";

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("To", sms.getCelular());
            body.add("From", fromNumber);
            // Usamos la plantilla exacta que Twilio permite en cuentas Trial
            body.add("Body", "sms_2fa");

            RestClient.create()
                    .post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .headers(headers -> headers.setBasicAuth(accountSid, authToken))
                    .body(body)
                    .retrieve()
                    .toBodilessEntity();

            System.out.println(">>> SMS ENVIADO EXITOSAMENTE A " + sms.getCelular());

        } catch (Exception e) {
            System.out.println(">>> ERROR DE TWILIO: " + e.getMessage());
            System.out.println(">>> SIMULANDO ENVÍO: PIN " + sms.getPin() + " para " + sms.getCelular());
        }
    }
}