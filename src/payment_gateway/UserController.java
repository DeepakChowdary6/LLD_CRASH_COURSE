package payment_gateway;

public class UserController {
    UserService userService;

    public UserController() { userService = new UserService(); }

    public UserDO addUser(UserDO userDOobj) { return userService.addUser(userDOobj); }

    public UserDO getUser(int userID) { return userService.getUser(userID); }
}
