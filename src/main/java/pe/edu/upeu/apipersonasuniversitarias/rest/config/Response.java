package pe.edu.upeu.apipersonasuniversitarias.rest.config;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class Response {
    private Message message;
    private Object data;
}
