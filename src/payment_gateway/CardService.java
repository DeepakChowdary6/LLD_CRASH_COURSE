package payment_gateway;

import java.util.*;

public class CardService extends InstrumentService {
    @Override
    public InstrumentDO addInstrument(InstrumentDO instrumentDO) {
        CardInstrument cardInstrument = new CardInstrument();

        cardInstrument.setInstrumentID(new Random().nextInt(9000) + 1000);
        cardInstrument.setCardNumber(instrumentDO.getCardNumber());
        cardInstrument.setCvvNumber(instrumentDO.getCvvNumber());
        cardInstrument.setInstrumentType(InstrumentType.CARD);
        cardInstrument.setUserID(instrumentDO.getUserID());

        List<Instrument> instruments = userVsInstruments.getOrDefault(cardInstrument.getUserID(), new ArrayList<>());
        instruments.add(cardInstrument);
        userVsInstruments.put(cardInstrument.getUserID(), instruments);

        return mapCardInstrumentToInstrumentDO(cardInstrument);
    }

    @Override
    public List<InstrumentDO> getInstrumentsByUserID(int userID) {
        List<InstrumentDO> result = new ArrayList<>();
        List<Instrument> instruments = userVsInstruments.getOrDefault(userID, new ArrayList<>());
        for (Instrument instrument : instruments) {
            if (instrument.getInstrumentType() == InstrumentType.CARD) {
                result.add(mapCardInstrumentToInstrumentDO((CardInstrument) instrument));
            }
        }
        return result;
    }

    private InstrumentDO mapCardInstrumentToInstrumentDO(CardInstrument cardInstrument) {
        InstrumentDO instrumentDO = new InstrumentDO();
        instrumentDO.setInstrumentID(cardInstrument.getInstrumentID());
        instrumentDO.setCardNumber(cardInstrument.getCardNumber());
        instrumentDO.setCvvNumber(cardInstrument.getCvvNumber());
        instrumentDO.setInstrumentType(cardInstrument.getInstrumentType());
        instrumentDO.setUserID(cardInstrument.getUserID());
        return instrumentDO;
    }
}
