package payment_gateway_Own.service;

import payment_gateway_Own.dto.*;
import payment_gateway_Own.repo.Repository;

public class InstrumentService {
    public void addInstrument(String userId, String instrumentId, String type) {
        Instrument instrument = new Instrument(instrumentId, type);
        Repository.instrumentMap.put(instrumentId, instrument);
        User user = Repository.userMap.get(userId);
        if (user != null) {
            user.addInstrument(instrument);
        }
    }
}
