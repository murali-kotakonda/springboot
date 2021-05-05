package mongo.relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Customer implements Serializable {

	@Id
	private String id;
	private int custId;
	private String customerName;
	private int age;
	private List<Accounts> accounts;
	
	public List<Accounts> getAccounts() {
		if(accounts==null){
			accounts =  new ArrayList<Accounts>();
		}
		return accounts;
	}

	public Customer() {
		super();
	}

	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Customer [Id=" + custId + ", customerName=" + customerName
				+ ", age=" + age + "]";
	}

	public Customer( String customerName, int age) {
		super();
		this.customerName = customerName;
		this.age = age;
	}
	
}