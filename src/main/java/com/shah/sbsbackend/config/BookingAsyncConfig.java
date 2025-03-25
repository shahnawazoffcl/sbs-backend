package com.shah.sbsbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class BookingAsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);  // Set the core pool size (minimum number of threads)
        executor.setMaxPoolSize(50);   // Set the maximum pool size (maximum number of threads)
        executor.setQueueCapacity(100);  // Set the capacity of the queue (for waiting tasks)
        executor.setThreadNamePrefix("AsyncThread-");  // Prefix for thread names (optional)
        executor.initialize();
        return executor;
    }
}
