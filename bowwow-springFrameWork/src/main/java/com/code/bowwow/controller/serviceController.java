package com.code.bowwow.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.bowwow.entity.cart;
import com.code.bowwow.entity.cart_detail;
import com.code.bowwow.entity.order;
import com.code.bowwow.entity.order_detail;
import com.code.bowwow.entity.product;
import com.code.bowwow.entity.review;
import com.code.bowwow.entity.user;
import com.code.bowwow.service.bowwowService;

@Controller
public class serviceController {
	
	@Autowired
	private bowwowService bowwowService;
	
	//메인 페이지
	@GetMapping("/")
	public String showHome(Model theModel) {
		List<product> theProducts = bowwowService.getProductsDESC(); //평점 높은 제품 노출
		theModel.addAttribute("products",theProducts);
		return "home";
	}
	
	//================================================================제품 노출 페이지==================================================================================
	

	//제품검색
	@GetMapping("/search")
	public String SearchProduct(@RequestParam("searchName") String theName, String search, Model theModel) {
		List<product> theProducts = bowwowService.searchProducts(theName, search); //theName을 입력받아서 제품이름 검색
		theModel.addAttribute("products", theProducts);
	return "shop/product_list";
	}
	
	//회원등급 vip전용 상품 페이지
	@GetMapping("/vip")
	public String vipProduct(Model theModel) {
		List<product> theProducts = bowwowService.vipProducts();
		theModel.addAttribute("products", theProducts);
	return "shop/vip";
	}
	
	//세일중인 상품 페이지
	@GetMapping("/sale")
	public String saleProduct(Model theModel) {
		List<product> theProducts = bowwowService.saleProducts();
		theModel.addAttribute("products", theProducts);
	return "shop/sale";
	}
	
	//===================================================================== 제품 상세페이지 + 페이지 내 리뷰출력 ==================================================================
	@GetMapping("/detail")
	public String detailProduct(@RequestParam("productNum") int proNum, Principal p, Model theModel) {
		List<product> theProducts = bowwowService.detailProducts(proNum); //제품번호로 제품상세 출력
		List<review> thereview = bowwowService.getReviews(proNum); //제품번호로 리뷰목록 출력
		String userName = bowwowService.getUser(p.getName()).get(0).getUserName(); //로그인id(email)로 닉네임 받아와서 리뷰작성자와 비교용도
		theModel.addAttribute("username",userName);
		theModel.addAttribute("products", theProducts);
		theModel.addAttribute("reviews", thereview);

	return "shop/detail";
	}
	
	//===================================================================== 상품구매 및 장바구니/구매내역 ==================================================================
	
	//상품 구매 후 구매내역으로 이동
	@GetMapping("/add")
	public String order(@RequestParam("product_count")int proCount, @RequestParam("product_name")String proName, order_detail s, Principal p, Model theModel) {
		order o = new order(p.getName());
		bowwowService.saveOrder(o); //새로운 주문(번호)생성
		s.setOrder_num(bowwowService.orderNum(o)); 
		bowwowService.savaOrderDetail(s); //새로 생성된 주문번호에 세부 제품추가
		
		List<order_detail> theOD = bowwowService.myOrderlist(p.getName()); //사용자의 구매내역 출력
		theModel.addAttribute("orderlist",theOD);
		bowwowService.minusStock(proCount,proName); //구매한 제품의 재고량 업데이트 --
		
		return "redirect:/orderlist?";
	}
	
	//구매내역 페이지
	@GetMapping("/orderlist")
	public String orderList(Principal p, Model theModel) {
		List<order_detail> theOD = bowwowService.myOrderlist(p.getName()); //주문내역 출력
		theModel.addAttribute("orderlist",theOD);

		List<order_detail> theOD2 = bowwowService.myOrderlist2(p.getName()); //취소내역 출력
		theModel.addAttribute("orderlist2",theOD2);
		
		return "shop/history";
	}
	
	//구매내역 페이지 내 선택제품 주문 취소
	@GetMapping("/cancel")
	public String cancel(@RequestParam("order_detail_num") ArrayList<Integer> theOrderNums, Principal p, Model theModel) {
		bowwowService.cancelOrder(theOrderNums); //order_state 업데이트(결재완료 -> 주문취소)
		List<order_detail> canclelist = bowwowService.cancelList(theOrderNums);
		for(int i=0; i<canclelist.size(); i++) {
			bowwowService.plusStock(canclelist.get(i).getProduct_count(),canclelist.get(i).getProduct_name()); // 주문 취소한제품 각각 재고량 업데이트 ++
		}
		List<order_detail> theOD = bowwowService.myOrderlist(p.getName()); //주문내역 출력
		theModel.addAttribute("orderlist",theOD);

		List<order_detail> theOD2 = bowwowService.myOrderlist2(p.getName()); //취소내역 출력
		theModel.addAttribute("orderlist2",theOD2);
		
		return"shop/history";
	}
	
