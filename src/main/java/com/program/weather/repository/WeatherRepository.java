package com.program.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.utils.Constants;

/**
 * Data Access Layer Weather with DB
 * 
 * @author USER
 *
 */
@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

	  WeatherEntity 		findByWeatherId(Long id); 
	  WeatherEntity 		findByNameCity(String nameCity);
	  Boolean 				existsByNameCity(String nameCity);
	  
	  //Get all weather by user 
	  List<WeatherEntity> 	findAllByUserEntity(UserEntity userEntity);
	  
	  //Get weather by User by group by name city by date DESC
	  @Query(value=Constants.QUERY_WEARTHER_DESC, nativeQuery=true)
	  List<WeatherEntity>   findAllByUserByDateDesc (UserEntity userEntity);
	  
	  //Get weather by User by group by name city by date ASC
	  @Query(value=Constants.QUERY_WEARTHER_ASC, nativeQuery=true)
	  List<WeatherEntity>   findAllByUserByDateAsc (UserEntity userEntity);

}
