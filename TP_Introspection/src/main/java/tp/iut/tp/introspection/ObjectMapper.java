package tp.iut.tp.introspection;

import java.awt.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javafx.scene.shape.TriangleMesh;

public class ObjectMapper {
	
	public ObjectMapper() {
		
	}
	
	public String object2Json(Object anyObject) {
		
		if(anyObject == null) {
			return null;
		}
		
		final StringBuilder sb = new StringBuilder();
		
		if(anyObject instanceof String) {
			string2Json(anyObject, sb);
		}
		else if(anyObject instanceof Integer) {
			integer2Json(anyObject, sb);
		}
		else if(anyObject instanceof Boolean) {
			boolean2Json(anyObject, sb);
		}
		else if(anyObject instanceof ArrayList) {
			listObjects2Json((ArrayList<Object>)anyObject, sb);
		}
		else {
			object2Json(anyObject, sb);
		}
		
		return sb.toString();
		
	}
	
	//For String
	public void string2Json(Object fieldValue, StringBuilder sb) {
		sb.append("\"" + fieldValue + "\"");
	}
	
	//For Integer
	public void integer2Json(Object fieldValue, StringBuilder sb) {
		sb.append(fieldValue);
	}
	
	//For Boolean
	public void boolean2Json(Object fieldValue, StringBuilder sb) {
		sb.append(fieldValue);
	}	
	
	//For Objects
	public void object2Json(Object anyObject, StringBuilder sb) {
		sb.append("{");
		
		Class c = anyObject.getClass();
		Field[] field = c.getFields();
		
		for(int i = 0; i < field.length; i++) {
			Field f = field[i];
			Object fieldValue;
			
			try {
				fieldValue = f.get(anyObject);
			} catch(IllegalArgumentException | IllegalAccessException e) {
				continue;
			}
		
			if(i > 0) {
				sb.append(",");
			}
			sb.append(" \"" + f.getName() + "\":");
			sb.append(" \"" + fieldValue + "\"");
		}
		
		sb.append(" }");
	}

	//For List
	public void listObjects2Json(ArrayList<Object> list, StringBuilder sb) {
		sb.append("[ ");
		
		for(int i = 0; i < list.size(); i++) {
			object2Json(list.get(i), sb);
		}
		
		sb.append(" ]");
	}

	public <T> T json2Object(String json, Class<T> clss) {
		
		if(json == null || json == "") {
			return null;
		}
		
		if(clss.getSimpleName().equals("String")) {
			T object = stringToGeneralizeObject(json);
			return object;
		} else if(clss.getSimpleName().equals("Integer")) {
			T object = integerToGeneralizeObject(json);
			return object;
		} else if(clss.getSimpleName().equals("Animal") || clss.getSimpleName().equals("Thing")) {
			T object = null;
			object = objectToGeneralizeObject(json, clss);
			return object;
		}
		
		return null;
	}
	
	public <T> T stringToGeneralizeObject(String json) {
		String str = json.substring(1, json.length()-1);
		T object = (T)str;
		return object;
	}
	
	public <T> T integerToGeneralizeObject(String json) {
		Integer nb = Integer.valueOf(json); 
		T object = (T)nb;
		return object;
	}
	
	public <T> T objectToGeneralizeObject(String json, Class<T> clss) {
		
		String[] attributs = json.split(",");
		T object = null;
		try {
			object = clss.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
		for(int i = 0; i < attributs.length; i++) {
			String value = attributs[i].split(":")[1];
			Method method;
			try {
				method = clss.getMethod("setName", String.class);
				method.invoke(object, value.split("\"")[1].trim());
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
				
		}
		
		return object;
	}
}
