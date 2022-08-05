package com.minhlv.socialappapi.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MonitorThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                // UniConnection.printConnection();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("MONITOR THREAD: {}", e);
            }
        }
    }
}
