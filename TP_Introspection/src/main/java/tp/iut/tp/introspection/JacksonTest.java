package tp.iut.tp.introspection;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

import tp.iut.tp.introspection.data.Book;
import tp.iut.tp.introspection.data.Author;

import org.junit.Assert;
import org.junit.Test;

public class JacksonTest {

	ObjectMapper om = new ObjectMapper();
	
	@Test
	public void test_jackson_json_2_object() {
		//Given
		Book book = new Book();
		book.setTitle("Design Pattern");
		Author author = new Author();
		
		//When
		//String json = om.writeValueAsString(book);
		
		//Book book2 = om.readValue(json, Book.class);
		
		//Then
		//Assert.assertEquals("{\"title\":\"Design Pattern\"", json);
		//Assert.assertEquals(book.title, book2.title);
	}
}
