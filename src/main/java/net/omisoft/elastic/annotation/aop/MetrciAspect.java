package net.omisoft.elastic.annotation.aop;

import net.omisoft.elastic.annotation.MetricInfo;
import net.omisoft.elastic.entity.Info;
import net.omisoft.elastic.repository.InfoRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MetrciAspect {

    @Autowired
    private InfoRepository timeRepository;

    @Around("@annotation(net.omisoft.elastic.annotation.MetricInfo)")
    public Object aroundTimer(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MetricInfo annotation = method.getAnnotation(MetricInfo.class);

        long start = System.nanoTime();
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        long stop = System.nanoTime();

        try {
            Info unit = Info.builder()
                    .name(annotation.value())
                    .duration(stop - start)
                    .build();
            timeRepository.save(unit);
        } catch (Exception ex) {
            return proceed;
        }

        return proceed;
    }

}
