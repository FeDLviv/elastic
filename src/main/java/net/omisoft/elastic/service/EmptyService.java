package net.omisoft.elastic.service;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class EmptyService {

    @Autowired
    private final MeterRegistry meterRegistry;

    private final Random RANDOM = new Random();

    public void empty() throws InterruptedException {
        long val = (RANDOM.nextInt(5) + 1) * 1_000;
        Thread.sleep(val);
        //meterRegistry.counter("my.method").increment();
        meterRegistry.timer("my.method").record(val, TimeUnit.MILLISECONDS);
        System.out.println(new Date());
        System.out.println(val);
    }

}
