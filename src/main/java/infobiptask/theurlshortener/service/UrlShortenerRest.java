package infobiptask.theurlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import infobiptask.theurlshortener.service.dto.AccountRequest;
import infobiptask.theurlshortener.service.dto.AccountResponse;
import infobiptask.theurlshortener.store.IUrlStoreService;

@RestController
@RequestMapping
public class UrlShortenerRest {

	@Autowired
	private RequestValidator requestValidator;

	@Autowired
	private IUrlStoreService urlStoreService;

	@RequestMapping(method = RequestMethod.POST, value = "/account")
	public ResponseEntity<AccountResponse> registerAccount(
			@RequestBody final AccountRequest request) throws Exception {
		AccountResponse response = requestValidator
				.validateRegisterAccountRequest(request);
		if (!response.getSuccess()) {
			return new ResponseEntity<AccountResponse>(response, null,
					HttpStatus.BAD_REQUEST);
		}

		try {
			String password = generatePassword(8);
			urlStoreService.storeAccount(request.getAccountId(), password);
			response.setPassword(password);
			response.setDescription("Your account is opened!");
			return new ResponseEntity<AccountResponse>(response, null,
					HttpStatus.CREATED);

		} catch (RuntimeException e) {
			response.setDescription(e.getMessage());
			response.setSuccess(false);
			e.printStackTrace();
			return new ResponseEntity<AccountResponse>(response, null,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public ResponseEntity<String> test(
			) throws Exception {
		return new ResponseEntity<>("Succes!!!", null, HttpStatus.OK);
	}

	private String generatePassword(int i) throws RuntimeException {
		// TODO Auto-generated method stub
		return "1234abcd";
	}

}
