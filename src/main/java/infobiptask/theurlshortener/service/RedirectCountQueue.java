package infobiptask.theurlshortener.service;

import java.util.concurrent.ConcurrentLinkedQueue;

public class RedirectCountQueue {
    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
    private static RedirectCountQueue queueInstance = null;

    public static RedirectCountQueue getInstance() {
        if (queueInstance == null) {
            queueInstance = new RedirectCountQueue();
        }
        return queueInstance;
    }

    public void add(String value) {
        queue.add(value);
    }

    public String poll() {
        return queue.poll();
    }

}
