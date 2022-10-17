package be.pxl.boardgame.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class BoardGameAspectAround {

    @Around("execution(* be.pxl.boardgame.service.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // Before

        System.out.println("Around...");

        // hoe lang was vorige call
        // > 5min geleden
        // then joinPoint.proceed()
        // else too_busy

        // return result
        Object result = joinPoint.proceed();

        // After

        System.out.println(result);

        return result;
        // after returning
        // after throwing
    }

}
