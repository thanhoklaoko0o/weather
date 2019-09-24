package com.program.weather.repository;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.CurrentWeatherEntity;
import com.program.weather.entity.UserEntity;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeatherEntity, Long>{
	CurrentWeatherEntity findByWeatherId(Long id);
	CurrentWeatherEntity findByNameCity(String nameCity);
	Boolean existsByNameCity(String nameCity);
	List<CurrentWeatherEntity> findAllByUserEntities(UserEntity userEntity);
	List<CurrentWeatherEntity> findAllByDate(Timestamp ts);
	Long countAllByNameCity(String nameCity);
	
	
	
}
