package infobiptask.theurlshortener.service;

import infobiptask.theurlshortener.service.dto.AccountRequest;
import infobiptask.theurlshortener.service.dto.AccountResponse;
import infobiptask.theurlshortener.service.dto.UrlRequest;
import infobiptask.theurlshortener.service.dto.UrlResponse;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UrlShortenerService {

    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody final UrlRequest request) throws Exception;

    public ResponseEntity<AccountResponse> createAccount(@RequestBody final AccountRequest request) throws Exception;

    public ResponseEntity<String> redirectUrl(String shortCode);

    public ResponseEntity<Map<String, Integer>> getStatisticForAccount(String accountId) throws Exception;

}
