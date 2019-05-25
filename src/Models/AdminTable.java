package Models;

import javafx.fxml.Initializable;

public class AdminTable{
    private Integer id_user;
    private String username, password, email;
    private Boolean admin;

    public AdminTable(Integer id_user, String username, String password, String email, Boolean isAdmin)  {
        this.id_user = id_user;
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = isAdmin;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
