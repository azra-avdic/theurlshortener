package infobiptask.theurlshortener.store;

import java.util.Map;



public interface IUrlStoreService {
    String findUrlById(String id);

    void storeUrl(String id, String url);
    
    
    void storeAccount(String accountId, String password);
    
    
    Map<String, String> getAccounts();
}
