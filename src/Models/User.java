package Models;

public class User {
    private int id_user;
    private String password;
    private String username;

    public User(int id_user, String username, String password)
    {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
    }

}
