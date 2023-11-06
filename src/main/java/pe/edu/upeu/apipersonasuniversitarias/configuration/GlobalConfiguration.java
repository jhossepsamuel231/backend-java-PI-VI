package pe.edu.upeu.apipersonasuniversitarias.configuration;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfiguration {

    public GlobalConfiguration(){

    }

    @Bean
    public JsonMapper getJsonMapper(){
    return  new JsonMapper();
    }

}
