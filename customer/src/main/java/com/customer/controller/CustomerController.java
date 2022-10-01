package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.entity.Customer;
import com.customer.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<Customer> cus=customerService.findAll();
		model.addAttribute("customer",cus);
		return "customer-list";
		
	}
	
	@RequestMapping("/showFormForAdd")
	public String formToAdd(Model model) {
		
		
		Customer cus=new Customer();
		model.addAttribute("customer",cus);
		return "form";
		
	}
	
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId,Model theModel) {
		
		Customer cus=customerService.findById(theId);
		theModel.addAttribute("customer",cus);
		return "form";
	}
	
	
	@RequestMapping("/save")
	public String save(@RequestParam("id") int id,@RequestParam("fname") String fname,@RequestParam("lname") String lname,@RequestParam("mail") String mail) {
		
		
			Customer cus;
			
			if(id !=0) {
				cus=customerService.findById(id);
				cus.setFname(fname);
				cus.setLname(lname);
				cus.setMail(mail);
				}
			
			else
			cus=new Customer(id,fname,lname,mail);
			
			customerService.save(cus);
			
			return "redirect:/customer/list";
		
		
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		
		
		customerService.deleteById(id);
		
		return "redirect:/customer/list";
	}
}
