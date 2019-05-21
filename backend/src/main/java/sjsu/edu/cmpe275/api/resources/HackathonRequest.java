package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

public class HackathonRequest {
	@NotNull
	private String eventName;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;

	private boolean isOpen;
	
	private String description;
	
	private int teamMinSize;
	
	private int teamMaxSize;
	
	private List<String> judges = new ArrayList<>();
	
	private List<String> sponsors = new ArrayList<>();
	
	private float registrationFee;
	
	private float discount;
	
	private boolean isFinalized;
	
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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

	public List<String> getJudges() {
		return judges;
	}

	public void setJudges(List<String> judges) {
		this.judges = judges;
	}

	public List<String> getSponsors() {
		return sponsors;
	}

	public void setSponsors(List<String> sponsors) {
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

}
