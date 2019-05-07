package sjsu.edu.cmpe275.api.persistence.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false)
	private String name;
	private String address;
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", referencedColumnName = "email")
	@JsonIgnore
	private Profile email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	@JsonIgnore
	private List<Profile> members = new ArrayList<Profile>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Profile getEmail() {
		return email;
	}

	public void setEmail(Profile owner) {
		this.email = owner;
	}

	public List<Profile> getRequests() {
		return this.getMembers().stream().filter(profile -> profile.isOrganizationApprovalStatus())
				.collect(Collectors.toList());
	}

	public List<Profile> getMembers() {
		return this.members;
	}

	public void setMembers(List<Profile> members) {
		this.members = members;
	}

}
