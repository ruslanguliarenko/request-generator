package net.test.generator;

import net.test.generator.service.http.HttpRequestSender;
import net.test.generator.service.http.HttpRequestSenderImpl;
import net.test.generator.service.scheduler.ScheduledTaskExecutor;
import net.test.generator.service.scheduler.TaskExecutor;


public class Launcher {

    public static final int AMOUNT_REQUEST = 400;
    public static final int POOL_SIZE = 200;
    public static final long DEFAULT_WORK_MILLIS = 20_005;
    public static final String DEFAULT_URL = "https://russia.tv/";

    private final HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
    private final ScheduledTaskExecutor executor = TaskExecutor.create(POOL_SIZE);

    private void startGenerate(String url, long workMillis, int amountRequest) {
        HttpRequestGenerator generator = new HttpRequestGeneratorImpl(executor, httpRequestSender);
        generator.generateGetRequest(url, DEFAULT_WORK_MILLIS, amountRequest);
    }

    public static void main(String...args) {
        String url = DEFAULT_URL;
        int amountRequest = AMOUNT_REQUEST;
        long workMillis = DEFAULT_WORK_MILLIS;

        if (args != null && args.length > 3) {
            url = args[0];
            amountRequest = Integer.valueOf(args[1]);
            workMillis = Long.valueOf(args[3]);
        }
        Launcher launcher = new Launcher();
        launcher.startGenerate(url, workMillis, amountRequest);
    }
}
