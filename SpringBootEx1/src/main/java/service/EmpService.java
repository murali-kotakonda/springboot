package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Employee;

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
