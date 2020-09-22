package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineListener;

import com.abc.dto.Car;
import com.abc.factory.ConnectionFactory;

public class CarDaoImpl implements CarDao {

	@Override
	public String save(Car car) {
		// TODO Auto-generated method stub
		String status ="";
	try {
		Connection connection=ConnectionFactory.getConnectionToDb();
		String sqlSelectQuery ="select * from `car` where `model`=?";
		PreparedStatement preparedStatement =null;
		
		preparedStatement=connection.prepareStatement(sqlSelectQuery);
		preparedStatement.setString(1, car.getModel());
		ResultSet resultSet = preparedStatement.executeQuery();
		

		if (resultSet.next() == true) {
			status = "alreadyExisted";

		} else {
			String sqlInsertQuery = "insert into `car`(`company`,`model`,`color`,`dateofpurchase`,`price`,`enginecapacity`,`licenceplatenumber`,`seatingcapacity`) values(?,?,?,?,?,?,?,?)";
			
			preparedStatement = connection.prepareStatement(sqlInsertQuery);
			preparedStatement.setString(1, car.getModel());
			preparedStatement.setString(2, car.getCompany());
			preparedStatement.setString(3, car.getColor());
			preparedStatement.setString(4, car.getDateofpurchase());
			preparedStatement.setString(5, car.getPrice());
			preparedStatement.setString(6, car.getEnginecapacity());
			preparedStatement.setString(7, car.getLicenceplatenumber());
			preparedStatement.setString(8, car.getSeatingcapacity());
			
			int rowCount = preparedStatement.executeUpdate();
			
			if (rowCount == 1) {
				status = "success";
			} else {
				status = "failure";
			}
			
		}
		} catch (SQLException e) {
		e.printStackTrace();
		status = "failure";
	}
	return status;
	}

	@Override
	public Car findById(String model) {
		
		Car car=null;
		try {
			Connection connection=ConnectionFactory.getConnectionToDb();
			String sqlSelectQuery ="select * from `car` where `model`=?";
			PreparedStatement preparedStatement =null;
			
			preparedStatement=connection.prepareStatement(sqlSelectQuery);
			preparedStatement.setString(1, model);
			ResultSet resultSet = preparedStatement.executeQuery();
			

			if (resultSet.next() == true) {
			
				car=new Car();
				// Keep the data obtained from ResultSet to Car DTO object
				car.setModel(resultSet.getString("model"));
				car.setCompany(resultSet.getString("company"));
				car.setColor(resultSet.getString("color"));
				car.setDateofpurchase(resultSet.getString("dateofpurchase"));
				car.setPrice(resultSet.getString("price"));
				car.setEnginecapacity(resultSet.getString("enginecapacity"));
				car.setLicenceplatenumber(resultSet.getString("licenceplatenumber"));
				car.setSeatingcapacity(resultSet.getString("seatingcapacity"));

			}else {
				car=null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return car;
	} 

	@Override
	public String deleteById(String model) {
		String status = "";
		try {
			Connection connection=ConnectionFactory.getConnectionToDb();
			String sqlSelectQuery ="select * from `car` where `model`=?";
			PreparedStatement preparedStatement =null;
			
			preparedStatement=connection.prepareStatement(sqlSelectQuery);
			preparedStatement.setString(1, model);
			ResultSet resultSet = preparedStatement.executeQuery();
			

			if (resultSet.next() == true) {
			
				String sqlDeleteQuery = "delete from `car` where `model`=?";
				preparedStatement = connection.prepareStatement(sqlDeleteQuery);
				preparedStatement.setString(1, model);
				int rowCount = preparedStatement.executeUpdate();
				if (rowCount == 1) {
					status = "success";
				} else {
					status = "failure";
				}

			} else {

				status = "notexisted";
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
		    status = "failure";
		}
		
		return status;
	}
	
	@Override
	public String update(Car car) {
		// TODO Auto-generated method stub
		String status="";
	
		try {
			Connection connection=ConnectionFactory.getConnectionToDb();
			String sqlUpdateQuery ="update `car` set `company`=?,`color`=?,`dateofpurchase`=?, `price`=?,`enginecapacity`=?,`licenceplatenumber`=?,`seatingcapacity`=?  where `model`=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);
			preparedStatement.setString(1, car.getCompany());
			preparedStatement.setString(2, car.getColor());
			preparedStatement.setString(3, car.getDateofpurchase());
			preparedStatement.setString(4, car.getPrice());
			preparedStatement.setString(5, car.getEnginecapacity());
			preparedStatement.setString(6, car.getLicenceplatenumber());
			preparedStatement.setString(7, car.getSeatingcapacity());
			preparedStatement.setString(8, car.getModel());
          	int rowCount = preparedStatement.executeUpdate();
          
			
			if (rowCount == 1) {
				status = "success";
			} else {
				status = "failure";
			}
			
			
		} catch (Exception e) {
			status = "failure";
			
		}
		
		return status;
	}

}
