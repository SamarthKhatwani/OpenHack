package sjsu.edu.cmpe275.api.resources;

import java.util.Date;

public class MemberDetail {
	
	private String email;
	
	private boolean isPaid;
	
	private Date timeOfPayment;
	
	private Float amountPaid;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Date getTimeOfPayment() {
		return timeOfPayment;
	}

	public void setTimeOfPayment(Date timeOfPayment) {
		this.timeOfPayment = timeOfPayment;
	}

	public Float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Float amountPaid) {
		this.amountPaid = amountPaid;
	}
}
