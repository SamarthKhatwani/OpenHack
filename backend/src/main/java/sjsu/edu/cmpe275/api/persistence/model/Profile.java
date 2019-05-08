package sjsu.edu.cmpe275.api.persistence.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * JPA Entity class for User
 * @author Samarth
 *
 */
@Entity
public class Profile implements Serializable {

	@Id
	private String email;
		
	@Column(unique=true)
	private String screenName;
	
	private String name;
	
	private String portraitUrl;
	
	private String businessTitle;
	
	private String aboutMe;
	
	private String address;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
	private Organization organization;
	
	private Boolean organizationApprovalStatus;
	
	private boolean isAmdin;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="judges")
	private List<Hackathon> hackathonJudge;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="profile")
	private List<HackathonTeamProfile> hackathonTeamProfiles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}

	public String getPortraitUrl() {
		return portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}

	public String getBusinessTitle() {
		return businessTitle;
	}

	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public boolean isOrganizationApprovalStatus() {
		return organizationApprovalStatus;
	}

	public void setOrganizationApprovalStatus(Boolean organizationApprovalStatus) {
		this.organizationApprovalStatus = organizationApprovalStatus;
	}

	public boolean isAmdin() {
		return isAmdin;
	}

	public void setAmdin(boolean isAmdin) {
		this.isAmdin = isAmdin;
	}

	public Boolean getOrganizationApprovalStatus() {
		return organizationApprovalStatus;
	}

	public List<Hackathon> getHackathonJudge() {
		return hackathonJudge;
	}

	public void setHackathonJudge(List<Hackathon> hackathonJudge) {
		this.hackathonJudge = hackathonJudge;
	}

	public List<HackathonTeamProfile> getHackathonTeamProfiles() {
		return hackathonTeamProfiles;
	}

	public void setHackathonTeamProfiles(List<HackathonTeamProfile> hackathonTeamProfiles) {
		this.hackathonTeamProfiles = hackathonTeamProfiles;
	}

}
