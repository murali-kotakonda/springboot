package mongo.relation;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Accounts  implements  Serializable {

	public Accounts() {
		super();
	}

	public Accounts(String accountName, String description) {
		super();
		this.accountName = accountName;
		this.description = description;
	}

	@Id
	private String id;
	
	private int accId;
	private String accountName;
	private String description;

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
}
