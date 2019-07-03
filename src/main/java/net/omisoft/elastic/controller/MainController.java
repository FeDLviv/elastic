package net.omisoft.elastic.controller;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import net.omisoft.elastic.annotation.MetricInfo;
import net.omisoft.elastic.entity.Info;
import net.omisoft.elastic.repository.InfoRepository;
import net.omisoft.elastic.service.EmptyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping
@AllArgsConstructor
//@Timed("my")
public class MainController {

    @Autowired
    private final InfoRepository timeRepository;

    @Autowired
    private final MeterRegistry meterRegistry;

    @Autowired
    private final EmptyService emptyService;

    private final Random RANDOM = new Random();

    @PostMapping(value = "session")
    //@MetricInfo(value = "session")
    //@Timed(value = "my.method", longTask = true)
    public void longWorkSession() throws InterruptedException {
        emptyService.empty();
    }

    @PostMapping(value = "some_method")
    @MetricInfo(value = "some_method")
    public void longWorkOther() throws InterruptedException {
        Thread.sleep((RANDOM.nextInt(5) + 1) * 1_000);
    }

    @GetMapping
    public Iterable<Info> getAllInfo() {
        Sort sort = Sort.by(
                Sort.Order.desc("date")
        );
        return timeRepository.findAll(sort);
    }

}
