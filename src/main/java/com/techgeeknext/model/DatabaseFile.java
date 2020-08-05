package com.techgeeknext.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class DatabaseFile {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String postid;

	private String fileName;

	private String fileType;

	@Lob
	private byte[] data;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="post_title")
	public String posttitle;
	
	@Column(name="post_description")
	public String postdescription;

	public DatabaseFile() {

	}

	public DatabaseFile(String fileName, String fileType, byte[] data,int userId, String posttitle, String postdescription) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.userId = userId;
		this.posttitle = posttitle;
		this.postdescription = postdescription;
	}

	public String getpostId() {
		return postid;
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

	public void setpostId(String postid) {
		this.postid = postid;
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

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = new Integer(userId);
	}
	
	public String getPostTitle() {
		return posttitle;
	}

	public void setPostTitle(String posttitle) {
		this.posttitle = posttitle;
	}

	public String getPostDescription() {
		return postdescription;
	}

	public void setPostDescription(String postdescription) {
		this.postdescription = postdescription;
	}
}
