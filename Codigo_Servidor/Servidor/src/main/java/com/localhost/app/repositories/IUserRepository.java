package com.localhost.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localhost.app.models.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long>{
	

}
