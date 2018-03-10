package pe.aloha.app.aloha.Core.model;

/**
 * Created by loualcala on 19/02/18.
 */

public class UserResponse {
    private String token;
    private String error;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
