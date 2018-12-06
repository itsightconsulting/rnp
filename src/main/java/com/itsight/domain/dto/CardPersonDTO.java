package com.itsight.domain.dto;

import java.io.Serializable;

// PersonaTarjeta
public class CardPersonDTO implements Serializable {

    // Fecha de vencimiento
    private String expirationDate;

    // Codigo de seguridad
    private String cvv;

    private String description;

    private boolean flagFavorite;

    private String cardType;

    private String digits;

    private String personName;

    // Número de documento de la persona
    private String personDocumentNumber;

    // Nombre del tipo de documento
    private String documentTypeName;

    public CardPersonDTO() {
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() { return cvv; }

    public void setCvv(String cvv) { this.cvv = cvv; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFlagFavorite() {
        return flagFavorite;
    }

    public void setFlagFavorite(boolean flagFavorite) {
        this.flagFavorite = flagFavorite;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonDocumentNumber() {
        return personDocumentNumber;
    }

    public void setPersonDocumentNumber(String personDocumentNumber) {
        this.personDocumentNumber = personDocumentNumber;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }
}
