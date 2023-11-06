package pe.edu.upeu.apipersonasuniversitarias.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="persona")
public class PersonaEntity implements Serializable {

    @Id
    @Column(name = "id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    @Column(name = "nombre")
    private String nombrePersona;

    @Column(name = "apellido")
    private String apellidoPersona;

    @Column(name = "dni")
    private String dniPersona;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimientoPersona;

    @Column(name = "codigo")
    private String codigoPersona;

    @Column(name = "foto")
    private String fotoPersona;


}
