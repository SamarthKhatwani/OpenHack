package sjsu.edu.cmpe275.api.persistence.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Hackathon implements Serializable{
	@Id
	private String eventName;
	private Date startDate;
	private Date endDate;

	private boolean isOpen;

	private String description;
	private int teamMinSize;
	private int teamMaxSize;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "HackathonJudge", joinColumns = {
			@JoinColumn(name = "HackathonId", referencedColumnName = "eventName") }, inverseJoinColumns = {
					@JoinColumn(name = "JudgeId", referencedColumnName = "email") })
	private List<Profile> judges = new ArrayList<>();

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "HackathonSponsor", joinColumns = {
			@JoinColumn(name = "HackathonId", referencedColumnName = "eventName") }, inverseJoinColumns = {
					@JoinColumn(name = "OrganizationId", referencedColumnName = "name") })
	private List<Organization> sponsors = new ArrayList<>();
		
	private float registrationFee;
	private float discount;
	private boolean isFinalized;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hackathon")
	private List<HackathonTeamProfile> teams = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hackathon")
	private List<Expense> expenses = new ArrayList<>();

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() throws ParseException {
//		DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = inputFormatter.parse(inputFormatter.format(startDate));
		return this.startDate;
	}

	public void setStartDate(Date startDate) throws ParseException {
//		DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = inputFormatter.parse(inputFormatter.format(startDate));
		this.startDate = startDate;
	}

	public Date getEndDate() throws ParseException {
//		DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = inputFormatter.parse(inputFormatter.format(endDate));
		return this.endDate;
	}

	public void setEndDate(Date endDate) throws ParseException {
//		DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = inputFormatter.parse(inputFormatter.format(endDate));
		this.endDate = endDate;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
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

	public List<HackathonTeamProfile> getTeams() {
		return teams;
	}

	public void setTeams(List<HackathonTeamProfile> teams) {
		this.teams = teams;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

}
