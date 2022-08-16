package poly.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.store.entity.Account;
import poly.store.service.AccountService;
import poly.store.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/order/checkout")
	public String checkout(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		Account ac = accountService.findById(username);
		model.addAttribute("ac", ac);
		return "order/index";
	}
	
	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String orderdetail(Model model,HttpServletRequest request, @PathVariable("id") Long id) {
		String username = request.getRemoteUser();
		Account ac = accountService.findById(username);
		model.addAttribute("ac", ac);
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}
}
