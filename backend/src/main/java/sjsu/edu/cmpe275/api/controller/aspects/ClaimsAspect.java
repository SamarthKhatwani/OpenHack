package sjsu.edu.cmpe275.api.controller.aspects;

import java.util.HashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import sjsu.edu.cmpe275.OHConstants;
import sjsu.edu.cmpe275.api.resources.ProfileRequest;
import sjsu.edu.cmpe275.api.resources.ResponseMessage;

@Aspect
@Order(3)
@Configuration
public class ClaimsAspect {

	@AfterReturning(pointcut="execution(* sjsu.edu.cmpe275.api.controller.interfaces.INoAuthAPI.signup(..))", returning="response")
	public ResponseEntity<Object> setClaimsAdvice(JoinPoint joinPoint, ResponseEntity<Object> response) throws Throwable{
		
			Object[] args=joinPoint.getArgs();
			HttpStatus status=response.getStatusCode();
			if(status.is2xxSuccessful()) {
				ProfileRequest profileRequest=(ProfileRequest)args[0];
				String email=profileRequest.getEmail();
				String domain=email.substring(email.indexOf("@")+1);
				if(OHConstants.Admin_Email_Domain.equals(domain)) {
					UserRecord userRecord= FirebaseAuth.getInstance().getUserByEmail(email);
					HashMap<String, Object> claims=new HashMap<>();
					claims.put("admin", true);
					try {
						FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
					}
					catch(FirebaseAuthException | IllegalArgumentException e) {
						e.printStackTrace();
						return new ResponseEntity<Object>(new ResponseMessage(false, "Unexpected error"), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} 
			}
			return response;
	}
}
