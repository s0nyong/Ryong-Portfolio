package com.code.bowwow.dao;

import java.util.ArrayList;
import java.util.List;

import com.code.bowwow.entity.cart;
import com.code.bowwow.entity.cart_detail;
import com.code.bowwow.entity.order;
import com.code.bowwow.entity.order_detail;
import com.code.bowwow.entity.product;
import com.code.bowwow.entity.review;
import com.code.bowwow.entity.user;

public interface bowwowDAO {

	public List<user> getUser(String userEmail);
	
	public List<product> getProducts();

	public List<product> searchProducts(String theName, String search);

	public List<product> detailProducts(int proNum);
	
	public List<review> getReviews(int proNum);
	
	public List<product> vipProducts();
	
	public List<product> saleProducts();
	
	public void saveOrder(order theOrder);
	
	public order orderNum(order o);
	
	public void savaOrderDetail(order_detail theOd);

	public void saveUser(user theUser);
	
	public void addReview(review review);

	public List<order_detail> orderlist(String order);
	
	public void cancelOrder(ArrayList<Integer> theOrderNums);
	
	public List<order_detail> cancelList(ArrayList<Integer> tt);
	
	public double calAverageScore (int productNum);
	
	public List<Integer> MyOrdrNums(String theEmail);
	
	public int getCartNum(String userEmail);
	   
	public List<cart_detail> getUserCart(int cartNum);
	
	public void avgScore(double averageScore, int proNum);
	
	public void minusStock(int proCount, String proName);
	
	public void plusStock(int proCount, String proName);

	public List<product> getProductsDESC();
	
	public List<order_detail> orderlist2(String order);

	public void createCart(cart cart);
	
	public void addCartDetail(cart_detail cart_detail);
	
	public void deleteCartItem(ArrayList<Integer> theCartNums);
	
	public void reviewDelete(review review);
	
	public void reviewModify(review review);
	
	public List<cart_detail> getBuyList(ArrayList<Integer> theCartNums);
	
	public List<user> masteruser();
	
	public void usergrade_update(String grade,String useremail);

}
