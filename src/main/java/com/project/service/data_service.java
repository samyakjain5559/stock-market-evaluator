package com.project.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.project.dao.data;

public class data_service {
	
    public ArrayList<data> process_request(String id,String envfile) throws IOException{
    	ArrayList<data> arr = new ArrayList<>();
		File file = new File(envfile);
	        BufferedReader br
	            = new BufferedReader(new FileReader(file));
	        String st;
	        while ((st = br.readLine()) != null) {
	        	if(st.contains(id)) {
	        		List<String> line_List = Arrays.asList(st.split(","));
	        		data userOne = new data();
	        		userOne.setQuarter(line_List.get(0));
	        		userOne.setStock(line_List.get(1));
	        		userOne.setDate(line_List.get(2));
	        		userOne.setOpen(line_List.get(3));
	        		userOne.setHigh(line_List.get(4));
	        		userOne.setLow(line_List.get(5));
	        		userOne.setClose(line_List.get(6));
	        		userOne.setVolume(line_List.get(7));
	        		userOne.setPercent_change_price(line_List.get(8));
	        		userOne.setPercent_change_volume_over_last_wk(line_List.get(9));
	        		userOne.setPrevious_weeks_volume(line_List.get(10));
	        		userOne.setNext_weeks_open(line_List.get(11));
	        		userOne.setNext_weeks_close(line_List.get(12));
	        		userOne.setPercent_change_next_weeks_price(line_List.get(13));
	        		userOne.setDays_to_next_dividend(line_List.get(14));
	        		userOne.setPercent_return_next_dividend(line_List.get(15));
	        		arr.add(userOne);
	        	}
	        }
        return arr;
    }
    
    public void add_request(String record,String envfile) throws IOException {
    	BufferedWriter out = new BufferedWriter(
                new FileWriter(envfile, true));
        out.write("\n"+record);
        out.close();
    }

	public void upload_request(String file) throws IOException {
		String name = "root";
   	    String password = "password";
   	    try{
   	    	Statement s = DriverManager.getConnection("jdbc:mysql://127.0.0.1/rbc-schema",name, password).createStatement();
   	    	File file_var = new File(file);
	        BufferedReader br
	            = new BufferedReader(new FileReader(file_var)); // receive data file path for bulk upload
	        String st;
	        while ((st = br.readLine()) != null) {
	        	int r = s.executeUpdate("INSERT INTO rbc-table values ('"+st+"');");  // each record added to database table
	        }
   	    }catch (SQLException e1) {
   	         e1.printStackTrace();
   	    }
	}
}
