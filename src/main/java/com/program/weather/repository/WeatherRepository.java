package com.program.weather.repository;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long>{
	WeatherEntity findByWeatherId(Long id);
	WeatherEntity findByNameCity(String nameCity);
	Boolean existsByNameCity(String nameCity);
	List<WeatherEntity> findAllByUserEntities(UserEntity userEntity);
	List<WeatherEntity> findAllByDate(Timestamp ts);
	Long countAllByNameCity(String nameCity);
	
	
	
}
