package pe.edu.upeu.apipersonasuniversitarias.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonaDto {

    private Integer idPersona;
    private String nombrePersona;
    private String apellidoPersona;
    private String dniPersona;
    private Date fechaNacimientoPersona;
    private String codigoPersona;
    private String fotoPersona;
}
