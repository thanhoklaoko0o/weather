package com.program.weather.dto;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class MailDTO {

	private String from;
	private String to;
	private String subject;
	private Map<String, Object> model;
}
