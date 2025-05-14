package com.serasa.basea.securanca;

import jakarta.persistence.AttributeConverter;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

public class CriptografiaGeral implements AttributeConverter<String, String> {
    private final StringEncryptor encryptor;

    @Autowired
    public CriptografiaGeral(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptor.decrypt(dbData);
    }
}