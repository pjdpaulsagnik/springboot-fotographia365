package com.techgeeknext.interfaces;
import java.io.File;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.techgeeknext.model.DatabaseFile;

public interface DatabaseFileServiceInterface {
	
	public DatabaseFile storeFile(MultipartFile file, String userid ,String posttitle,String postdescription) throws FileUploadException;

	public List<DatabaseFile> getPost( int userid );

	public List<DatabaseFile> getallPost();

}
