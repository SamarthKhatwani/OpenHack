package sjsu.edu.cmpe275.api.controller.aspects;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Aspect
@Order(0)
@Configuration
public class ClaimsAspect {

	//@Around("execution(* sjsu.edu.cmpe275.api.controller.interfaces.INormalAuthAPI.*(..))")
}
