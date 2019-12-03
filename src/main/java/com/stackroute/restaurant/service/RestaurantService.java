package com.stackroute.restaurant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.stackroute.restaurant.model.Restaurant;

public interface RestaurantService {
	
	Restaurant create(Restaurant restaurant);
	public Restaurant delete(int id);
	public List findAll();
	Restaurant findById(int id);
	Restaurant update(Restaurant restaurant);
	//Page<Restaurant> findAll(PageRequest pageRequest);
}
