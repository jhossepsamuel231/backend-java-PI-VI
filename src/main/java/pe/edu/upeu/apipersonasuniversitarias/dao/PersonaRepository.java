package pe.edu.upeu.apipersonasuniversitarias.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.apipersonasuniversitarias.entity.PersonaEntity;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

    @Query("SELECT p FROM PersonaEntity p WHERE p.codigoPersona = :codigo")
    Optional<PersonaEntity> findByCodigo(@Param("codigo") String codigo);


}
