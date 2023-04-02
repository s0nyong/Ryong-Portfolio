package com.bowwow.customer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bowwow.common.entity.User;
import com.bowwow.customer.user.UserService;

@RestController
public class OrderRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/order/getAddress")
	public String checkUnique(Authentication auth) {
		String userEmail = auth.getName();
		User theUser = userService.findByEmail(userEmail);
		String theAddress = theUser.getAddress();
		return theAddress;
		}
}
	
//	@PostMapping("/order/save")
//	public String saveOrders(@RequestParam(name="PurchaseProId") List<Integer> productId,
//	@RequestParam(name="PurchaseProCount") List<Integer> quantity,
//	@RequestParam(name="PurchaseCartId") List<Integer> cartId,
//	Model model, Authentication auth, @RequestParam("imp_uid") String impUid) throws Exception {
//	// 로그인한 유저의 정보 가져오기
//	String userEmail = auth.getName();
//	User theUser = userService.findByEmail(userEmail);
//
//	// Order 테이블 추가, 주문하는 유저의 user_id 저장
//	Order orderNow = new Order();
//	orderNow.setUser(theUser);
//	orderService.save(orderNow);
//
//	for(int i=0; i<productId.size(); i++) {
//	    Product product = proService.findById(productId.get(i));
//	    int pricePerItem = (int) (product.getPrice() * quantity.get(i) * (1-product.getDiscount()));
//	    // OrderDetail 테이블 추가
//	    OrderDetail orderDetail = new OrderDetail(orderNow, product, quantity.get(i), pricePerItem, OrderStatus.배송준비);
//	    orderDetailService.save(orderDetail);
//
//	    // 재고 차감시키기
//	    int stock = product.getStock() - quantity.get(i);
//	    product.setStock(stock);
//	    proService.save(product);
//	}
//
//	// 구매한 품목 장바구니에서 제거
//	if(cartId.get(0) != 0) {
//	    cartService.deleteById(cartId);
//	}
//
//	// 아임포트 REST API를 사용하여 결제 정보 확인
//	IamportClient client = new IamportClient("REST API KEY", "REST API SECRET");
//	Payment payment = client.paymentByImpUid(impUid).getResponse();
//	String status = payment.getStatus();
//
////	// 결제 승인이 되었을 경우, 주문 상태를 "결제완료"로 변경
////	if(status.equals("paid")) {
////	    orderNow.setStatus(OrderStatus.배송준비);
////	    orderService.save(orderNow);
////	}
//
//	return "buysuccess";
//}
//}
