package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	private String name;
	
	private Set<Reservation> reservations;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "user")
	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", reservations="
				+ reservations + "]";
	}

	public boolean equals(Object other){
		if((this == other))
			return true;
		if(!(other instanceof User))
			return false;
		User castOther = (User)other;
		return new EqualsBuilder().append(this.getUserId(), castOther.getUserId()).append(this.getName(),castOther.getName()).isEquals();
	}
	
	public int hashCode(){
		return new HashCodeBuilder().append(getUserId()).append(getName()).toHashCode();
	}
	
	

}
