package engine.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    private Long id;

    @NotNull(message = "Enter email, please")
    @Email(message = "Email must be correct")
    private String email;

    @NotNull(message = "Enter password, please")
    @Size(min = 5, message = "Password must have at least 5 symbols.")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

     */

}
