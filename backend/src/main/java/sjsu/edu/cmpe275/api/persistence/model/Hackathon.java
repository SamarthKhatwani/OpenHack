package sjsu.edu.cmpe275.api.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Hackathon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String eventName;
	private String startDate;
	private String endDate;

	private String openDate;
	private String closeDate;

	private String description;
	private int teamMinSize;
	private int teamMaxSize;

	@ManyToMany
	@JoinTable(name = "HackathonJudge", joinColumns = {
			@JoinColumn(name = "HackathonId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "JudgeId", referencedColumnName = "id") })
	private List<Profile> judges = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "HackathonSponsor", joinColumns = {
			@JoinColumn(name = "HackathonId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "OrganizationId", referencedColumnName = "id") })
	private List<Organization> sponsors = new ArrayList<>();
	private float registrationFee;
	private float discount;
	private boolean isFinalized;
	private boolean isOpen;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	private List<Team> teams = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTeamMinSize() {
		return teamMinSize;
	}

	public void setTeamMinSize(int teamMinSize) {
		this.teamMinSize = teamMinSize;
	}

	public int getTeamMaxSize() {
		return teamMaxSize;
	}

	public void setTeamMaxSize(int teamMaxSize) {
		this.teamMaxSize = teamMaxSize;
	}

	public List<Profile> getJudges() {
		return judges;
	}

	public void setJudges(List<Profile> judges) {
		this.judges = judges;
	}

	public List<Organization> getSponsors() {
		return sponsors;
	}

	public void setSponsors(List<Organization> sponsors) {
		this.sponsors = sponsors;
	}

	public float getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(float registrationFee) {
		this.registrationFee = registrationFee;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public boolean isFinalized() {
		return isFinalized;
	}

	public void setFinalized(boolean isFinalized) {
		this.isFinalized = isFinalized;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

//	public List<Team> getTeams() {
//		return teams;
//	}
//
//	public void setTeams(List<Team> teams) {
//		this.teams = teams;
//	}

}
