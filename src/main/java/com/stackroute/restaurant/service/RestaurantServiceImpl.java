package com.stackroute.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.stackroute.restaurant.model.Restaurant;
import com.stackroute.restaurant.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired 
	private RestaurantRepository repo;

	@Override
	public Restaurant create(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return repo.save(restaurant);
	}

	@Override
	public Restaurant delete(int id) {
		// TODO Auto-generated method stub
		Restaurant restaurant = findById(id);
		if(restaurant != null) {
			repo.delete(restaurant);
		}
		return restaurant;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Restaurant findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Restaurant update(Restaurant restaurant) {
		// TODO Auto-generated method stub
		Restaurant updateRestaurant = repo.save(restaurant);
		return updateRestaurant;
	}

//	@Override
//	public Page<Restaurant> findAll(PageRequest pageRequest) {
//		// TODO Auto-generated method stub
//		return repo.findAll();
//	}
	
}
