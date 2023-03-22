package com.bowwow.admin.order;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.bowwow.admin.user.UserService;
import com.bowwow.common.entity.OrderDetail;
import com.bowwow.common.entity.OrderStatus;
import com.bowwow.common.entity.User;

@Controller
public class OrderController {

	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/order")
	public String listOrder(Model model) {
		return listByPage(1, "id", "asc", null, model);
	}
	
	@GetMapping("/order/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum,@Param("sortField")String sortField,@RequestParam(name ="sortDir")String sortDir,@Param("keyword")String keyword,Model model) {
		Page<OrderDetail> page = orderService.listByPage(pageNum,sortField,sortDir,keyword);
		List<OrderDetail> listOrders = page.getContent();
		long startCount = (pageNum -1) * OrderService.ORDER_PER_PAGE + 1;
		long endCount = startCount + OrderService.ORDER_PER_PAGE -1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir",reverseSortDir);
		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("currentPage",pageNum);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("listOrders",listOrders);
		model.addAttribute("keyword" ,keyword);
		return "order/order";
	}
	
	
	
	@GetMapping("order/update")
	public String updateOrder(@RequestParam("theId") int theId, @RequestParam("updateStatus") OrderStatus updateStatus) {
		OrderDetail theOrderDetail = orderService.findById(theId);
		if(theOrderDetail.getStatus() == updateStatus) {
			
		}else {
			theOrderDetail.setStatus(updateStatus);
			theOrderDetail.setUpdateDate(LocalDateTime.now());
			orderService.save(theOrderDetail);
			if(updateStatus == OrderStatus.구매확정) {
				int theUserId = theOrderDetail.getOrder().getUser().getId();
				User theUser = userService.findById(theUserId);
				theUser.setPoint(theUser.getPoint() + (int) (theOrderDetail.getPrice() * userPointRate(theUser)));
				userService.save(theUser);
			}
		}
		
		return "redirect:/order";
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
}