	//장바구니페이지
	@GetMapping("/cart")
	public String userCart(Principal p, cart cart, Model theModel) {
		int cart_num = bowwowService.getCartNum(p.getName()); //사용자의 장바구니 index(cart_num) 조회
		if(cart_num != 0) { //장바구니 생성여부 확인 후 장바구니가 있을때
			cart.setCart_num(cart_num);
			List<cart_detail> cart_detail = bowwowService.getUserCart(cart.getCart_num()); //장바구니에 담긴 제품 세부 받아오기
			if(cart_detail.isEmpty()) {
				theModel.addAttribute("users_cart", "null"); //장바구니가 비어있을경우 null출력
			}else {
				theModel.addAttribute("users_cart", cart_detail); //장바구니가 비어있지 않을경우 제품이름, 수량, 가격 출력
			}
		}else if(cart_num == 0) { //장바구니 생성여부 확인 후 장바구니가 없을때
			theModel.addAttribute("users_cart", "null"); //null 출력
		}
		return "shop/user_cart";
	}
	
	//장바구니에 추가
	@GetMapping("/add_cart")
	public String addCart(cart_detail cart_detail, Principal p, cart cart, Model theModel) {
		int cart_num = bowwowService.getCartNum(p.getName());  //사용자의 장바구니 index 조회
		if(cart_num != 0) {  //장바구니 생성여부 확인 후 장바구니가 있을때
			cart_detail.setCart_num(cart_num);
			bowwowService.addCartDetail(cart_detail); //장바구니에 추가할 제품 정보(제품이름, 수량, 가격) 입력
		}else if(cart_num == 0) {  //장바구니 생성여부 확인 후 장바구니가 없을때
			cart.setUser_mail(p.getName()); 
			bowwowService.createCart(cart); //장바구니 생성(새로운 cart_num생성)
			cart_detail.setCart_num(cart_num);
			bowwowService.addCartDetail(cart_detail); //장바구니에 추가할 제품 정보(제품이름, 수량, 가격) 입력
		}
		List<cart_detail> theCart = bowwowService.getUserCart(cart_num); //장바구니내역 출력
		theModel.addAttribute("users_cart",theCart);
		
		return "redirect:/cart?";
	}
	
	//장바구니 내 제품 삭제
	@GetMapping("/delete_cart")
	public String deleteCart(@RequestParam("cart_detail_num") ArrayList<Integer> theCartDetailNums) {
		bowwowService.deleteCartItem(theCartDetailNums); //선택한 장바구니 상세 index(cart_detail_num)의 제품 삭제
		return "redirect:/cart?";
	}
	
	//장바구니 내 제품 구매
	@GetMapping("/buy_cart")
	public String buy_cart(@RequestParam("cart_detail_num") ArrayList<Integer> theCartDetailNums, order_detail order_detail, Principal p, Model theModel) {
		order o = new order(p.getName());
		bowwowService.saveOrder(o); //새로운 주문(번호)생성
		List<cart_detail> addNewOrder = bowwowService.getBuyList(theCartDetailNums); //장바구니에서 선택한 제품들의 제품정보 받아기
		for(int i=0; i < addNewOrder.size(); i++) {
			order_detail.setOrder_num(bowwowService.orderNum(o));
			order_detail.setProduct_name(addNewOrder.get(i).getProduct_name());
			order_detail.setProduct_count(addNewOrder.get(i).getProduct_count());
			order_detail.setProduct_price(addNewOrder.get(i).getProduct_price());
			bowwowService.savaOrderDetail(order_detail); //받아온 제품정보를 각각 새로 생성된 주문 번호로 구매내역에 저장
			bowwowService.minusStock(addNewOrder.get(i).getProduct_count(),addNewOrder.get(i).getProduct_name()); //구매한 제품의 재고량 업데이트 --
		}
		bowwowService.deleteCartItem(theCartDetailNums);
		List<order_detail> theOD = bowwowService.myOrderlist(p.getName()); //주문내역 페이지내 출력
		theModel.addAttribute("orderlist",theOD);
		List<order_detail> theOD2 = bowwowService.myOrderlist2(p.getName());  //취소내역 페이지내 출력
		theModel.addAttribute("orderlist2",theOD2);
		return "shop/history";
	}
	
	//======================================================================= 리뷰 관리 ====================================================================
	
	//리뷰추가
		@PostMapping("/review_add")
		public String review_add(Principal p, review review, product product) {
			List<user> theUser = bowwowService.getUser(p.getName());  //사용자의 닉네임 받아오기
			review.setUsername(theUser.get(0).getUserName());
			bowwowService.addReview(review); //입력받은 리뷰 및 평점 업데이트
			double averageScore = bowwowService.calAverageScore(product.getProduct_num()); //해당제품의 평점 출력
			bowwowService.avgScore(averageScore, product.getProduct_num()); //출력받은 제품의 평점 업데이트
		
			return "redirect:/detail?productNum="+review.getProduct_num();
		}
	
		//리뷰 삭제 및 수정
		   @GetMapping("/review_btn")
		   public String reviewDelete(Principal p,
		         @RequestParam(value="delete",required=false)String delete, //삭제
		         @RequestParam(value="update",required=false)String update, //수정
		         review review, product product, //리뷰객체가져오기위한셋팅
		         Model theModel) {

		      if(delete == "delete" || update == null || delete != null) {
		         bowwowService.reviewDelete(review);  //선택한 리뷰 삭제
		         double averageScore = bowwowService.calAverageScore(product.getProduct_num()); //해당제품의 평점 출력
		         bowwowService.avgScore(averageScore, product.getProduct_num()); //출력받은 제품의 평점 업데이트
		      }
		      else{
		         bowwowService.reviewModify(review); //선택한 리뷰 comment 수정
		      }
		      
		      return "redirect:/detail?productNum="+review.getProduct_num();
		   }
	
	
}
