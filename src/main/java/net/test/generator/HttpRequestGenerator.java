package net.test.generator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public interface HttpRequestGenerator {

    void generateGetRequest(String url, long period, long workSize, int batchSize, TimeUnit unit);

    void generateGetRequest(String url, long workMillis, int batchSize);
}
