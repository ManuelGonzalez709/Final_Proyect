package es.localhost.anunciaya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.localhost.anunciaya.models.DirEnvModel;

@Repository
public interface IDirEnvRepository extends JpaRepository<DirEnvModel, Long>{

}
