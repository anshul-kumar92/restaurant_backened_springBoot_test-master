package com.stackroute.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.restaurant.model.Restaurant;
import com.stackroute.restaurant.service.RestaurantService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping({ "/api" })
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping()
	@RequestMapping(value = "/restaurant", method = RequestMethod.POST)
	public Restaurant create(@RequestBody Restaurant restaurant) {
		return restaurantService.create(restaurant);
	}

	@RequestMapping(value = "/getall/{id}", method = RequestMethod.GET)
	public Restaurant findById(@PathVariable("id") int id) {
		return restaurantService.findById(id);
	}

	// @PutMapping
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public Restaurant update(@RequestBody Restaurant restaurant) {
		return restaurantService.update(restaurant);
	}

	// @DeleteMapping(path ={"/{id}"})
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Restaurant delete(@PathVariable("id") int id) {
		return restaurantService.delete(id);
	}

	// @GetMapping
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<?> findAll() {
		return restaurantService.findAll();
	}
}
