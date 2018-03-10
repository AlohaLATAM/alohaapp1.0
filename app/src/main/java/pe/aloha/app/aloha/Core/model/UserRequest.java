package pe.aloha.app.aloha.Core.model;

/**
 * Created by loualcala on 19/02/18.
 */

public class UserRequest {
    private String dni;
    private String password;

    public UserRequest() { }

    public String getUsername() {
        return dni;
    }

    public void setUsername(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
