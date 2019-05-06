package sjsu.edu.cmpe275.api.controller.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import sjsu.edu.cmpe275.api.exceptions.BadRequestException;
import sjsu.edu.cmpe275.api.resources.ResponseMessage;

@Aspect
@Configuration
@Order(5)
public class ExceptionAspect {

	@Around("execution(* sjsu.edu.cmpe275.api.controller.interfaces.*.*(..))")
	public ResponseEntity<Object> badRequestExceptionAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
		try {
			return (ResponseEntity<Object>) joinPoint.proceed();
		}catch(BadRequestException ex) {
			return new ResponseEntity<Object>(new ResponseMessage(false, ex.getMessage() ), HttpStatus.BAD_REQUEST);
		}
	}
	

}
