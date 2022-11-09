package liga.medical.messageanalyzer.core.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.messageanalyzer.core.model.Debug;
import liga.medical.messageanalyzer.core.model.Exception;
import liga.medical.messageanalyzer.core.model.MessageDTO;
import liga.medical.messageanalyzer.core.model.MessageStatus;
import liga.medical.messageanalyzer.core.service.DebugService;
import liga.medical.messageanalyzer.core.service.ExceptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final DebugService debugService;
    private final ExceptionService exceptionService;

    private final AtomicLong atomicLong = new AtomicLong(0);

    @Pointcut("@annotation(liga.medical.messageanalyzer.core.annotations.DbLog)")
    public void loggableMethodsQueue() {
    }

    @Around("loggableMethodsQueue()")
    public Object applicationLogger(ProceedingJoinPoint joinPoint) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String className = joinPoint.getTarget().getClass().toString();
        String methodName = joinPoint.getSignature().getName();
        Object[] array = joinPoint.getArgs();
        MessageDTO messageDTO = (MessageDTO) Arrays.stream(array).findFirst().get();

        if (messageDTO.getType() == MessageStatus.ALERT || messageDTO.getType() == MessageStatus.DAILY) {
            Debug debug = new Debug();
            debug.setId(atomicLong.incrementAndGet());
            debug.setSystemTypeId(messageDTO.getType().toString());
            debug.setMethodParams(className + "." + methodName + "" + mapper.writeValueAsString(array));
            debugService.saveDebugInDB(debug);
            log.info("id: " + debug.getId() + " system_type: " + debug.getSystemTypeId() + " param: " + debug.getMethodParams());
        } else {
            Exception exception = new Exception();
            exception.setId(atomicLong.incrementAndGet());
            exception.setSystemTypeId("Error");
            exception.setMethodParams(className + "." + methodName + "" + mapper.writeValueAsString(array));
            exceptionService.saveExceptionInDB(exception);
            log.error("id: " + exception.getId() + " system_type: " + exception.getSystemTypeId() + " param: " + exception.getMethodParams());
        }

        Object obj = null;

        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
}