package sjsu.edu.cmpe275.api.controller.aspects;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.resources.ResponseMessage;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Component
@Aspect
@Order(1)
public class AuthenticationAuthorizationAspect {
	
	@Autowired
	private IProfileManagementService profileManagementService;

	@Around("execution(* sjsu.edu.cmpe275.api.controller.interfaces.IAdminAuthAPI.*(..))")
	public ResponseEntity<Object> adminAuthAspect(ProceedingJoinPoint joinPoint) throws Throwable{
		Object[] args=joinPoint.getArgs();
		String headerToken =(String)args[0];
		FirebaseToken verifiedToken=FirebaseAuth.getInstance().verifyIdToken(headerToken);
		Map<String,Object> userClaims=verifiedToken.getClaims();
		Object adminClaim=userClaims.get("admin");
		if(adminClaim!=null) {
			if(!Boolean.parseBoolean((adminClaim.toString()))) {
				return new ResponseEntity<Object>(new ResponseMessage(false, "User does not have admin privileges"), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>(new ResponseMessage(false, "User does not have admin privileges"), HttpStatus.FORBIDDEN);
		}
		return (ResponseEntity<Object>) joinPoint.proceed();
	}
}
