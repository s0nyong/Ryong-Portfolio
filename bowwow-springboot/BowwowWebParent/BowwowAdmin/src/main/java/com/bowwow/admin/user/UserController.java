package com.bowwow.admin.user;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bowwow.common.entity.User;

@Controller
public class UserController {
	
	

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String listUsers(Model model) {
//		List<User> listUsers = userService.listAll();
//		model.addAttribute("listUsers", listUsers);
		return listByPage(1,"name","asc",null,model);
	}
	
	@GetMapping("/users/user_form")
	public String createUsers(User user,Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("user",new User());
		model.addAttribute("pageTitle", "Create User");
		model.addAttribute("listUsers", listUsers);
		return "users/user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUsers(@ModelAttribute("user")User user, RedirectAttributes redirectAttributes) throws IOException{
		userService.save(user);
		return "redirect:/users";
	}
	
	//유저 정보 수정
	@GetMapping("/users/edit/{id}")
	public String EditUser(@PathVariable("id")Integer id,Model model) {
		User user = userService.findById(id);
		List<User> listUsers = userService.listAll();
		model.addAttribute("user", user);
		model.addAttribute("pageTitle", "Edit User (ID : " + id + ")");
		model.addAttribute("listUsers", listUsers);
		return "users/user_form";
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id,Model model) {
		userService.findBydelete(id);
		return "redirect:/users";
	}
	@GetMapping("/users/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum,@Param("sortField")String sortField,@RequestParam(name ="sortDir")String sortDir,@Param("keyword")String keyword,Model model) {
		Page<User> page = userService.listByPage(pageNum,sortField,sortDir,keyword);
		List<User> listUsers = page.getContent();
		long startCount = (pageNum -1) * UserService.USERS_PER_PAGE + 1;
		long endCount = startCount + UserService.USERS_PER_PAGE -1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir",reverseSortDir);
		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("currentPage",pageNum);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("startCount",startCount);
		model.addAttribute("endCount",endCount);
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("listUsers",listUsers);
		model.addAttribute("keyword" ,keyword);
		return "users/users";
	}
}
