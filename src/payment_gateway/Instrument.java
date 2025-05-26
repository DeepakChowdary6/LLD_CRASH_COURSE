package payment_gateway;

public class Instrument {
    int instrumentID;
    InstrumentType instrumentType;
    int userID;

    public int getInstrumentID() { return instrumentID; }
    public void setInstrumentID(int instrumentID) { this.instrumentID = instrumentID; }

    public InstrumentType getInstrumentType() { return instrumentType; }
    public void setInstrumentType(InstrumentType instrumentType) { this.instrumentType = instrumentType; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
}
