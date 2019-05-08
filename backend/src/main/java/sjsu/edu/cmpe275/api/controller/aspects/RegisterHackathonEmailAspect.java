package sjsu.edu.cmpe275.api.controller.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import sjsu.edu.cmpe275.api.resources.TeamMemberRequest;
import sjsu.edu.cmpe275.api.resources.TeamRegisterRequest;

@Aspect
@Order(7)
@Configuration
public class RegisterHackathonEmailAspect {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${register.email.subject}")
	private String subject;

	@Value("${register.email.content}")
	private String content;

	@AfterReturning(pointcut = "execution(* sjsu.edu.cmpe275.api.controller.interfaces.IHackerAuthAPI.registerHackathon(..))", returning = "response")
	public ResponseEntity<Object> sendEmailAspect(JoinPoint joinPoint, ResponseEntity<Object> response) {
		Object[] args = joinPoint.getArgs();
		TeamRegisterRequest request = (TeamRegisterRequest) args[1];
		for (TeamMemberRequest m : request.getTeamMembers()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(m.getEmail());
			message.setSubject(request.getEmail() + subject.replace("\"", "") + request.getEventName());
			String[] splits = content.split("#");
			message.setText(splits[0].replace("\"", "") + request.getEventName() + splits[1].replace("\"", "")
					+ "http://someUrl");
			javaMailSender.send(message);
		}
		return response;
	}

}
