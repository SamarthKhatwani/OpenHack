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
    @JoinColumn(name = "owner", referencedColumnName = "email")
	private Profile owner;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
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

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}

	public List<Profile> getRequests() {
		this.getMembers().size();
		return this.getMembers().stream().filter(profile -> !profile.isOrganizationApprovalStatus())
				.collect(Collectors.toList());
	}

	public List<Profile> getMembers() {
		return this.members;
	}

	public void setMembers(List<Profile> members) {
		this.members = members;
	}

}
