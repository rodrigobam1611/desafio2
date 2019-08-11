package br.com.desafio.qa.api;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class TestAPI {
	
	private static final String ID_DO_FILME = "tt1285016";
	private static final String API_KEY = "52ec71bf";
	
	/**
	 * Validação do nome, ano e idioma do filme.
	 */
	@Test
	public void validarNomeAnoIdioma() {
		
		String uriBase = "http://www.omdbapi.com/";
			
		given()
		.relaxedHTTPSValidation()
		.param("i", ID_DO_FILME) //aqui passamos o parâmetro ID_DO_FILME para a URL
		.param("apikey", API_KEY) //aqui passamos o parâmetro API_KEY para a URL
		.when()
		.get(uriBase)
		.then()
		.statusCode(200) // O status http retornado foi 200
		.contentType(ContentType.JSON) // O response foi retornado no formato JSON
		.body("Title", equalTo("The Social Network")) //A chave "Title" contém o valor "The Social Network"
		.body("Year", equalTo("2010")) // a chave "Year" contém o valor "2010"
		.body("Language", equalTo("English, French")); //A chave "Language" contém os valores "English, French"
	}
	
	/**
	 * Validação de um busca por filme inexistente.
	 */
	@Test
	public void validarFilmeInexistente() {
		
		String uriBase = "http://www.omdbapi.com/";
			
		given()
		.relaxedHTTPSValidation()
		.param("t", "abcdd") //aqui passamos o parâmetro "t" para buscar um filme pelo título
		.param("apikey", API_KEY) //aqui passamos o parâmetro API_KEY para a URL
		.when()
		.get(uriBase)
		.then()
		.statusCode(200) // O status http retornado foi 200
		.contentType(ContentType.JSON) // O response foi retornado no formato JSON
		.body("Response", equalTo("False")) // a chave "Response" contém o valor "False"
		.body("Error", equalTo("Movie not found!"));//A chave "Error" contém o valor "Movie not found!"
		
	}
	
}
