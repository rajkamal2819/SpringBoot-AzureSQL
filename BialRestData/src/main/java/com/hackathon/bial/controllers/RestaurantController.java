package com.hackathon.bial.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.bial.exceptions.ResourceNotFoundException;
import com.hackathon.bial.models.Restaurants;
import com.hackathon.bial.repository.RestaurantRepository;

@RestController
public class RestaurantController {
	

	@Autowired
	private RestaurantRepository restaurantRepository;

	@GetMapping("/restaurants")
	public java.util.List<Restaurants> getAllRestaurants(){
		return restaurantRepository.findAll();
	}
	
	@GetMapping("/restaurants/{id}")
	public ResponseEntity<Restaurants> getEmployeeById(@PathVariable(value = "id") Long restaurantId)
			throws ResourceNotFoundException {
		Restaurants restaurants = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurants not found for this id :: " + restaurantId));
		return ResponseEntity.ok().body(restaurants);
	}
	
	@PostMapping("/restaurants")
	public Restaurants createRestaurants(@RequestBody Restaurants restaurants) {
		return restaurantRepository.save(restaurants);
	}

	@PutMapping("/restaurants/{id}")
	public ResponseEntity<Restaurants> updateEmployee(@PathVariable(value = "id") Long restaurantId,
			 @RequestBody Restaurants restaurantDetails) throws ResourceNotFoundException {
		Restaurants restaurants = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for this id :: " + restaurantId));

		restaurants.setName(restaurantDetails.getName());
		restaurants.setDescription(restaurantDetails.getDescription());
		restaurants.setTimmings(restaurantDetails.getTimmings());
		restaurants.setLocation(restaurantDetails.getLocation());
		restaurants.setContact(restaurantDetails.getContact());
		restaurants.setEmail(restaurantDetails.getEmail());
		restaurants.setSecurity(restaurantDetails.getSecurity());
		
		final Restaurants updatedRestaurant = restaurantRepository.save(restaurants);
		return ResponseEntity.ok(updatedRestaurant);
	}

	@DeleteMapping("/restaurants/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long restaurantId)
			throws ResourceNotFoundException {
		Restaurants restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurants not found for this id :: " + restaurantId));

		restaurantRepository.delete(restaurant);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

     
	
	
}
