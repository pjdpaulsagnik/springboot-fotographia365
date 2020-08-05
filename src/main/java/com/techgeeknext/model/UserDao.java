package com.techgeeknext.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @Column
    @Size(min = 5, max = 255, message = "Please enter between {min} and {max} characters.")
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    @NotBlank
	private String fullname;
    @Column
    @NotBlank
    private String email;
    @Column
    @NotBlank
	private String bio;
    @Column
    @NotBlank
	private String usertype;
    
    public Long getUserID() {
        return user_id;
    }

    public void setUserID(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getfullname() {
		return fullname;
	}
    
	public void setfullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getbio() {
		return bio;
	}
	
	public void setbio(String bio) {
		this.bio = bio;
	}
	
	public String getusertype() {
		return usertype;
	}
	
	public void setusertype(String usertype) {
		this.usertype = usertype;
	}

}

