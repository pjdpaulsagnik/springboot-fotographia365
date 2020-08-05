package com.techgeeknext.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techgeeknext.exception.FileNotFoundException;
import com.techgeeknext.model.ProfileImage;
import com.techgeeknext.repository.ProfileImageRepository;

@Service
public class ProfileImageService {
	
	@Autowired
	private ProfileImageRepository profileImage;
	
	public ProfileImage getFile(String fileId) {
        return profileImage.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

}
