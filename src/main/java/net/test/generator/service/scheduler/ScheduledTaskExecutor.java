package net.test.generator.service.scheduler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public interface ScheduledTaskExecutor {

    void executeTask(Runnable task, long period, long workSize, int batchSize, TimeUnit unit);
}
