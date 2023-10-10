package mye.fisio.mak.apimak.security;

import org.springframework.stereotype.Component;

import mye.fisio.mak.apimak.domain.Client;

@Component
public class Validations {

    public boolean registerValidation(Client client) {
        return client != null && validateCedula(client.getCedula()) && validateEmail(client.getEmail())
                && validatePhone(client.getPhone()) && validatePassword(client.getPassword())
                && validateName(client.getName()) && validateLast_name(client.getLast_name())
                && validateEnterprise_name(client.getEnterprise_name());
    }

    public boolean validateCedula(String cedula) {
        return cedula != null && !cedula.isBlank() && cedula.length() == 9 && cedula.matches("[0-9]*");
    }

    public boolean validateEmail(String email) {
        return email != null && !email.isBlank() && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                && email.length() <= 100 && email.length() >= 5;
    }

    public boolean validatePhone(String phone) {
        return phone != null && !phone.isBlank() && phone.matches("[0-9]*") && phone.length() == 8;
    }

    public boolean validatePassword(String password) {
        return password != null && !password.isBlank() && password.length() >= 6 && password.length() <= 20;
    }

    public boolean validateName(String name) {
        return name != null && !name.isBlank() && name.length() >= 3 && name.length() <= 50;
    }

    public boolean validateLast_name(String last_name) {
        return last_name != null && !last_name.isBlank() && last_name.length() >= 3 && last_name.length() <= 50;
    }

    public boolean validateEnterprise_name(String enterprise_name) {
        return enterprise_name != null && !enterprise_name.isBlank() && enterprise_name.length() >= 3
                && enterprise_name.length() <= 100;
    }

}
