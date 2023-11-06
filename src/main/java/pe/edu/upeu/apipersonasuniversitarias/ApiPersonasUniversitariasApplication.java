package pe.edu.upeu.apipersonasuniversitarias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiPersonasUniversitariasApplication {

		public String PORT = System.getenv("PORT");
	public String MYSQL_URL = System.getenv("MYSQL_URL");
	public String MYSQL_USERNAME = System.getenv("MYSQL_USERNAME");
	public String MYSQL_PASSWORD = System.getenv("MYSQL_PASSWORD");

	public static void main(String[] args) {
		SpringApplication.run(ApiPersonasUniversitariasApplication.class, args);
	}

}
