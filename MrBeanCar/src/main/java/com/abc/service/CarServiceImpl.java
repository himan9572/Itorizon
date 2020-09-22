package com.abc.service;

import java.util.List;

import com.abc.dao.CarDao;
import com.abc.dto.Car;
import com.abc.factory.CarDaoFactory;

public class CarServiceImpl implements CarService {

	@Override
	public String save(Car car) {
		
	     CarDao carDao=CarDaoFactory.getCarDao();
	  String status =   carDao.save(car);
		return status;
	}

	@Override
	public Car findById(String model) {
		
		CarDao carDao=CarDaoFactory.getCarDao();
		Car car=carDao.findById(model);
		return car;
	}

	@Override
	public String deleteById(String model) {
		CarDao carDao=CarDaoFactory.getCarDao();
		String status=carDao.deleteById(model);
		return status;
	}

	@Override
	public String update(Car car) {
		
		CarDao carDao=CarDaoFactory.getCarDao();
		String status=carDao.update(car);
		return status;
	}

}
