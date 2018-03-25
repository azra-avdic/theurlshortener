package infobiptask.theurlshortener.service.dto;

import java.io.Serializable;

public class UrlResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String shortUrl;

    public UrlResponse() {
        super();
    }

    public UrlResponse(String shortUrl) {
        super();
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shortUrl == null) ? 0 : shortUrl.hashCode());
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
        UrlResponse other = (UrlResponse) obj;
        if (shortUrl == null) {
            if (other.shortUrl != null)
                return false;
        } else if (!shortUrl.equals(other.shortUrl))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UrlResponse [shortUrl=" + shortUrl + "]";
    }

}
