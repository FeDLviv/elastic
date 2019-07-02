package net.omisoft.elastic.controller;

import lombok.AllArgsConstructor;
import net.omisoft.elastic.annotation.MetricInfo;
import net.omisoft.elastic.entity.Info;
import net.omisoft.elastic.repository.InfoRepository;
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
public class MainController {

    @Autowired
    private final InfoRepository timeRepository;

    private final Random RANDOM = new Random();

    @PostMapping(value = "session")
    @MetricInfo(value = "session")
    public void longWorkSession() throws InterruptedException {
        Thread.sleep((RANDOM.nextInt(5) + 1) * 1_000);
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
