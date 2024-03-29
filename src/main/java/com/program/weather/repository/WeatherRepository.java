package com.program.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;

/**
 * Data Access Layer Weather with DB
 * 
 * @author NgocHung
 *
 */
@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

	WeatherEntity findByWeatherId(Long id);

	WeatherEntity findByNameCity(String nameCity);

	Boolean existsByNameCity(String nameCity);

	//Get list weather by user 
	List<WeatherEntity> findAllByUserEntity(UserEntity userEntity);

	//Get weather by user by group by name city by date DESC
	@Query(value=" SELECT * FROM weatherinfo WHERE create_by = ?1 and date in (SELECT max(date)"
				+" FROM weatherinfo WHERE create_by = ?1 GROUP BY name_city)"
				+" ORDER BY date desc", nativeQuery=true)
	List<WeatherEntity> findAllByUserByDateDesc (Long userId);

	//Get weather by user by group by name city by date ASC
	@Query(value=" SELECT * FROM weatherinfo WHERE create_by = ?1 and date in (SELECT min(date)" 
				+" FROM weatherinfo WHERE create_by = ?1 GROUP BY name_city)"
				+" ORDER BY date desc", nativeQuery=true)
	List<WeatherEntity> findAllByUserByDateAsc (Long userId);
}
