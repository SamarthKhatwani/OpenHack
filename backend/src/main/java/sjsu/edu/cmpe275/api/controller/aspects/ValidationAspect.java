package sjsu.edu.cmpe275.api.controller.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;

import sjsu.edu.cmpe275.api.exceptions.BadRequestException;
import sjsu.edu.cmpe275.api.persistence.model.Profile;
import sjsu.edu.cmpe275.api.resources.ProfileRequest;
import sjsu.edu.cmpe275.api.service.intefaces.IOrganizationManagementService;
import sjsu.edu.cmpe275.api.service.intefaces.IProfileManagementService;

@Aspect
@Configuration
@Order(6)
public class ValidationAspect {

	@Autowired
	private IProfileManagementService profileManagementService;

	@Autowired
	private IOrganizationManagementService organizationManagementService;

	@Around("execution(* sjsu.edu.cmpe275.api.controller.interfaces.INoAuthAPI.signup(..))")
	public ResponseEntity<Object> validateProfileRequestSignUp(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] object = joinPoint.getArgs();
		ProfileRequest request = (ProfileRequest) (object.length == 1 ? object[0] : object[1]);
		if (request.getEmail() == null) {
			throw new BadRequestException("Email is required");
		}
		Profile profile = profileManagementService.getProfile(request.getEmail());
		if (profile != null) {
			throw new BadRequestException("Profile Already Exist");
		}
		
		profile = profileManagementService.getProfileByScreenName(request.getScreenName());
		if (profile != null) {
			throw new BadRequestException("ScreenName Already Exist");
		}
		
		if (request.getOrganization() != null
				&& organizationManagementService.getOrganizationByName(request.getOrganization()) == null) {
			throw new BadRequestException("Organization Doesn't Exist");
		}
		return (ResponseEntity<Object>) joinPoint.proceed();
	}
	
	@Around("execution(* sjsu.edu.cmpe275.api.controller.interfaces.INormalAuthAPI.updateProfile(..))")
	public ResponseEntity<Object> validateProfileRequestUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] object = joinPoint.getArgs();
		ProfileRequest request = (ProfileRequest) (object.length == 1 ? object[0] : object[1]);
		if (request.getEmail() == null) {
			throw new BadRequestException("Email is required");
		}
		if (request.getOrganization() != null
				&& organizationManagementService.getOrganizationByName(request.getOrganization()) == null) {
			throw new BadRequestException("Organization Doesn't Exist");
		}
		return (ResponseEntity<Object>) joinPoint.proceed();
	}
	 
}
