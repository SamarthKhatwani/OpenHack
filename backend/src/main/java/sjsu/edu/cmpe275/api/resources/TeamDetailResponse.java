package sjsu.edu.cmpe275.api.resources;

import java.util.ArrayList;
import java.util.List;

public class TeamDetailResponse {
	private List<String> member = new ArrayList<String>();
	private String submission;
	private boolean isAllPaid;
	
	public List<String> getMember() {
		return member;
	}
	public void setMember(List<String> member) {
		this.member = member;
	}
	public String getSubmission() {
		return submission;
	}
	public void setSubmission(String submission) {
		this.submission = submission;
	}
	public boolean isAllPaid() {
		return isAllPaid;
	}
	public void setAllPaid(boolean isAllPaid) {
		this.isAllPaid = isAllPaid;
	}
}
