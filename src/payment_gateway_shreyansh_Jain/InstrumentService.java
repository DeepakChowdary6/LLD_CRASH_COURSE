package payment_gateway_shreyansh_Jain;
import java.util.*;
public abstract class InstrumentService {
    static Map<Integer, List<Instrument>> userVsInstruments = new HashMap<>();

    public abstract InstrumentDO addInstrument(InstrumentDO instrumentDO);

    public abstract List<InstrumentDO> getInstrumentsByUserID(int userID);
}