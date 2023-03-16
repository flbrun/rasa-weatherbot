package de.fhdw.webtec.weatherchat.aspect;

import de.fhdw.webtec.weatherchat.jsonmapper.MessageToFrontendMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
------------------------------------------------------------------------------------------------------------------------
    This Class is an Aspect for logging.
    It is used to log the incoming requests and the outgoing responses.
    It is also used to log the exceptions that occur during execution.
    The logging is done with the java.util.logging.Logger
    Every method that is annotated with @Around is executed before and after the method it is applied to.
    Every method that is annotated with @After is executed after the method it is applied to.
    Every method that is annotated with @Before is executed before the method it is applied to.
    see https://www.baeldung.com/spring-aop-annotation for more information
------------------------------------------------------------------------------------------------------------------------
 */

@Aspect
@Component
public class LoggingAspect {
    /*
------------------------------------------------------------------------------------------------------------------------
    Logging for all methods in the package de.fhdw.webtec.weatherchat.service
------------------------------------------------------------------------------------------------------------------------
     */

    @Around(value = "execution(* de.fhdw.webtec.weatherchat.service.MessageHandler.frontendInputHandler(..))")
    protected Object rasaAnswerCheckerResponseTime(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable
    {
        StopWatch stopWatch = new StopWatch();
        String className = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        try
        {
            stopWatch.start();
            return proceedingJoinPoint.proceed();
        }
        catch (Exception e)
        {
            Logger.getLogger(className)
                    .log(Level.SEVERE, "Rasa Response Handler Error: {0}", new String[]{e.getMessage()});
            return MessageToFrontendMapper
                    .convertForFrontend("Tut mir leid, ich habe dich nicht verstanden.", "");
        }
        finally
        {
            stopWatch.stop();
            Logger.getLogger(className)
                    .log(Level.INFO, "Rasa Response Handler took {0} ms", new Long[]{stopWatch.getTotalTimeMillis()});
        }
    }
    /*
------------------------------------------------------------------------------------------------------------------------
    Logging for all methods in the package de.fhdw.webtec.weatherchat.api
------------------------------------------------------------------------------------------------------------------------
     */

    @After(value = "execution(public * de.fhdw.webtec.weatherchat.api.*.*.*(..))")
    public void methodCallInformation(JoinPoint joinPoint)
    {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] signatureArgs = joinPoint.getArgs();
            Logger.getLogger(className)
                    .log(Level.INFO, "{0} called with Arguments: {1}", new String[]{joinPoint.getSignature().getName(), Arrays.toString(signatureArgs)});
    }


}
