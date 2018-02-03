package com.dbh.rabbitmq;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class HandlerExecutePool {

    private ExecutorService executor;

    public HandlerExecutePool(int corePoolSize){

        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        executor = new ThreadPoolExecutor(corePoolSize,
                200, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), nameThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

    }

    public void execute(Runnable task){
        executor.execute(task);
    }

    public void shutdown(){
        executor.shutdown();
    }

}
