package rest1.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {
	
	@Value("${dbName}")
	String dbname;

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
	public void save(){
		System.out.println(dbname);
	}
}
