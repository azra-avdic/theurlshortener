package infobiptask.theurlshortener.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "url")
public class UrlEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "short_code")
    private String shortCode;

    @Column(name = "original_url")
    private String originalUrl;

    private Integer count;

    @Column(name = "redirect_type")
    private Integer redirectType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    public UrlEntity() {
        super();
    }

    public UrlEntity(String shortCode, String originalUrl, Integer redirectType, String accountId) {
        super();
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.redirectType = redirectType;
        this.count = 0;
        this.account = new AccountEntity(accountId);
    }

    public UrlEntity(String originalUrl, Integer count) {
        super();
        this.originalUrl = originalUrl;
        this.count = count;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

}
