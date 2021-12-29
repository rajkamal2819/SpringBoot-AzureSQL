package com.hackathon.bial.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hackathon.bial.models.Restaurants;

public interface RestaurantRepository extends JpaRepository<Restaurants,Long> {
	
}
