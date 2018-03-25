package infobiptask.theurlshortener.service;

import infobiptask.theurlshortener.dao.AccountDAO;
import infobiptask.theurlshortener.dao.AccountEntity;
import infobiptask.theurlshortener.dao.UrlDAO;
import infobiptask.theurlshortener.dao.UrlEntity;
import infobiptask.theurlshortener.rest.UrlShortenerRest;
import infobiptask.theurlshortener.service.dto.AccountRequest;
import infobiptask.theurlshortener.service.dto.AccountResponse;
import infobiptask.theurlshortener.service.dto.UrlRequest;
import infobiptask.theurlshortener.service.dto.UrlResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenerRest.class);

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    AccountDAO accountDao;

    @Autowired
    UrlDAO urlDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody final UrlRequest request) throws Exception {
        requestValidator.validateUrlRequest(request);

        String accountId = request.getAccountId();
        String url = request.getUrl();
        Integer redirectType = request.getRedirectType() == null ? HttpStatus.FOUND.value() : request.getRedirectType();
        String hasshedUrl = UrlUtils.hashMD5(url + accountId);

        boolean success = false;
        String shortCode = null;
        long startTime = System.currentTimeMillis();

        while (!success && notTimedOut(startTime)) {
            try {
                shortCode = tryToInsertToDB(url, hasshedUrl, redirectType, accountId);
                success = true;
            } catch (ConstraintViolationException | DataIntegrityViolationException e) {
                shortCode = null;
                LOGGER.error(e.toString());
            }
        }

        return new ResponseEntity<>(new UrlResponse(UrlUtils.root + shortCode), null, HttpStatus.CREATED);
    }

    private boolean notTimedOut(long startTime) {
        if ((System.currentTimeMillis() - startTime) > 5000) {
            throw new RuntimeException("Timeout occured while generating short url");
        }
        return true;
    }

    @Override
    public ResponseEntity<AccountResponse> createAccount(@RequestBody final AccountRequest request) throws Exception {
        AccountResponse response = new AccountResponse();
        try {
            requestValidator.validateCreateAccountRequest(request);
            String password = UrlUtils.generateRandomString(8);
            accountDao.persist(new AccountEntity(request.getAccountId(), passwordEncoder.encode(password)));

            response.setSuccess(true);
            response.setPassword(password);
            response.setDescription("Your account is opened!");
            return new ResponseEntity<AccountResponse>(response, null, HttpStatus.CREATED);

        } catch (UrlValidationException e) {
            response.setSuccess(false);
            response.setDescription(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<AccountResponse>(response, null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> redirectUrl(String shortCode) {
        UrlEntity urlEntity = urlDao.findByPK(shortCode);
        if (urlEntity == null) {
            return new ResponseEntity<>("Url not found!", null, HttpStatus.NOT_FOUND);
        }

        RedirectCountQueue.getInstance().add(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", urlEntity.getOriginalUrl());

        return new ResponseEntity<String>(headers, HttpStatus.valueOf(urlEntity.getRedirectType()));
    }

    @Override
    public ResponseEntity<Map<String, Integer>> getStatisticForAccount(String accountId) throws Exception {
        requestValidator.validateExistsAccount(accountId);

        List<UrlEntity> urls = urlDao.findAllByAccountId(accountId);
        Map<String, Integer> map = urls.stream().collect(Collectors.toMap(UrlEntity::getOriginalUrl, UrlEntity::getCount));

        return new ResponseEntity<>(map, null, HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String tryToInsertToDB(String url, String hasshedUrl, Integer redirectType, String accountId)
            throws ConstraintViolationException, DataIntegrityViolationException {

        String shortedHashedUrl = UrlUtils.randomSubstring(hasshedUrl, 6);
        UrlEntity entity = new UrlEntity(shortedHashedUrl, url, redirectType, accountId);
        urlDao.persist(entity);

        return shortedHashedUrl;
    }

}
