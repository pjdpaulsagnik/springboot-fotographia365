package com.techgeeknext.controller;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techgeeknext.interfaces.DatabaseFileServiceInterface;
import com.techgeeknext.model.DatabaseFile;
import com.techgeeknext.model.UserDao;
import com.techgeeknext.payload.Response;
import com.techgeeknext.repository.UserRepository;
import com.techgeeknext.service.DatabaseFileService;

@RestController
@CrossOrigin("http://localhost:4200")
public class FileUploadController {

    @Autowired
    private DatabaseFileServiceInterface fileStorageService;
    
    @Autowired
	UserRepository userRepository;

    @PostMapping("/uploadFile/{userid}")
    public Response uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String userid,@RequestParam("posttitle") String posttitle,@RequestParam("postdescription") String postdescription )throws FileUploadException {
    	DatabaseFile fileName = fileStorageService.storeFile(file, userid, posttitle, postdescription);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }
    
    @GetMapping
	@RequestMapping("/userforpost/{username}")
    public UserDao findUser(@PathVariable String username)
    {
		System.out.println(username);
    	UserDao retUsr = new UserDao();
    	List<UserDao> userList = null;
    	userList = (List<UserDao>) userRepository.findAll();
    	if (userList.size() > 0)
    	{
    		for(UserDao usr : userList)
    		{
    			if(usr.getUsername().equals(username) )
    			{
    				retUsr.setUserID(usr.getUserID());
    				return retUsr;
    			}
    		}
    	}
    	return null;
    }
    
}
