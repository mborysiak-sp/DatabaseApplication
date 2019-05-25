package Models;

public class User {
    private Integer id_user;
    private String password;
    private String username;
    private String email;
    private Boolean admin;

    public User(Integer id_user, String username, String password, String email, Boolean admin)
    {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

}
