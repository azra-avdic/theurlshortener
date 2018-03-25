package infobiptask.theurlshortener.service;

import infobiptask.theurlshortener.dao.AccountDAO;
import infobiptask.theurlshortener.dao.UrlDAO;
import infobiptask.theurlshortener.service.dto.AccountRequest;
import infobiptask.theurlshortener.service.dto.UrlRequest;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {
    @Autowired
    AccountDAO accountRepo;

    @Autowired
    UrlDAO urlRepo;

    public void validateCreateAccountRequest(final AccountRequest request) throws UrlValidationException {
        if (request.getAccountId() == null || request.getAccountId().isEmpty()) {
            throw new UrlValidationException("Invalid request: AccountId cannot be null or empty!");
        }

        if (existsAccount(request.getAccountId())) {
            throw new UrlValidationException("Account with id " + request.getAccountId() + " already exists!");
        }
    }

    public void validateUrlRequest(UrlRequest request) throws UrlValidationException {
        if (request.getUrl() == null || request.getUrl().isEmpty()) {
            throw new UrlValidationException("Invalid request: url cannot be null or empty!");
        }

        if (!isValidRedirectType(request.getRedirectType())) {
            throw new UrlValidationException("Invalid redirect type!");
        }
        if (!isUrlValid(request.getUrl())) {
            throw new UrlValidationException("Invalid URL: protocol is not specified, or an unknown protocol is found, or URL is null!");
        }

        if (urlRepo.existsByOriginalUrlAndAccountId(request.getUrl(), request.getAccountId())) {
            throw new UrlValidationException("You already shortened URL: " + request.getUrl() + "!");
        }
    }

    public void validateExistsAccount(String accountId) throws Exception {
        if (accountId == null || accountId.isEmpty()) {
            throw new Exception("Invalid request: AccountId cannot be null or empty!");
        }

        if (!existsAccount(accountId)) {
            throw new Exception("Account with id" + accountId + " doesn't exists!");
        }
    }

    private boolean isValidRedirectType(Integer redirectType) {
        if (redirectType == null || HttpStatus.valueOf(redirectType) == HttpStatus.MOVED_PERMANENTLY
                || HttpStatus.valueOf(redirectType) == HttpStatus.FOUND)
            return true;
        return false;
    }

    private boolean isUrlValid(String url) {
        boolean valid = true;
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            valid = false;
        }
        return valid;
    }

    private boolean existsAccount(String accountId) {
        return accountRepo.existsByPK(accountId);
    }

}
