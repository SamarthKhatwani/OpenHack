package sjsu.edu.cmpe275.api.controller.aspects;

import java.util.List;

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

import sjsu.edu.cmpe275.api.persistence.model.HackathonTeamProfile;
import sjsu.edu.cmpe275.api.persistence.repository.HacathonTeamProfileRepository;
import sjsu.edu.cmpe275.api.resources.Quotation;
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

	@Value("${register.email.page}")
	private String page;

	@Autowired
	private HacathonTeamProfileRepository hacathonTeamProfileRepository;

	@AfterReturning(pointcut = "execution(* sjsu.edu.cmpe275.api.controller.interfaces.IHackerAuthAPI.registerHackathon(..))", returning = "response")
	public ResponseEntity<Object> sendEmailAspect(JoinPoint joinPoint, ResponseEntity<Object> response) {
		Object[] args = joinPoint.getArgs();
		TeamRegisterRequest request = (TeamRegisterRequest) args[1];
		for (TeamMemberRequest member : request.getTeamMembers()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(member.getEmail());
			message.setSubject(request.getEmail() + subject.replace("\"", "") + request.getEventName());
			String[] splits = content.split("#");
			message.setText(splits[0].replace("\"", "") + request.getEventName() + splits[1].replace("\"", "") + page
					+ "?email=" + member.getEmail() + "&eventName=" + request.getEventName());
			javaMailSender.send(message);
		}
		return response;
	}

	@AfterReturning(pointcut = "execution(* sjsu.edu.cmpe275.api.controller.interfaces.IHackerAuthAPI.makePayment(..))", returning = "response")
	public ResponseEntity<Object> sendEmailAllRegisterdAspect(JoinPoint joinPoint, ResponseEntity<Object> response) {
		Object[] args = joinPoint.getArgs();
		Quotation request = (Quotation) args[1];
		List<HackathonTeamProfile> profiles = hacathonTeamProfileRepository.findByTeamName(request.getTeamName());
		String to = "";
		boolean allPaid = true;
		for (HackathonTeamProfile profile : profiles) {
			if (!profile.isPaid()) {
				allPaid = false;
				break;
			}
			if (profile.isLead()) {
				to = profile.getProfile().getEmail();
			}
		}
		if (allPaid && !to.isEmpty()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject("Everyone in you team: " + request.getTeamName() + " paid the fee for event: "
					+ request.getEventName() + ", Enjoy hacking");
			message.setText("Everyone in you team: " + request.getTeamName() + " paid the fee for event: "
					+ request.getEventName() + ", Enjoy hacking");
			javaMailSender.send(message);
		}
		return response;
	}

	@AfterReturning(pointcut = "execution(* sjsu.edu.cmpe275.api.controller.interfaces.IAdminAuthAPI.createUpdateHackathon(..))", returning = "response")
	public ResponseEntity<Object> sendLeaderBoardEmail(JoinPoint joinPoint, ResponseEntity<Object> response) {
		return response;
	}
}
