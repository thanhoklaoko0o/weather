package com.program.weather.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "weatherinfo")
public class WeatherEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weather_id")
	private Long weatherId;

	@Column(name = "icon")
	private String icon;

	@Column(name = "name_city")
	private String nameCity;

	@Column(name = "id_city")
	private String idCity;

	@Column(name = "date")
	private Timestamp date;

	@Column(name = "temp")   
	private String temp;

	@Column(name = "description")
	private String description;

	@Column(name = "wind")
	private String wind;

	@Column(name = "humidity")
	private String humidity;

	@Column(name = "pressure")
	private String pressure;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "create_by")
    private UserEntity userEntity;
}
