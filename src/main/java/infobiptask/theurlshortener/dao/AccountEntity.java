package infobiptask.theurlshortener.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    private String id;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<UrlEntity> urls;

    public AccountEntity() {
        super();
    }

    public AccountEntity(String id) {
        super();
        this.id = id;
    }

    public AccountEntity(String id, String password) {
        super();
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
