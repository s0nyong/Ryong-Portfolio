package com.code.bowwow.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.bowwow.entity.cart;
import com.code.bowwow.entity.cart_detail;
import com.code.bowwow.entity.order;
import com.code.bowwow.entity.order_detail;
import com.code.bowwow.entity.product;
import com.code.bowwow.entity.review;
import com.code.bowwow.entity.user;
import com.code.bowwow.dao.bowwowDAO;

@Transactional
@Service
public class bowwowServiceImpl implements bowwowService{

	@Autowired
	private bowwowDAO bowwowDAO;
		
	@Override
	@Transactional
	public List<user> getUser(String userEmail) {
		// TODO Auto-generated method stub
		return bowwowDAO.getUser(userEmail);
	}
	
	@Override
	@Transactional
	public List<product> getProducts() {
		// TODO Auto-generated method stub
		return bowwowDAO.getProducts();
	}

	@Override
	@Transactional
	public List<product> searchProducts(String theName, String search) {
		// TODO Auto-generated method stub
		return bowwowDAO.searchProducts(theName, search);
	}
	
	@Override
	@Transactional
	public List<product> detailProducts(int proNum) {
		// TODO Auto-generated method stub
		return bowwowDAO.detailProducts(proNum);
	}

	@Override
	@Transactional
	public List<review> getReviews(int proNum) {
		// TODO Auto-generated method stub
		return bowwowDAO.getReviews(proNum);
	}	
	
	@Override
	@Transactional
	public List<product> vipProducts() {
		// TODO Auto-generated method stub
		return bowwowDAO.vipProducts();
	}
	
	@Override
	@Transactional
	public List<product> saleProducts() {
		// TODO Auto-generated method stub
		return bowwowDAO.saleProducts();
	}
	
	@Override
	@Transactional
	public void saveOrder(order theOrder) {
		bowwowDAO.saveOrder(theOrder);
		}
	
	@Override
	@Transactional
	public int orderNum(order o) {
		return bowwowDAO.orderNum(o).getOrder_num();
	}
	
	@Override
	@Transactional
	public void savaOrderDetail(order_detail theOd) {
		bowwowDAO.savaOrderDetail(theOd);
	}

	@Override
	@Transactional
	public void saveUser(user theUser) {
		bowwowDAO.saveUser(theUser);
	}

	@Override
	@Transactional
	public void addReview(review review) {
		
		bowwowDAO.addReview(review);
	}


	@Override
	@Transactional
	public void cancelOrder(ArrayList<Integer> theOrderNums) {
		
		bowwowDAO.cancelOrder(theOrderNums);
	}
	
	@Override
	public List<order_detail> cancelList(ArrayList<Integer> tt) {
		// TODO Auto-generated method stub
		return bowwowDAO.cancelList(tt);
	}

	@Override
	public double calAverageScore(int productNum) {
		
		return bowwowDAO.calAverageScore(productNum);
	}

	@Override
	public List<order_detail> myOrderlist(String theEmail) {
		List<Integer> MyOrdrNums = bowwowDAO.MyOrdrNums(theEmail);
		if(MyOrdrNums.isEmpty()) {
			return null;
		}else {
			String inString = bowwowDAO.MyOrdrNums(theEmail).toString().replace('[', '(').replace(']', ')');
			return bowwowDAO.orderlist(inString);
		}
	}

	@Override
	public List<order_detail> myOrderlist2(String theEmail) {
		List<Integer> MyOrdrNums = bowwowDAO.MyOrdrNums(theEmail);
		if(MyOrdrNums.isEmpty()) {
			return null;
		}else {
		String inString = bowwowDAO.MyOrdrNums(theEmail).toString().replace('[', '(').replace(']', ')');
		return bowwowDAO.orderlist2(inString);
		}
	}

	
	@Override
	@Transactional
	public int getCartNum(String userEmail) {
		// TODO Auto-generated method stub
		return bowwowDAO.getCartNum(userEmail);
	   }

	@Override
	@Transactional
	public List<cart_detail> getUserCart(int cartNum) {
		// TODO Auto-generated method stub
		return bowwowDAO.getUserCart(cartNum);
   }
	
	@Override
	@Transactional
	public void avgScore(double averageScore, int proNum) {
		// TODO Auto-generated method stub
		bowwowDAO.avgScore(averageScore,proNum);
   }

	@Override
	@Transactional
	public void minusStock(int proCount, String proName) {
		bowwowDAO.minusStock(proCount, proName);
		
	}

	@Override
	@Transactional
	public void plusStock(int proCount, String proName) {
		bowwowDAO.plusStock(proCount, proName);
		
	}

	@Override
	@Transactional
	public List<product> getProductsDESC() {
		// TODO Auto-generated method stub
		return bowwowDAO.getProductsDESC();
	}	
	
	@Override
	public void createCart(cart cart) {
		// TODO Auto-generated method stub
		bowwowDAO.createCart(cart);
	}
	
	@Override
	public void addCartDetail(cart_detail cart_detail) {
		// TODO Auto-generated method stub
		bowwowDAO.addCartDetail(cart_detail);
	}

	@Override
	public void deleteCartItem(ArrayList<Integer> theCartNums) {
		bowwowDAO.deleteCartItem(theCartNums);
	}
	
	@Override
	@Transactional
	public void reviewDelete(review review) {
		// TODO Auto-generated method stub
		bowwowDAO.reviewDelete(review);
	}

	
	@Override
	@Transactional 
	public void reviewModify(review review) { 
	  bowwowDAO.reviewModify(review); 
	  }

	@Override
	public List<cart_detail> getBuyList(ArrayList<Integer> theCartNums) {
		// TODO Auto-generated method stub
		return bowwowDAO.getBuyList(theCartNums);
	}
	
	@Override
	public List<user> masteruser(){
		return bowwowDAO.masteruser();
	}
	
	@Override
	@Transactional
	public void usergrade_update(String grade,String useremail) {
		bowwowDAO.usergrade_update(grade,useremail);
		
	}
}

