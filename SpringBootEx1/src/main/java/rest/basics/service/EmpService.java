package rest.basics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpService {

	@Autowired
	private EmpDAO dao;
	
	public void process(Employee emp){
		dao.save(emp);
	}

	public void remove(String id) {
	}
}
