package zjtech.dmf.domain.adminuser;

import zjtech.dmf.domain.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    private String name;
    private String password;
    private String description;
    private boolean pwdChanged;
    private Date lastLogin;
    private boolean locked;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "pwd_changed")
    public boolean isPwdChanged() {
        return pwdChanged;
    }

    public void setPwdChanged(boolean pwdChanged) {
        this.pwdChanged = pwdChanged;
    }

    @Basic
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "locked")
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean deprecated) {
        this.locked = deprecated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (locked != that.locked) return false;
        if (pwdChanged != that.pwdChanged) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pwdChanged ? 1 : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (locked ? 1 : 0);
        return result;
    }
}
