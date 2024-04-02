package es.localhost.anunciaya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.localhost.anunciaya.models.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long>{

}
