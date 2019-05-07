package sjsu.edu.cmpe275.api.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * JPA Entity class for User
 * @author Samarth
 *
 */
@Entity
public class Profile implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true, nullable=false)
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
	
	
}
