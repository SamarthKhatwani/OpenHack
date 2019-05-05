package sjsu.edu.cmpe275.api.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * JPA Entity class for User
 * @author Samarth
 *
 */
@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true, nullable=false)
	private String email;
		
	@Column(unique=true, nullable=false)
	private String screenName;
	
	private String firstName;
	
	private String lastName;
	
	private String portraitUrl;
	
	private String businessTitle;
	
	private String aboutMe;
	
	private String address;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
	private Organization employeeOrganization;
	
	private Boolean organizationApprovalStatus;
	
	private boolean isAmdin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Organization getEmployeeOrganization() {
		return employeeOrganization;
	}

	public void setEmployeeOrganization(Organization organization) {
		this.employeeOrganization = organization;
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
	
	
}
