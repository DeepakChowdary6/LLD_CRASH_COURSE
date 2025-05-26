package payment_gateway_shreyansh_Jain;

public class CardInstrument extends Instrument {
    String cardNumber;
    String cvvNumber;

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCvvNumber() { return cvvNumber; }
    public void setCvvNumber(String cvvNumber) { this.cvvNumber = cvvNumber; }
}