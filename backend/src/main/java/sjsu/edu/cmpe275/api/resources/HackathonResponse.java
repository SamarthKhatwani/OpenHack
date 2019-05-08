package sjsu.edu.cmpe275.api.resources;

import java.util.Date;
import java.util.List;

public class HackathonResponse {
	private boolean success;
	private String message;
	private String eventName;
	private Date startDate;
	private Date endDate;
	private String description;
	private float registrationFee;
	private int teamMinSize;
	private int teamMaxSize;
	private List<String> judges;
	private List<String> sponsors;
	private Float discount;
	private Date openDate;
	private Date closeDate;
	private boolean isFinalized;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(float registrationFee) {
		this.registrationFee = registrationFee;
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

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public boolean isFinalized() {
		return isFinalized;
	}

	public void setFinalized(boolean isFinalized) {
		this.isFinalized = isFinalized;
	}

}
