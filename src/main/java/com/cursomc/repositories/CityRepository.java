package com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomc.domain.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
