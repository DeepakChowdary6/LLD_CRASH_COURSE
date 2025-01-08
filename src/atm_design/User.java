package atm_design;

public class User {
    public static Integer idcounter=0;
    private final Integer userid;
    private final String name;

    public User(String name) {
        this.name = name;
        this.userid=idcounter++;
    }

    public String getName() {
        return name;
    }
}
