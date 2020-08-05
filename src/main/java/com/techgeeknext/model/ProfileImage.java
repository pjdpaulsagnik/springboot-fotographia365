package com.techgeeknext.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "profileimage")
public class ProfileImage {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String profileimageid;

	@Column(name="filename")
	private String fileName;

	@Column(name="filetype")
	private String fileType;

	@Lob
	private byte[] data;
	
	@Column(name="username")	
	private String username;
	

	public ProfileImage() {

	}

	public ProfileImage(String fileName, String fileType, byte[] data,String username) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.username = username;

	}

	public String getprofileimageid() {
		return profileimageid;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setprofileimageid(String profileimageid) {
		this.profileimageid = profileimageid;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}


}
