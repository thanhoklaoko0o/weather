package com.program.weather.repository;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.CurrentWeatherEntity;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeatherEntity, Long>{
	CurrentWeatherEntity findByWeatherId(Long id);
	CurrentWeatherEntity findByNameCity(String nameCity);
	Boolean existsByNameCity(String nameCity);
	List<CurrentWeatherEntity> findAllByCreateBy(Long id);
	List<CurrentWeatherEntity> findAllByDate(Timestamp ts);
	
	
}
