package com.sdacademy.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }
    // jdbc:h2:mem:testdb
    // user: sa    bez hasła
    // w przeglądarce:
    // http://localhost:8080/messages/temat1/wiadomosc1_wiadomosc2_wiadomosc3
    // wyśle wiadomość; podkreślniki oddzielają kolejne wiadomości
    // http://localhost:8080/messages/remove/temat3/
    // usunie wszystkie wiadomości z tematu3

}

