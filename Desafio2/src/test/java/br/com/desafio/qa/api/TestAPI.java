package br.com.desafio.qa.api;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class TestAPI {
	
	private static final String ID_DO_FILME = "tt1285016";
	private static final String API_KEY = "52ec71bf";
	
	/**
	 * Valida��o do nome, ano e idioma do filme.
	 */
	@Test
	public void validarNomeAnoIdioma() {
		
		String uriBase = "http://www.omdbapi.com/";
			
		given()
		.relaxedHTTPSValidation()
		.param("i", ID_DO_FILME) //aqui passamos o par�metro ID_DO_FILME para a URL
		.param("apikey", API_KEY) //aqui passamos o par�metro API_KEY para a URL
		.when()
		.get(uriBase)
		.then()
		.statusCode(200) // O status http retornado foi 200
		.contentType(ContentType.JSON) // O response foi retornado no formato JSON
		.body("Title", equalTo("The Social Network")) //A chave "Title" cont�m o valor "The Social Network"
		.body("Year", equalTo("2010")) // a chave "Year" cont�m o valor "2010"
		.body("Language", equalTo("English, French")); //A chave "Language" cont�m os valores "English, French"
	}
	
	/**
	 * Valida��o de um busca por filme inexistente.
	 */
	@Test
	public void validarFilmeInexistente() {
		
		String uriBase = "http://www.omdbapi.com/";
			
		given()
		.relaxedHTTPSValidation()
		.param("t", "abcdd") //aqui passamos o par�metro "t" para buscar um filme pelo t�tulo
		.param("apikey", API_KEY) //aqui passamos o par�metro API_KEY para a URL
		.when()
		.get(uriBase)
		.then()
		.statusCode(200) // O status http retornado foi 200
		.contentType(ContentType.JSON) // O response foi retornado no formato JSON
		.body("Response", equalTo("False")) // a chave "Response" cont�m o valor "False"
		.body("Error", equalTo("Movie not found!"));//A chave "Error" cont�m o valor "Movie not found!"
		
	}
	
}
