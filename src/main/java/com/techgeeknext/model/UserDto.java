package com.techgeeknext.model;

import org.springframework.web.multipart.MultipartFile;

public class UserDto {
	private Long user_id; 
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String bio;
    private String usertype;
    private String jsontoken;
    private MultipartFile file;
    
    public Long getUserID() {
        return user_id;
    }

    public void setUserID(Long user_id) {
        this.user_id = user_id;
    }
    
    public String getjsontoken() {
        return jsontoken;
    }

    public void setjsontoken(String jsontoken) {
        this.jsontoken = jsontoken;
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
	
	public MultipartFile getfile() 
	{
		return file;
	}
	
	public void setfile(MultipartFile file) 
	{
		this.file = file;
	}
	
}
