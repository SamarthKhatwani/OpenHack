package sjsu.edu.cmpe275.api.controller.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import sjsu.edu.cmpe275.api.resources.ResponseMessage;


@Aspect
@Order(0)
@Configuration
public class NormalAuthAuthenticationAspect {

	/**
	 * validates the user token received from client
	 * @param joinPoint
	 * @return
	 */
	@Around("execution(* sjsu.edu.cmpe275.api.controller.interfaces.INormalAuthAPI.*(..)) || "
			+ "execution(* sjsu.edu.cmpe275.api.controller.interfaces.IAdminAuthAPI.*(..))")
	public ResponseEntity<Object> checkAuthenticationAspect(ProceedingJoinPoint joinPoint) throws Throwable{
		try {	
			Object[] args=joinPoint.getArgs();
			String headerToken =(String)args[0];
			FirebaseToken verifiedToken=FirebaseAuth.getInstance().verifyIdToken(headerToken);
			if(verifiedToken==null) {
				return new ResponseEntity<Object>(new ResponseMessage(false, "User is not authorized" ), HttpStatus.UNAUTHORIZED);
			}
			return (ResponseEntity<Object>) joinPoint.proceed();
			}
			catch(IllegalArgumentException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(new ResponseMessage(false, "User is not authorized"), HttpStatus.UNAUTHORIZED);
			}
			catch (FirebaseAuthException e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(new ResponseMessage(false, "User is not authorized"), HttpStatus.UNAUTHORIZED);
			}
			
	}

}
