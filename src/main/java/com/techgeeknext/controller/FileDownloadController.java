package com.techgeeknext.controller;

import java.util.List;



import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techgeeknext.model.DatabaseFile;
import com.techgeeknext.model.ProfileImage;
import com.techgeeknext.model.UserDao;
import com.techgeeknext.repository.ProfileImageRepository;
import com.techgeeknext.repository.UserRepository;
import com.techgeeknext.service.DatabaseFileService;
import com.techgeeknext.service.ProfileImageService;

@RestController
@CrossOrigin("http://localhost:4200")
public class FileDownloadController {

    @Autowired
    private DatabaseFileService fileStorageService;
    
    @Autowired
    private ProfileImageService profileImageService;

    @Autowired
	ProfileImageRepository profileimageRepository;
    
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        DatabaseFile databaseFile = fileStorageService.getFile(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }
    
    @GetMapping
    @RequestMapping("/postuser/{userid}")
	public List<DatabaseFile> getPost(@PathVariable int userid ) {
		return fileStorageService.getPost(userid);		
	}
    
    @GetMapping
    @RequestMapping("/postall")
	public List<DatabaseFile> getallPost() {
		return fileStorageService.getallPost();		
	}
    
    @GetMapping
    @RequestMapping("/profileimage/{username}")
	public ProfileImage getUserProfileImage(@PathVariable String username) {
    	System.out.println(username);
    	ProfileImage retUsr = new ProfileImage();
    	List<ProfileImage> userList = null;
    	userList = (List<ProfileImage>) profileimageRepository.findAll();
    	if (userList.size() > 0)
    	{
    		for(ProfileImage usr : userList)
    		{
    			if(usr.getUsername().equals(username) )
    			{
    				retUsr.setprofileimageid(usr.getprofileimageid());
    				retUsr.setFileName(usr.getFileName());
    				return retUsr;
    			}
    		}
    	}
    	return null;	
	}
    
    @GetMapping("/downloadProfileImage/{fileName:.+}")
    public ResponseEntity<Resource> ProfileImage(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        ProfileImage prfImage = profileImageService.getFile(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(prfImage.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + prfImage.getFileName() + "\"")
                .body(new ByteArrayResource(prfImage.getData()));
    }

}
