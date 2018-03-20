package infobiptask.theurlshortener.service;

import infobiptask.theurlshortener.service.dto.AccountRequest;
import infobiptask.theurlshortener.service.dto.AccountResponse;
import infobiptask.theurlshortener.store.IUrlStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RequestValidator {

    @Autowired
    private IUrlStoreService urlStoreService;


    public AccountResponse validateRegisterAccountRequest(final AccountRequest request) {
    	if(request.getAccountId()== null || request.getAccountId().isEmpty()){
    	return new AccountResponse(false, "Invalid request: accountId cannot be null or empty!");
    	}
    	
    	if(existsAccount(request.getAccountId())){
        	return new AccountResponse(false, "Account with id"  + request.getAccountId() +" already exists!");
    	}

        return  new AccountResponse(true, null);
    }

private boolean existsAccount(String accountId) {
	return false;
}

}
