package net.test.generator.service.scheduler;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskExecutor implements ScheduledTaskExecutor {

    public static final int DEFAULT_POOL_SIZE = 10;

    private int poolSize;

    private long delayStart = 0;

    private TaskExecutor(int poolSize) {
        this.poolSize = poolSize;
    }

    public static ScheduledTaskExecutor create(int poolSize) {
        return new TaskExecutor(poolSize);
    }

    public static ScheduledTaskExecutor create() {
        return new TaskExecutor(DEFAULT_POOL_SIZE);
    }


    @Override
    public void executeTask(Runnable task, long period, long workMillis, int batchSize, TimeUnit unit) {
        if (batchSize < 0 || workMillis < 0) {
            throw new IllegalArgumentException();
        }

        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(poolSize);
        
        for (int k = 0; k <= batchSize; k++) {
            executor.scheduleAtFixedRate(() -> {
                executor.execute(() -> {
                    task.run();
                });
            }, delayStart, period, unit);
        }
        stopExecutorOverTime(executor, workMillis);
    }

    private void stopExecutorOverTime(ExecutorService executor, long timeMillis) {
        executor.execute(() -> {
            try {
                Thread.sleep(timeMillis);
                executor.shutdownNow();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public long getDelayStart() {
        return delayStart;
    }

    public void setDelayStart(long delayStart) {
        this.delayStart = delayStart;
    }
}
