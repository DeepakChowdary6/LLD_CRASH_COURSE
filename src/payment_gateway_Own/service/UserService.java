package payment_gateway_Own.service;

import payment_gateway_Own.dto.*;
import payment_gateway_Own.repo.Repository;

public class UserService {
    public void createUser(String userId, String name) {
        if (!Repository.userMap.containsKey(userId)) {
            Repository.userMap.put(userId, new User(userId, name));
        }
    }

    public User getUser(String userId) {
        return Repository.userMap.get(userId);
    }
}