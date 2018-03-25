package infobiptask.theurlshortener.service.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UrlRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String url;
    private Integer redirectType;

    @JsonIgnore
    private String accountId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "UrlRequest [url=" + url + ", redirectType=" + redirectType + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((redirectType == null) ? 0 : redirectType.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UrlRequest other = (UrlRequest) obj;
        if (redirectType == null) {
            if (other.redirectType != null)
                return false;
        } else if (!redirectType.equals(other.redirectType))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

}
