package payment_gateway;

public class InstrumentDO {
    int instrumentID;
    InstrumentType instrumentType;
    int userID;
    String bankAccountNumber;
    String ifscCode;
    String cardNumber;
    String cvvNumber;

    // Getters and setters for all fields...
    public int getInstrumentID() { return instrumentID; }
    public void setInstrumentID(int instrumentID) { this.instrumentID = instrumentID; }

    public InstrumentType getInstrumentType() { return instrumentType; }
    public void setInstrumentType(InstrumentType instrumentType) { this.instrumentType = instrumentType; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getBankAccountNumber() { return bankAccountNumber; }
    public void setBankAccountNumber(String bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCvvNumber() { return cvvNumber; }
    public void setCvvNumber(String cvvNumber) { this.cvvNumber = cvvNumber; }
}