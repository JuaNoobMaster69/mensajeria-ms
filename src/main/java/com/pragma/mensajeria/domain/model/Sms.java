/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pragma.mensajeria.domain.model;

/**
 *
 * @author Juan Camilo
 */
public class Sms {
    private String celular;
    private String pin;

    public Sms() {
    }

    public Sms(String celular, String pin) {
        this.celular = celular;
        this.pin = pin;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}