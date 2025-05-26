package payment_gateway_shreyansh_Jain;

public class BankInstrument extends Instrument {
    String bankAccountNumber;
    String ifscCode;

    public String getBankAccountNumber() { return bankAccountNumber; }
    public void setBankAccountNumber(String bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }
}
