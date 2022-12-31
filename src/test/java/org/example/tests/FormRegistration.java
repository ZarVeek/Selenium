package org.example.tests;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FormRegistration {
    private String name;
    private String phone;
    private String mail;
    private String address;

    public FormRegistration generateData(){
        this.name = "Артем Киреев";
        this.phone = "9999999999";
        this.mail = "qwertyqwerty";
        this.address = "AinB";
        return this;
    }
    public List<String> getListValue(){
        return Arrays.asList(name, formatPhone(), mail, address);
    }

    private String formatPhone() {
        return "+7 (" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6);
    }
}

