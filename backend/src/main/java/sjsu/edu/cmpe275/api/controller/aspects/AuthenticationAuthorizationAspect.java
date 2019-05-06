package sjsu.edu.cmpe275.api.controller.aspects;

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
		Profile profile = profileManagementService.getProfile(verifiedToken.getEmail());
		if(profile==null || !profile.isAmdin()) {
			return new ResponseEntity<Object>(new ResponseMessage(false, "User is forbidden to access admin route"), HttpStatus.FORBIDDEN);
		}
		return (ResponseEntity<Object>) joinPoint.proceed();
	}
}
