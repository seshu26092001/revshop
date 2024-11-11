package com.Revshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Revshop.models.Order;
import com.Revshop.models.OrderDetails;
import com.Revshop.models.Product;
import com.Revshop.repos.OrderDetailsRepository;

@Service
public class OrderDetailsService {

	@Autowired private OrderDetailsRepository odrepo;
	
	public List<OrderDetails> allItemsinOrder(int orderid){
		return odrepo.findByOrderid(orderid);
	}
	
	public void saveItem(OrderDetails od) {		
		odrepo.save(od);
	}
	
	public void deleteAllItems(int orderid) {
		odrepo.deleteByOrderid(orderid);
	}
}
