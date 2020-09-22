package com.abc.service;

import java.util.List;

import javax.sound.sampled.LineListener;

import com.abc.dto.Car;

public interface CarService {

	public String save(Car car);
	public Car findById(String model);
	public String deleteById(String model);
	public String update(Car car);
	
}
