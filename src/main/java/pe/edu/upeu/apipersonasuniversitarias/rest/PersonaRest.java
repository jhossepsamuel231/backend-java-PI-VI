package pe.edu.upeu.apipersonasuniversitarias.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.apipersonasuniversitarias.constantes.GlobalConstants;
import pe.edu.upeu.apipersonasuniversitarias.dto.PersonaDto;
import pe.edu.upeu.apipersonasuniversitarias.rest.config.Message;
import pe.edu.upeu.apipersonasuniversitarias.rest.config.Response;
import pe.edu.upeu.apipersonasuniversitarias.services.PersonaService;
import pe.edu.upeu.apipersonasuniversitarias.services.exception.ServiceException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin({"*"})
@RequestMapping(GlobalConstants.API_PERSONA)
public class PersonaRest {

    private PersonaService personaService;

    public PersonaRest(PersonaService personaService){
        super();
        this.personaService=personaService;
    }

    @GetMapping("/listarPersonas")
    public ResponseEntity<Response> getAllPerson(){
        try{
            List<PersonaDto> listPersonas = personaService.getAll(null);
            if (listPersonas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(
                    Response
                            .builder()
                            .message(Message.builder().code(1).message(GlobalConstants.MSG_LISTAR_EXITO).build())
                            .data(listPersonas)
                            .build());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/buscarPersona/{codigo}")
    public ResponseEntity<Response> findPersonById(@PathVariable String codigo){
        try{
            Optional<PersonaDto> optionalPersonaDto = personaService.findByCode(codigo);
            if (optionalPersonaDto.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(
                    Response
                            .builder()
                            .message(Message.builder().code(GlobalConstants.COD_MSG_EXITO).message(GlobalConstants.MSG_LISTAR_EXITO).build())
                            .data(optionalPersonaDto.get())
                            .build());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/agregarPersona")
    public ResponseEntity<Response> addPerson(@RequestBody PersonaDto personaDto){
        try {
            PersonaDto addNewPerson = personaService.save(personaDto);
            if (Objects.isNull(addNewPerson)) {
                return ResponseEntity
                        .noContent().build();
            }
            return ResponseEntity.ok(
                    Response.builder()
                            .message(Message.builder().code(GlobalConstants.COD_MSG_EXITO).message(GlobalConstants.MSG_REGISTRO_EXITO).build())
                            .data(addNewPerson)
                            .build()
            );
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/editarPersona/{id}")
    public ResponseEntity<Response> updatePersonById(@PathVariable int id, @RequestBody PersonaDto personaDto){
        try {
            PersonaDto updatePersona = personaService.update(id, personaDto);
            return ResponseEntity.ok(
                    Response.builder()
                            .message(Message.builder().code(GlobalConstants.COD_MSG_EXITO).message(GlobalConstants.MSG_UPDATE_EXITO).build())
                            .data(updatePersona)
                            .build()
            );
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/eliminarPersona/{id}")
    public ResponseEntity<Response> delete(@PathVariable int id) {
        try {
            personaService.delete(id);
            return ResponseEntity.ok(
                    Response.builder()
                            .message(Message.builder().code(GlobalConstants.COD_MSG_EXITO).message(GlobalConstants.MSG_DELETE_EXITO).build())
                            .build()
            );
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
