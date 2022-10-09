package com.example.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.data;
import com.example.service.data_service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path="/JSON", produces="application/json")
public class rest_controller {
	
	@Value("${data.envfile}")
	String envfile;
	
	data_service service = new data_service();
	
	@GetMapping(value = "/data/{id}")
	public ArrayList<data> getdata(@PathVariable("id") String id) throws IOException{
		ArrayList<data> data = service.process_request(id,envfile); // read data file
		return data;
	}
	
	@GetMapping(value = "/addrecord/{record}")
	public void adddata(@PathVariable("record") String record) throws IOException{
		service.add_request(record.replaceAll("-", "/"),envfile); // update the data file
	}
	
	@GetMapping(value = "/bulkupload/{file}")
	public void uploaddata(@PathVariable("file") String file) throws IOException{
		service.upload_request(file); // upload file to database
	}
}
