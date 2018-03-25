package infobiptask.theurlshortener.rest;

import infobiptask.theurlshortener.service.ForbiddenAccessException;
import infobiptask.theurlshortener.service.UrlShortenerService;
import infobiptask.theurlshortener.service.dto.AccountRequest;
import infobiptask.theurlshortener.service.dto.AccountResponse;
import infobiptask.theurlshortener.service.dto.UrlRequest;
import infobiptask.theurlshortener.service.dto.UrlResponse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UrlShortenerRest {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @RequestMapping(method = RequestMethod.POST, value = "/account")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody final AccountRequest request) throws Exception {
        return urlShortenerService.createAccount(request);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody final UrlRequest request) throws Exception {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String accountId = authUser.getUsername();
        request.setAccountId(accountId);
        return urlShortenerService.shortenUrl(request);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{shortCode}")
    public ResponseEntity<String> redirect(@PathVariable String shortCode) throws Exception {
        return urlShortenerService.redirectUrl(shortCode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/statistic/{accountId}")
    public ResponseEntity<Map<String, Integer>> getStats(@PathVariable String accountId) throws Exception {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authAccountId = authUser.getUsername();

        if (!authAccountId.equals(accountId))
            throw new ForbiddenAccessException("You are not allowed to see statistic for account " + accountId);

        return urlShortenerService.getStatisticForAccount(accountId);
    }



}
