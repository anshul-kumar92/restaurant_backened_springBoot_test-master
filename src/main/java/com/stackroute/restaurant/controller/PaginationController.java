package com.stackroute.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.restaurant.service.RestaurantService;

@RestController
@CrossOrigin(origins = "http://localhost:8088", allowedHeaders = "*")
public class PaginationController {

	@Autowired
	private RestaurantService restaurantService;
	
//	@SuppressWarnings("deprecation")
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public Page<Restaurant> showPage(@RequestParam(defaultValue = "0") int page){
//		return restaurantService.findAll(new PageRequest(page, 4));
//	}	
}
