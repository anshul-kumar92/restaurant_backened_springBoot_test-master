package com.stackroute.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.restaurant.model.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{

	void delete(Restaurant restaurant);
	
	List findAll();
		
	Restaurant findById(int id);
	
	Restaurant save(Restaurant restaurant);

}
