package pe.edu.upeu.apipersonasuniversitarias.services.impl;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.stereotype.Service;
import pe.edu.upeu.apipersonasuniversitarias.dao.PersonaRepository;
import pe.edu.upeu.apipersonasuniversitarias.dto.PersonaDto;
import pe.edu.upeu.apipersonasuniversitarias.entity.PersonaEntity;
import pe.edu.upeu.apipersonasuniversitarias.services.PersonaService;
import pe.edu.upeu.apipersonasuniversitarias.services.exception.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    private PersonaRepository personaRepository;
    private JsonMapper jsonMapper;

    public  PersonaServiceImpl(PersonaRepository personaRepository, JsonMapper jsonMapper){
        super();
        this.personaRepository = personaRepository;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public List<PersonaDto> getAll(PersonaDto personaDto) throws ServiceException {
        try {
            List<PersonaEntity> listPersonaEntity = personaRepository.findAll();
            return listPersonaEntity.stream().map(e -> this.getPersonaDto(e))
                    .collect(Collectors.toList());
        }catch (Exception error){
            throw new ServiceException(error);
        }
    }

    @Override
    public Optional<PersonaDto> findByCode(String codigo) throws ServiceException {
        try {
            Optional<PersonaEntity> personaEntity = personaRepository.findByCodigo(codigo);

            if (personaEntity.isPresent()) {
                return Optional.of(getPersonaDto(personaEntity.get()));
            } else {
                throw new ServiceException("Persona no Encontrada, Ingrese un código que sí exista en la base de datos");
            }

        } catch (Exception error) {
            throw new ServiceException(error.getMessage(), error);
        }
    }


    @Override
    public PersonaDto save(PersonaDto personaDto) throws ServiceException {
        try{
            PersonaEntity nuevaPersona = getPersonaEntity(personaDto);
            PersonaEntity saveNuevaPersona = personaRepository.save(nuevaPersona);
            return getPersonaDto(saveNuevaPersona);
        }catch (Exception error){
            throw new ServiceException(error);
        }
    }

    @Override
    public PersonaDto update(int id, PersonaDto personaDto) throws ServiceException {
        try{
            Optional<PersonaEntity> optionalPersonaEntity = personaRepository.findById(id);
            if (optionalPersonaEntity.isPresent()){
                PersonaEntity updatePersona = optionalPersonaEntity.get();
                updatePersona.setNombrePersona(personaDto.getNombrePersona());
                updatePersona.setApellidoPersona(personaDto.getApellidoPersona());
                updatePersona.setDniPersona(personaDto.getDniPersona());
                updatePersona.setFechaNacimientoPersona(personaDto.getFechaNacimientoPersona());
                updatePersona.setCodigoPersona(personaDto.getCodigoPersona());
                updatePersona.setFotoPersona(personaDto.getFotoPersona());

                PersonaEntity savePersona = personaRepository.save(updatePersona);
                return getPersonaDto(savePersona);
            }else {
                throw new ServiceException("Persona no encontrada, ingresar id de persona existente porfavor");
            }
        }catch (Exception error){
            throw new ServiceException(error);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            Optional<PersonaEntity> optionalPersonaEntity = personaRepository.findById(id);
            if (optionalPersonaEntity.isPresent()) {
                personaRepository.delete(optionalPersonaEntity.get());
            } else{
                throw new ServiceException("Persona no encontrada, ingresa otro id para eliminar la persona");
            }
        }catch (Exception error){
            throw new ServiceException(error);
        }
    }


    private PersonaDto getPersonaDto(PersonaEntity personaEntity){
        return jsonMapper.convertValue(personaEntity, PersonaDto.class);
    }

    private PersonaEntity getPersonaEntity(PersonaDto personaDto){
        return jsonMapper.convertValue(personaDto, PersonaEntity.class);
    }
}
