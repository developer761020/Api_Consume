package com.consume.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.consume.api.model.*;

@Controller
public class Api_Controller {

	@RequestMapping("/api")
	@ResponseBody
	private String hello() {
		return "hello";
	}
	
	@RequestMapping("/user/{id}")
	private ModelAndView getuser(@PathVariable Integer id,Model model) {
		String uri= "https://jsonplaceholder.typicode.com/users/"+id;
		RestTemplate RT_ob= new RestTemplate();
		
		User user=RT_ob.getForObject(uri, User.class);
		Address address=user.getAddress();
		Geo geo=address.getGeo();
		Company cmpny=user.getCompany();
		
		ModelAndView m_a_v=new ModelAndView("user");
		m_a_v.addObject("user",user);
		m_a_v.addObject("address", address);
		m_a_v.addObject("geo", geo);
		m_a_v.addObject("cmp", cmpny);
		return m_a_v;
	}
}
