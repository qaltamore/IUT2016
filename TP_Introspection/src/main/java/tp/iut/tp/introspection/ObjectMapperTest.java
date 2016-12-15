package tp.iut.tp.introspection;

import tp.iut.tp.introspection.data.Author;
import tp.iut.tp.introspection.data.Book;
import tp.iut.tp.introspection.data.Thing;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.Assert;

public class ObjectMapperTest {

	ObjectMapper om = new ObjectMapper();
	
	
	@Test
	public void test_string_are_return_as_quoted_in_json() {
		// Given
		// When
		String res = om.object2Json("Hello");
		
		// Then
		Assert.assertEquals("\"Hello\"", res);
	}
	
	@Test
	public void test_integers_are_literal_value_in_json() {
		// Given
		// When
		String res = om.object2Json(123);
		
		// Then
		Assert.assertEquals("123", res);
	}

	@Test
	public void test_boolean_true_is_literal_value_in_json() {
		// Given
		// When
		String res = om.object2Json(Boolean.TRUE);
		// list.add(book);
		
		// Then
		Assert.assertEquals("true", res);
	}

	@Test
	public void test_boolean_true_is_literal_value_in_json2() {
		// Given
		// When
		String res = om.object2Json(true);
		
		// Then
		Assert.assertEquals("true", res);
	}

	@Test
	public void test_boolean_false_is_literal_value_in_json() {
		// Given
		// When
		String res = om.object2Json(false);
		
		// Then
		Assert.assertEquals("false", res);
	}

	
	@Test
	public void test_objects_are_surrounded_with_curly_brace_in_json() {
		//Given
		Book book = new Book();
		book.setTitle("Being Fat At Java");
		Author author = new Author();
		author.setAuthor("Quentin", "Altamore");
		
		//When
		String resBook = om.object2Json(book);
		String resAuthor = om.object2Json(author);
		
		//Then
		String expected = "{ \"title\": \"Being Fat At Java\" }";
		Assert.assertEquals(expected, resBook);	
		
		String expected2 = "{ \"firstName\": \"Quentin\", \"lastName\": \"Altamore\" }";
		Assert.assertEquals(expected2, resAuthor);	
	}
	
	@Test
	public void test_list_objects_surrounded_by_curly_braces_is_surrounded_with_brackets_in_json() {
		//Given
		ArrayList<Object> list = new ArrayList<Object>();
		Author author = new Author();
		Book book = new Book();
		
		author.setAuthor("Quentin", "Altamore");
		book.setTitle("How To Become Strong In Java");
		
		list.add(author);
		list.add(book);
		
		//When
		String res = om.object2Json(list);
		
		//Then
		
		String expected = "[ " +
							"{ \"firstName\": \"Quentin\", \"lastName\": \"Altamore\" }" +
							"{ \"title\": \"How To Become Strong In Java\" } " +
							"]";
		Assert.assertEquals(expected, res);
	}

	@Test
	public void test_int_value_is_recognized_in_json() {
		//Given
		//When
		Integer res = (Integer) om.json2Object("123", Integer.class);
		
		//Then
		Assert.assertEquals(Integer.valueOf(123),  res);
	}
	
	@Test
	public void test_String_value_is_recognized_in_json() {
		//Given
		//When
		String res = om.json2Object("\"Hello\"", String.class);
		
		//Then
		Assert.assertEquals("Hello",  res);
	}
	
	@Test
	public void test_objects_are_recognized_with_their_types_in_json() {
		//Given
		String thingJson = "{ \"name\": \"Quentin\" }";
		
		//When
		Thing resThing = om.json2Object(thingJson, Thing.class);
		
		System.out.println(resThing);
		
		//Then
		try {
			Assert.assertEquals("Quentin", resThing.getClass().getMethod("getName", null).invoke(resThing, null));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}

}
