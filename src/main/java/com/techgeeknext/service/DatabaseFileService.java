package com.techgeeknext.service;

import java.io.IOException;

import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.techgeeknext.exception.FileNotFoundException;
import com.techgeeknext.exception.FileStorageException;
import com.techgeeknext.interfaces.DatabaseFileServiceInterface;
import com.techgeeknext.model.DatabaseFile;
import com.techgeeknext.repository.DatabaseFileRepository;

@Service
public class DatabaseFileService implements DatabaseFileServiceInterface{

    @Autowired
    private DatabaseFileRepository dbFileRepository;

    public DatabaseFile storeFile(MultipartFile file,String userid,String posttitle,String postdescription)throws FileUploadException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFile dbFile = new DatabaseFile();
            
            dbFile.setUserId(userid);
            dbFile.setPostTitle(posttitle);
            dbFile.setPostDescription(postdescription);
            dbFile.setFileName(fileName);
            dbFile.setFileType(file.getContentType());
            dbFile.setData(file.getBytes());
            
            dbFile = dbFileRepository.save(dbFile);
            return dbFile;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatabaseFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

	@Override
	public List<DatabaseFile> getPost(int userid) {
		
		return dbFileRepository.findByUserId(userid);
		
	}
	
	@Override
	public List<DatabaseFile> getallPost() {
		
		return dbFileRepository.findAll();
	}
}
