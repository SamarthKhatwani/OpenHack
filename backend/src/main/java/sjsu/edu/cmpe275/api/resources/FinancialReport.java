package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class FinancialReport {
	
	private String eventName;
	
	@JsonIgnoreProperties(value= {"score", "rank"})
	private List<LeaderBoardTeam> teams = new ArrayList<>();
	
	private Float revenueFromRegistration;
	
	private Float revenueFromSponsor;
	
	private Float amountUnpaid;
	
	private Float totalExpenses;
	
	private Float profit;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public List<LeaderBoardTeam> getTeams() {
		return teams;
	}

	public void setTeams(List<LeaderBoardTeam> teams) {
		this.teams = teams;
	}

	public Float getRevenueFromRegistration() {
		return revenueFromRegistration;
	}

	public void setRevenueFromRegistration(Float revenueFromRegistration) {
		this.revenueFromRegistration = revenueFromRegistration;
	}

	public Float getRevenueFromSponsor() {
		return revenueFromSponsor;
	}

	public void setRevenueFromSponsor(Float revenueFromSponsor) {
		this.revenueFromSponsor = revenueFromSponsor;
	}

	public Float getAmountUnpaid() {
		return amountUnpaid;
	}

	public void setAmountUnpaid(Float amountUnpaid) {
		this.amountUnpaid = amountUnpaid;
	}

	public Float getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Float totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public Float getProfit() {
		return profit;
	}

	public void setProfit(Float profit) {
		this.profit = profit;
	}

}
