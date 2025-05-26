package payment_gateway_shreyansh_Jain;
import java.util.*;
public class UserService {
    static List<User> usersList = new ArrayList<>();

    public UserDO addUser(UserDO userDO) {
        User userObj = new User();
        userObj.setUserName(userDO.getName());
        userObj.setEmail(userDO.getMail());
        userObj.setUserID(new Random().nextInt(90) + 10); // random between 10 and 99
        usersList.add(userObj);
        return convertUserDOToUser(userObj);
    }

    public UserDO getUser(int userID) {
        for (User user : usersList) {
            if (user.getUserID() == userID) {
                return convertUserDOToUser(user);
            }
        }
        return null;
    }

    private UserDO convertUserDOToUser(User userObj) {
        UserDO userDO = new UserDO();
        userDO.setName(userObj.getUserName());
        userDO.setMail(userObj.getEmail());
        userDO.setUserID(userObj.getUserID());
        return userDO;
    }
}