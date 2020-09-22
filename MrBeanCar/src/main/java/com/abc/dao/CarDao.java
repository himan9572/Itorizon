package com.abc.dao;

import java.util.List;

import com.abc.dto.Car;

public interface CarDao {
	
	public String save(Car car);
	public Car findById(String model);
	public String deleteById(String model);
	public String update(Car car);
	
	

}
