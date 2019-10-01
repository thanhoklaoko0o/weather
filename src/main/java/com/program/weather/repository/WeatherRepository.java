package com.program.weather.repository;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long>{
	WeatherEntity findByWeatherId(Long id);
	WeatherEntity findByNameCity(String nameCity);
	Boolean 	existsByNameCity(String nameCity);
	List<WeatherEntity> findAllByUserEntities(UserEntity userEntity);
	List<WeatherEntity> findAllByDate(Timestamp ts);
	Long countAllByNameCity(String nameCity);
	
	@Query(value = "SELECT * \r\n" + 
			"FROM dbweather.user_weather a join dbweather.weatherinfo b on a.weather_id = b.weather_id\r\n" + 
			" where a.user_id=?1 and b.date \r\n" + 
			" in ( select max(date) \r\n" + 
			" FROM dbweather.user_weather a join dbweather.weatherinfo b on a.weather_id = b.weather_id\r\n" + 
			" where a.user_id=?1 group by b.name_city order by date desc ) \r\n" + 
			" group by b.name_city order by date desc", nativeQuery = true)
	List<WeatherEntity>findDateTimeByUserGroupbyDateTimeDest(long id);
	
	@Query(value = "SELECT * \r\n" + 
			"FROM dbweather.user_weather a join dbweather.weatherinfo b on a.weather_id = b.weather_id\r\n" + 
			" where a.user_id=?1 and b.date \r\n" + 
			" in ( select min(date) \r\n" + 
			" FROM dbweather.user_weather a join dbweather.weatherinfo b on a.weather_id = b.weather_id\r\n" + 
			" where a.user_id=?1 group by b.name_city order by date desc ) \r\n" + 
			" group by b.name_city order by date desc", nativeQuery = true)
	List<WeatherEntity>findDateTimeByUserGroupbyDateTimeAcs(long id);
	
}
