package com.techgeeknext.service;

import com.techgeeknext.model.UserDao;

import com.techgeeknext.model.UserDto;
import com.techgeeknext.repository.ProfileImageRepository;
import com.techgeeknext.repository.UserRepository;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.techgeeknext.exception.FileStorageException;
import com.techgeeknext.model.CustomUser;
import com.techgeeknext.model.DatabaseFile;
import com.techgeeknext.model.ProfileImage;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private ProfileImageRepository profileImage;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserDao save(UserDto user) throws FileUploadException{
		UserDao newUser = new UserDao();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setfullname(user.getfullname());
		newUser.setEmail(user.getEmail());
		newUser.setbio(user.getbio());
		newUser.setusertype(user.getusertype());
		MultipartFile file = user.getfile();
		
		ProfileImage profiledata = new ProfileImage();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            
            profiledata.setUsername(user.getUsername());
            profiledata.setFileName(fileName);
            profiledata.setFileType(file.getContentType());
            profiledata.setData(file.getBytes());
            
            profiledata = profileImage.save(profiledata);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
		return userDao.save(newUser);
	}
}