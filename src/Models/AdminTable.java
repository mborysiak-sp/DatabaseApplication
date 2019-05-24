package Models;

public class AdminTable {
    private Integer id_user;
    private String username, password, email;
    private Boolean isAdmin;

    public AdminTable(Integer id_user, String username, String password, String email, Boolean isAdmin) {
        this.id_user = id_user;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

}
