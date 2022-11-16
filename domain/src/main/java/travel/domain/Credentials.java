package travel.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Credentials {
    @Column(name = "login_name")
    private String loginName;
    private String password;

    public Credentials(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public Credentials() {

    }

    @Override
    public String toString() {
        return "Credentials{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return Objects.equals(loginName, that.loginName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, password);
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
