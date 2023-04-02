package com.bowwow.customer.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bowwow.common.entity.Cart;
import com.bowwow.common.entity.Order;
import com.bowwow.common.entity.OrderDetail;
import com.bowwow.common.entity.OrderStatus;
import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.cart.CartService;
import com.bowwow.customer.product.ProductService;
import com.bowwow.customer.user.UserService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private ProductService proService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
//---구매 상품 정보 불러오기---------------------------------------------------------------------------	
	@GetMapping("/order/buynow")
	public String buynow(@RequestParam(name="productId", required=false) Integer productId, @RequestParam(name="selectdItem", required=false) ArrayList<Integer> theCartId,
			@RequestParam(name="quantity", required=false) Integer quantity, Model model, Authentication auth, @ModelAttribute("user") User user) {
		
		int totalPrice = 0;     // 총금액
		int totalDiscount = 0;  // 총 할인액
		int finalPrice = 0;     // 총 결제금액
		
		if(auth == null) {
			return "redirect:/login";
		}
		
		//구매 유저 정보
		String userEmail = auth.getName();
		User theUser = userService.findByEmail(userEmail);
		
		//선택상품 목록
		List<Cart> selectedCarts = new ArrayList<>();

		if(productId != null && quantity != null && theCartId == null) { //상세 페이지 바로구매
			Product theProduct = proService.findById(productId);
			Cart tempCart = new Cart(0, theUser, theProduct, quantity);
			selectedCarts.add(tempCart);
			totalPrice += theProduct.getPrice() * quantity;
			totalDiscount += (int) ((theProduct.getPrice() * (theProduct.getDiscount())) * quantity);
		}else {                                                           //장바구니 구매
			for(int i = 0; i<theCartId.size(); i++) {
				Cart selectedCart = cartService.findById(theCartId.get(i));
				selectedCarts.add(selectedCart);
				totalPrice += ( selectedCart.getProduct().getPrice() * selectedCart.getCount() );
				totalDiscount += (int) (( selectedCart.getProduct().getPrice() * selectedCart.getProduct().getDiscount() ) * selectedCart.getCount());
				
			}
		} 
		
		model.addAttribute("user", theUser);
		//선택상품 목록
		model.addAttribute("selectedCarts", selectedCarts);
		//총상품금액
		model.addAttribute("totalPrice", totalPrice);	
		//총할인액
		model.addAttribute("totalDiscount", totalDiscount);	
		//최종결제금액
		finalPrice = totalPrice - totalDiscount;
		model.addAttribute("finalPrice", finalPrice);
		//사용가능 포인트
		model.addAttribute("availablePoint", theUser.getPoint());
		//사용자 등급
		model.addAttribute("userRole", theUser.getRole());
		//적립예상 포인트
		int newPoint = (int) (finalPrice * userPointRate(theUser));
		model.addAttribute("newPoint", newPoint);
	
//		int addPoint = theUser.getPoint() + newPoint;
//		theUser.setPoint(addPoint);
//		userService.save(theUser);
		return "buynow";
	}
	
	//등급별 적립률 반환
	public double userPointRate(User user) {
		String userRole = user.getRole();
		double pointRate = 0;
		switch(userRole) {
			case "일반": 
				pointRate = 0.03;
				break;
			case "우수":
				pointRate = 0.05;
				break;
			case "VIP":
				pointRate = 0.1;
				break;
			default :
				pointRate = 0.03;
				break;
		}
		return pointRate;
	}
	
@PostMapping("/order/save")
	public String saveOrders(@RequestParam(name="PurchaseProId") List<Integer> productId,
			@RequestParam(name="PurchaseProCount") List<Integer> quantity, 
			@RequestParam(name="PurchaseCartId") List<Integer> cartId,
			Model model, Authentication auth) throws Exception {
		//로그인한 유저의 정보 가져오기
		String userEmail = auth.getName();
		User theUser = userService.findByEmail(userEmail);

		//Order 테이블 추가, 주문하는 유저의 user_id 저장
		Order orderNow = new Order();
		orderNow.setUser(theUser);
		orderService.save(orderNow);
		
		for(int i=0; i<productId.size(); i++) {
			Product product = proService.findById(productId.get(i));
			int pricePerItem = (int) (product.getPrice() * quantity.get(i) * (1-product.getDiscount()));
			//OrderDetail 테이블 추가
			OrderDetail orderDetail = new OrderDetail(orderNow, product, quantity.get(i), pricePerItem, OrderStatus.배송준비);
			orderDetailService.save(orderDetail);
			
			//재고 차감시키기
			int stock = product.getStock() - quantity.get(i);
			product.setStock(stock);
			proService.save(product);
		}
		
		//구매한품목 장바구니에서 제거
		if(cartId.get(0) != 0) {
			cartService.deleteById(cartId);
		}
		
		
		return "buysuccess";
	}
	
	
	@GetMapping("/order/orderlist")
	   public String orderList(Model model, Authentication auth) {
	      
		
	      //로그인한 유저의 이름 가져와 보여주기
	      String userEmail = auth.getName();
	      User theUser = userService.findByEmail(userEmail);
	      
	      
	      
	      String userName = theUser.getName();
	      model.addAttribute("userName", userName);
	      
	      //로그인한 유저 id에 해당하는 order 테이블 리스트 뽑아오기
	      List<Order> orders = new ArrayList<>();
	      orders = orderService.findByUserId(theUser.getId());
	      System.err.println(orders);
	      model.addAttribute("orderlist", orders);
	      

	      return "/order/orderlist";
	   }
	
	@GetMapping("/order/updateStatus")
	public String updateOrderStatus(@RequestParam("status") String newStatus, 
			@RequestParam("orderDetailId") int orderDetailId,
			Model model) {	
		OrderDetail theOrderDetail = orderDetailService.findById(orderDetailId);
		if(newStatus.equals("confirm")) {
			theOrderDetail.setStatus(OrderStatus.구매확정);
			theOrderDetail.setUpdateDate(LocalDateTime.now());
			orderDetailService.save(theOrderDetail);
		}else if(newStatus.equals("cancel")) {
			theOrderDetail.setStatus(OrderStatus.환불요청);
			theOrderDetail.setUpdateDate(LocalDateTime.now());
			orderDetailService.save(theOrderDetail);
		}
		return "redirect:/order/orderlist";
	}
	
}
