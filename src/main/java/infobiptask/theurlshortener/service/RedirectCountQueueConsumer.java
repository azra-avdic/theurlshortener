package infobiptask.theurlshortener.service;

import infobiptask.theurlshortener.dao.UrlDAO;
import infobiptask.theurlshortener.dao.UrlEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class RedirectCountQueueConsumer {

    @Autowired
    UrlDAO urlRepo;

    @Scheduled(initialDelayString = "1000", fixedRateString = "1000")
    public void run() {
        RedirectCountQueue queue = RedirectCountQueue.getInstance();
        String shortCode;
        if ((shortCode = queue.poll()) != null) {
            UrlEntity urlEntity = urlRepo.findByPK(shortCode);
            urlEntity.setCount(urlEntity.getCount() + 1);
            urlRepo.merge(urlEntity);
        }
    }
}
