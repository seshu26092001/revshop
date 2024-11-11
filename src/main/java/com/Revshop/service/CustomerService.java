package com.Revshop.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.Revshop.exception.CustomerNotFoundException;
import com.Revshop.models.Customer;
import com.Revshop.repos.CustomerRepository;
 
@Service
public class CustomerService {
 
	@Autowired CustomerRepository crepo;
	public List<Customer> allCustomers(){
		return crepo.findAll();
	}
	public Customer findByUserId(String userid) {
	    return crepo.findById(userid)
	        .orElseThrow(() -> new CustomerNotFoundException(userid));
	}
 
	
	public boolean updatePassword(String userid,String pwd,String opwd) {
		Customer c=findByUserId(userid);
		System.err.println(pwd);
		if(c.getPwd().equals(opwd)) {
			c.setPwd(pwd);
			crepo.save(c);
			return true;
		}
		return false;
	}
	public Customer ValidateLogin(String userid,String pwd) {
		Customer c=findByUserId(userid);
		if(c==null) {
			return null;
		}
		else  {
			if(c.getPwd().equals(pwd))
				return c;
			else 
				return null;
		}
	}
	public String VerifyUser(String userid) {
		if(findByUserId(userid)==null)
			return "ok";
		else
			return "no";
	}
	public Customer saveCustomer(Customer c) {
		return crepo.save(c);
	}
	public void changePassword(String userid,String pwd) {
		Customer c=findByUserId(userid);
		c.setPwd(pwd);
		crepo.save(c);
	}
	public boolean checkPassword(String userid,String old) {
		Customer c=findByUserId(userid);
		return c.getPwd().equals(old);
	}
}

