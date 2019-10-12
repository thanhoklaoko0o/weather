package com.program.weather.repository;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.utils.Constants;
/**
 * Data Access Layer Weather with DB
 * @author USER
 *
 */
@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long>{
	
	WeatherEntity 		findByWeatherId(Long id);
	WeatherEntity		findByNameCity(String nameCity);
	Boolean 	  		existsByNameCity(String nameCity);
	List<WeatherEntity> findAllByUserEntities(UserEntity userEntity);
	List<WeatherEntity> findAllByDate(Timestamp ts);
	Long 				countAllByNameCity(String nameCity);
	
	/**
	 * Query SQL Get List Weather By User By Unique Name City Order By Date DESC
	 * @param id
	 * @return
	 */
	@Query(value = Constants.QUERY_WEARTHER_DESC, nativeQuery = true)
	List<WeatherEntity>findDateTimeByUserGroupbyDateTimeDest(long id);
	
	/**
	 * Query SQL Get List Weather By User By Unique Name City Order By Date ASC
	 * @param id
	 * @return
	 */
	@Query(value = Constants.QUERY_WEARTHER_ASC, nativeQuery = true)
	List<WeatherEntity>findDateTimeByUserGroupbyDateTimeAcs(long id);
	
}
