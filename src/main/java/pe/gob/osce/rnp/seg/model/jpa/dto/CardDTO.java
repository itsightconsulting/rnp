package pe.gob.osce.rnp.seg.model.jpa.dto;

public class CardDTO {

    private String personDni;

    // Fecha de vencimiento
    private String expirationDate;

    // Codigo de seguridad
    private String cvv;

    private String description;

    private String cardType;

    private String digits;

    public CardDTO() {
    }

    public String getPersonDni() {
        return personDni;
    }

    public void setPersonDni(String personDni) {
        this.personDni = personDni;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String ccv) {
        this.cvv = ccv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
