package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;

public class HackathonRequest {
	
	private String eventName;
	
	private String startDate;
	
	private String endDate;
	
	private String openDate;
	
	private String closeDate;
	
	private String description;
	
	private int teamMinSize;
	
	private int teamMaxSize;
	
	private List<String> judges = new ArrayList<>();
	
	private List<String> sponsors = new ArrayList<>();
	
	private float registrationFee;
	
	private float discount;
	
	private boolean isFinalized;
	
	private boolean isOpen;

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

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

}
