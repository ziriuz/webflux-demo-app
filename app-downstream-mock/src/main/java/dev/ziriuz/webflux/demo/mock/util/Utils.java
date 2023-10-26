package dev.ziriuz.webflux.demo.mock.util;

public class Utils {
    public static void sleepSeconds(int i) {
        try {
            Thread.sleep(1000 * i);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
