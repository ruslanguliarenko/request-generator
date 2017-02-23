package net.test.generator;

import net.test.generator.service.http.HttpRequestSender;
import net.test.generator.service.scheduler.ScheduledTaskExecutor;

import java.util.concurrent.TimeUnit;


public class HttpRequestGeneratorImpl implements HttpRequestGenerator {

    public static final long DEFAULT_PERIOD = 1000;

    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MILLISECONDS;

    private ScheduledTaskExecutor executor;
    private HttpRequestSender httpRequestSender;

    public HttpRequestGeneratorImpl(ScheduledTaskExecutor executor, HttpRequestSender httpRequestSender) {
        this.executor = executor;
        this.httpRequestSender = httpRequestSender;
    }

    @Override
    public void generateGetRequest(String url, long period, long workMillis, int batchSize, TimeUnit unit) {

        executor.executeTask(() -> httpRequestSender.sendGet(url), period, workMillis, batchSize, unit);
    }

    @Override
    public void generateGetRequest(String url, long workMillis, int batchSize) {

        generateGetRequest(url, DEFAULT_PERIOD, workMillis, batchSize, DEFAULT_TIME_UNIT);
    }
}
