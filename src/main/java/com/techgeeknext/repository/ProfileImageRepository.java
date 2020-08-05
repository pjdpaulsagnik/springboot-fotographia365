package com.techgeeknext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techgeeknext.model.ProfileImage;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, String>{

}
