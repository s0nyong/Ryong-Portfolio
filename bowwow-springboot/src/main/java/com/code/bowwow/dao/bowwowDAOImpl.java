package com.code.bowwow.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.bowwow.entity.cart;
import com.code.bowwow.entity.cart_detail;
import com.code.bowwow.entity.order;
import com.code.bowwow.entity.order_detail;
import com.code.bowwow.entity.product;
import com.code.bowwow.entity.review;
import com.code.bowwow.entity.user;

@Repository
public class bowwowDAOImpl implements bowwowDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	   public List<user> getUser(String userEmail) {
	      
	      Session curSession = sessionFactory.getCurrentSession();
	      Query<user> theQuery = curSession.createQuery("FROM user where email=:userEmail", user.class);
	      theQuery.setParameter("userEmail",userEmail);
	      List<user> theUser = theQuery.getResultList();
	      return theUser;
	   }
	
	
	@Override
	public List<product> getProducts() {
		Session curSession = sessionFactory.getCurrentSession();
		Query<product> theQuery = curSession.createQuery("FROM product", product.class);
		System.out.println(theQuery);
		List<product> theProduct = theQuery.getResultList();
		return theProduct;
	}
	
	@Override
	public List<product> searchProducts(String theName, String search) {
		Session curSession = sessionFactory.getCurrentSession();
		StringBuilder sb = new StringBuilder("From product where ");
		sb.append(search);
		sb.append(" Like :searchName");
		
		theName = theName.replace(" ","");
		Query<product> theQuery = null;
		
		if(theName != null && theName.length() > 0) {
		theQuery = curSession.createQuery(sb.toString(), product.class);
		theQuery.setParameter("searchName", "%" + theName + "%");
		}else {
			theQuery = curSession.createQuery("FROM product", product.class);
		}
		List<product> theProduct = theQuery.getResultList();
		
		return theProduct;
	}
	
	@Override
	public List<product> detailProducts(int proNum) {
		Session curSession = sessionFactory.getCurrentSession();
		Query<product> theQuery = curSession.createQuery("FROM product where product_num = :productNum", product.class);
		theQuery.setParameter("productNum", proNum);
		List<product> theProduct = theQuery.getResultList();
		return theProduct;
	}
	
	@Override
	public List<review> getReviews(int proNum) {
		Session curSession = sessionFactory.getCurrentSession();
		Query<review> theQuery = curSession.createQuery("FROM review where product_num = :productNum", review.class);
		theQuery.setParameter("productNum", proNum);
		List<review> thereview = theQuery.getResultList();
		return thereview;
	}
	
	@Override
	public List<product> vipProducts() {
		Session curSession = sessionFactory.getCurrentSession();
		Query<product> theQuery = curSession.createQuery("FROM product where product_discount = 0.4", product.class);
		List<product> theProduct = theQuery.getResultList();
		return theProduct;
	}
	
	@Override
	public List<product> saleProducts() {
		Session curSession = sessionFactory.getCurrentSession();
		Query<product> theQuery = curSession.createQuery("FROM product where product_discount = 0.3", product.class);
		List<product> theProduct = theQuery.getResultList();
		return theProduct;
	}
	

	@Override
	public void saveOrder(order theOrder){

		Session curSession = sessionFactory.getCurrentSession();
		
		curSession.saveOrUpdate(theOrder);
		System.out.println(11);
		
	}
	
	@Override
	public void saveUser(user theUser){

		Session curSession = sessionFactory.getCurrentSession();
		
		curSession.saveOrUpdate(theUser);
		System.out.println(11);
		
	}
	
	@Override
	public order orderNum(order o) {
		
		System.out.println(1);
		Session curSession = sessionFactory.getCurrentSession();
		Query<order> theQuery = curSession.createQuery("FROM order where email =:Order ORDER by day_time desc",order.class);
		theQuery.setMaxResults(1);
		theQuery.setParameter("Order", o.getEmail());
		
		return theQuery.getSingleResult();
	}
	
	@Override
	public void savaOrderDetail(order_detail theOd) {
		Session curSession = sessionFactory.getCurrentSession();
		System.out.println(2);
		curSession.saveOrUpdate(theOd);
	}
	
	
	@Override
	   public void addReview(review review) {
	      Session curSession = sessionFactory.getCurrentSession();
	       curSession.saveOrUpdate(review);
	   }


	@Override
	public List<order_detail> orderlist(String order) {
		Session curSession = sessionFactory.getCurrentSession();
		Query<order_detail> theQuery = curSession.createQuery("FROM order_detail where order_num in "+order+"AND order_state = '결제완료'", order_detail.class);
		
		List<order_detail> theOrderDetail = theQuery.getResultList();
		
		return theOrderDetail ;
	}

	@Override
	public List<order_detail> orderlist2(String order) {
		Session curSession = sessionFactory.getCurrentSession();
		Query<order_detail> theQuery = curSession.createQuery("FROM order_detail where order_num in "+order+"AND order_state = '주문취소'", order_detail.class);
		
		List<order_detail> theOrderDetail = theQuery.getResultList();
		
		return theOrderDetail ;
	}


	@Override
	public void cancelOrder(ArrayList<Integer> theOrderNums) {
		Session curSession = sessionFactory.getCurrentSession();
		Query theQuery = curSession.createQuery("UPDATE order_detail SET order_state = :newState WHERE order_detail_num IN (:OrderNums)");
		theQuery.setParameter("newState", "주문취소");
		theQuery.setParameterList("OrderNums",theOrderNums);
		theQuery.executeUpdate();
		
	}
	@Override
	public List<order_detail> cancelList(ArrayList<Integer> tt){
		Session curSession = sessionFactory.getCurrentSession();
		Query <order_detail>theQuery = curSession.createQuery("FROM order_detail WHERE order_detail_num IN (:tt)",order_detail.class);
		theQuery.setParameterList("tt",tt);
		List<order_detail> theOD = theQuery.getResultList();
		return theOD;
	}
	
	@Override
	public double calAverageScore (int productNum) {
		double score; 
		try {
		Session curSession = sessionFactory.getCurrentSession();
		Query<Double> theQuery = curSession.createQuery("select avg(product_score) from review  where product_num=:product_num",Double.class);
		theQuery.setParameter("product_num", productNum);
		
		score = theQuery.getSingleResult();
		}catch(Exception e) {
			score =0.0;
		}
		return score;
	}
	
	@Override
	public List<Integer> MyOrdrNums(String theEmail){
		Session curSession = sessionFactory.getCurrentSession();
		Query<Integer> theQuery = curSession.createQuery("select order_num from order where email=:e",Integer.class);
		theQuery.setParameter("e", theEmail);
		
		return theQuery.getResultList();
	}
	
	@Override
	public int getCartNum(String userEmail) {
		int cartNum;
		try {
			Session curSession = sessionFactory.getCurrentSession();
			Query<cart> theQuery = curSession.createQuery("FROM cart where user_email = :email", cart.class);
			theQuery.setParameter("email", userEmail);
			List<cart> theUserCart = theQuery.getResultList();
			cartNum = theUserCart.get(0).getCart_num();
			
		}catch(Exception e) {
			cartNum = 0;
		}
		return cartNum;
	}

	@Override
	public List<cart_detail> getUserCart(int cartNum) {
		Session curSession = sessionFactory.getCurrentSession();
		Query<cart_detail> theQuery = curSession.createQuery("FROM cart_detail where cart_num = :cartNum ",
				cart_detail.class);
		theQuery.setParameter("cartNum", cartNum);
		List<cart_detail> theUserCartDetail = theQuery.getResultList();
		return theUserCartDetail;

	}
	@Override
	public void avgScore(double averageScore, int proNum) {
		Session curSession = sessionFactory.getCurrentSession();
		Query theQuery = curSession.createQuery("UPDATE product SET product_score= :avgScore WHERE product_num = :productNum");
		theQuery.setParameter("avgScore", averageScore);
		theQuery.setParameter("productNum", proNum);
		
		theQuery.executeUpdate();
	}


	@Override
	public void minusStock(int proCount, String proName) {
		Session curSession = sessionFactory.getCurrentSession();
		Query theQuery = curSession.createQuery("UPDATE product SET product_stock= product_stock -:proC WHERE product_name = :proN");
		theQuery.setParameter("proC", proCount);
		theQuery.setParameter("proN", proName);
		
		theQuery.executeUpdate();
	}
	
	
	
	@Override
	public void plusStock(int proCount, String proName) {
		Session curSession = sessionFactory.getCurrentSession();
		System.out.println(proCount+","+ proName);
		Query theQuery = curSession.createQuery("UPDATE product SET product_stock= product_stock +:proC WHERE product_name = :proN");
		theQuery.setParameter("proC", proCount);
		theQuery.setParameter("proN", proName);
		
		theQuery.executeUpdate();
	}
	
	@Override
	public List<product> getProductsDESC() {
		Session curSession = sessionFactory.getCurrentSession();
		Query<product> theQuery = curSession.createQuery("FROM product WHERE product_discount=1.0 order BY product_score desc", product.class);
		System.out.println(theQuery);
		List<product> theProduct = theQuery.getResultList();
		return theProduct;
	}
	
	@Override
	public void createCart(cart cart) {
		Session curSession = sessionFactory.getCurrentSession();
		curSession.saveOrUpdate(cart);
	}


	@Override
	public void addCartDetail(cart_detail cart_detail) {
		Session curSession = sessionFactory.getCurrentSession();
		curSession.saveOrUpdate(cart_detail);
		
	}


	@Override
	public void deleteCartItem(ArrayList<Integer> theCartNums) {
		Session curSession = sessionFactory.getCurrentSession();
		Query theQuery = curSession.createQuery("DELETE FROM cart_detail WHERE cart_detail_num IN (:theCartNums)");
		theQuery.setParameterList("theCartNums", theCartNums);
		theQuery.executeUpdate();
	}
	
	@Override
	public void reviewDelete(review review) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(review);
		
	}
	
	public void reviewModify(review review) { 
	Session currentSession = sessionFactory.getCurrentSession(); 
	currentSession.saveOrUpdate(review);
	}


	@Override
	public List<cart_detail> getBuyList(ArrayList<Integer> theCartNums) {
		Session curSession = sessionFactory.getCurrentSession();
		Query<cart_detail> theQuery = curSession.createQuery("FROM cart_detail WHERE cart_detail_num IN (:cartDetailNums)", cart_detail.class);
		theQuery.setParameterList("cartDetailNums", theCartNums);
		List<cart_detail> theCartList = theQuery.getResultList();
		return theCartList;
	}
	
	public List<user> masteruser(){
		Session curSession = sessionFactory.getCurrentSession();
		Query<user> theQuery = curSession.createQuery("FROM user", user.class);
		List<user> theuser = theQuery.getResultList();
		return theuser;
	}
	
	public void usergrade_update(String grade,String useremail) {
		Session curSession = sessionFactory.getCurrentSession();
		String hql = "UPDATE user SET grade=:a WHERE email =:b";
		Query query = curSession.createQuery(hql);
		query.setParameter("a", grade);
		query.setParameter("b", useremail);
		query.executeUpdate();
	}
}
