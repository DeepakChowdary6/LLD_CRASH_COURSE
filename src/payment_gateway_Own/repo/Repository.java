package payment_gateway_Own.repo;

import payment_gateway_Own.dto.*;
import payment_gateway_Own.repo.Repository;
import java.util.*;

public class Repository {
    public static final Map<String, User> userMap = new HashMap<>();
    public static final Map<String, Transaction> transactionMap = new HashMap<>();
    public static final Map<String, Instrument> instrumentMap = new HashMap<>();
}