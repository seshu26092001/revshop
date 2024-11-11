package com.Revshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Revshop.exception.CartItemNotFoundException;
import com.Revshop.models.Cart;
import com.Revshop.repos.CartRepository;

@Service
public class CartService {
	
	@Autowired CartRepository crepo;
	@Autowired CustomerService csrv;
	@Autowired ProductService psrv;
	
	public List<Cart> findItemsByUserId(String userid){
		return crepo.findByUserid(userid);
	}
	
	public int getItemsinCart(String userid) {
		List<Cart> items=findItemsByUserId(userid);
		int count=0;
		for(Cart c : items) {
			count+=c.getQty();
		}
		return count;
	}
	
	public void emptyCart(String userid) {
		crepo.deleteByUserid(userid);
	}
	
	public void saveItem(Cart c) {
		c.setCustomer(csrv.findByUserId(c.getUserid()));
		c.setProduct(psrv.findProductById(c.getProdid()));
		crepo.save(c);
	}
	
	public boolean checkItem(Cart c) {
		c.setCustomer(csrv.findByUserId(c.getUserid()));
		c.setProduct(psrv.findProductById(c.getProdid()));
		List<Cart> items=findItemsByUserId(c.getCustomer().getUserid());
		return items.contains(c.getProduct());
	}
	
	public void deleteItem(int id) {
	    Cart cartItem = crepo.findById(id)
	        .orElseThrow(() -> new CartItemNotFoundException(id));
	    crepo.delete(cartItem);
	}

}
