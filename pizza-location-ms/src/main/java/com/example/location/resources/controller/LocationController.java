package com.example.location.resources.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;

import com.example.pizzalocation.model.external.City;
import com.example.pizzalocation.model.external.GetCityListResponse;

@Slf4j
@Controller
public class LocationController {

	protected MongoTemplate mongoTemplate;
	protected String collectionName;

	protected static Logger logger = LoggerFactory.getLogger(LocationController.class);

	public LocationController(@Value("${spring.data.mongodb.collection-name}")String collectionName, MongoTemplate mongoTemplate) {
		this.collectionName = collectionName;
		this.mongoTemplate = mongoTemplate;
	}
	
	public GetCityListResponse getCityList() {
		List<City> cityList;

		try {
			logger.debug("Trying to retrieve cities from Database");
			cityList = mongoTemplate.findAll(City.class, collectionName);

			if (cityList == null || cityList.isEmpty()) {
				throw new Exception("There are no cities in Database");
			}
			logger.debug("Successfully retrieved {} cities", cityList.size());

		} catch (Exception e) {
			logger.error("Database error when trying to retrieve cities. Error Message: {}", e.getMessage());
			cityList = new ArrayList<>();
		}

		return createResponse(cityList);
	}

	private GetCityListResponse createResponse(List<City> cityList) {
		GetCityListResponse response = new GetCityListResponse();
		response.setCityList(cityList);
		return response;
	}
}
