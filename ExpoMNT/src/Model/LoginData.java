package Model;

public class LoginData {
    private String name;
    private String username;
    private String password;

    public LoginData(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    // Getter dan setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
