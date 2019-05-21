package sjsu.edu.cmpe275.api.resources;

import java.util.Date;

public class Expense {
	private String title;

	private String description;

	private Date timeOfExpense;

	private Float amount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimeOfExpense() {
		return timeOfExpense;
	}

	public void setTimeOfExpense(Date timeOfExpense) {
		this.timeOfExpense = timeOfExpense;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
}
