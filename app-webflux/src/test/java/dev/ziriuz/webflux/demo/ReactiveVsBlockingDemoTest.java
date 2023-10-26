package dev.ziriuz.webflux.demo;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReactiveVsBlockingDemoTest {

    private final ExecutorService taskExecutor = Executors.newFixedThreadPool(3);

    private static void log(String message) {
        System.out.println( LocalDateTime.now() + " [" + Thread.currentThread().getName() + "] " + message);
    }

    private Runnable process(int input) {
        return () ->{
            String request = String.format("{date: %s, input: %d}", new Date(), input);
            log("received request: " + request);
            int result = timeConsumingCalculate(input);
            sendResponse(result);
        };
    }

    //@Contract(pure = true)
    private Runnable processReactive(int input) {
        return () ->{
            String request = String.format("{date: %s, input: %d}", new Date(), input);
            log("received request: " + request);
            Mono.fromSupplier(() -> timeConsumingCalculate(input))
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe(result -> taskExecutor.execute( () -> sendResponse(result)));
        };
    }

    public void runReactiveTest() {
        log("========== START ============");
        for (int i = 0; i < 10; i++) {
            taskExecutor.execute(processReactive(i + 1));
            log("Task submitted:: " + (i + 1));
        }
        log("========== END ============");
        sleepSeconds(4);
    }

    public void runBlockingTest() {
        log("========== START ============");
        for (int i = 0; i < 10; i++) {
            taskExecutor.execute(process(i + 1));
            log("Task submitted:: " + (i + 1));
        }
        log("========== END ============");
        sleepSeconds(15);
    }

    public void stop(){
        taskExecutor.shutdownNow();
    }

    private int timeConsumingCalculate(int input) {
        log("start calculation for " + input);
        sleepSeconds(3);
        int res = input * input;
        log("finished calculation for " + input + ", result: " + res);
        return res;
    }

    private void sendResponse(int result){
        String response = String.format("{date: %s, output: %d})", new Date(), result);
        log("sending response " + response);
    }

    static void sleepSeconds(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        var app = new ReactiveVsBlockingDemoTest();
        log("################### BLOCKING Thread-per-request ##################");
        app.runBlockingTest();
        log("##############################################################");
        sleepSeconds(5);
        log("################### Non-BLOCKING Event Loop ##################");
        app.runReactiveTest();
        log("##############################################################");
        app.stop();

    }

}
