package payment_gateway;
import java.util.*;
public class BankService extends InstrumentService {
    @Override
    public InstrumentDO addInstrument(InstrumentDO instrumentDO) {
        BankInstrument bankInstrument = new BankInstrument();
        bankInstrument.setInstrumentID(new Random().nextInt(9000) + 1000);
        bankInstrument.setBankAccountNumber(instrumentDO.getBankAccountNumber());
        bankInstrument.setIfscCode(instrumentDO.getIfscCode());
        bankInstrument.setInstrumentType(InstrumentType.BANK);
        bankInstrument.setUserID(instrumentDO.getUserID());

        List<Instrument> instruments = userVsInstruments.getOrDefault(bankInstrument.getUserID(), new ArrayList<>());
        instruments.add(bankInstrument);
        userVsInstruments.put(bankInstrument.getUserID(), instruments);

        return mapBankInstrumentToInstrumentDO(bankInstrument);
    }

    @Override
    public List<InstrumentDO> getInstrumentsByUserID(int userID) {
        List<InstrumentDO> result = new ArrayList<>();

        List<Instrument> instruments = userVsInstruments.getOrDefault(userID, new ArrayList<>());
        for (Instrument instrument : instruments) {
            if (instrument.getInstrumentType() == InstrumentType.BANK) {
                result.add(mapBankInstrumentToInstrumentDO((BankInstrument) instrument));
            }
        }
        return result;
    }

    private InstrumentDO mapBankInstrumentToInstrumentDO(BankInstrument bankInstrument) {
        InstrumentDO instrumentDO = new InstrumentDO();
        instrumentDO.setInstrumentID(bankInstrument.getInstrumentID());
        instrumentDO.setBankAccountNumber(bankInstrument.getBankAccountNumber());
        instrumentDO.setIfscCode(bankInstrument.getIfscCode());
        instrumentDO.setInstrumentType(bankInstrument.getInstrumentType());
        instrumentDO.setUserID(bankInstrument.getUserID());
        return instrumentDO;
    }
}