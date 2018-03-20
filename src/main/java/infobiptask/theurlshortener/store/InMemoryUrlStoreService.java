package infobiptask.theurlshortener.store;

import org.springframework.stereotype.Service;

import infobiptask.theurlshortener.service.dto.AccountResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class InMemoryUrlStoreService implements IUrlStoreService{
    private Map<String, String> urlByIdMap = new ConcurrentHashMap<String, String>();
    private Map<String, String> accounts = new HashMap<String, String>();
    
    
    public String findUrlById(String id) {
        return urlByIdMap.get(id);
    }

    
    public void storeUrl(String id, String url) {
        urlByIdMap.put(id, url);
    }

	public void storeAccount(String accountId, String password) {
		accounts.put(accountId, password);		
	}


	@Override
	public Map<String, String>  getAccounts() {
		return accounts;
	}
}
