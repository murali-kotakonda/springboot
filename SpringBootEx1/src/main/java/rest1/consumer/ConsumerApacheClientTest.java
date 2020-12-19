package rest1.consumer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConsumerApacheClientTest {

	public  static <T> T getObject(Class<T> clazz,String jsonInString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		T t= null;
		//3.call method to read json and create obj
		try{
		t= (T)mapper.readValue(jsonInString, clazz);
		}catch(Exception exp){
			System.out.println(exp);
		}
		return t;
	}

	private static <T> String getJsonStr(T t) throws IOException,
			JsonGenerationException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		String inputData = mapper.writeValueAsString(t);
		return inputData;
	}

}
