package sjsu.edu.cmpe275.api.resources;

public class MemberDetail {
	private String email;
	
	private boolean isPaid;

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
}
