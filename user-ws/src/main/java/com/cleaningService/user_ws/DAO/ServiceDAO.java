package com.cleaningService.user_ws.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cleaningService.user_ws.util.DBConnection;
import com.cleaningService.user_ws.model.Service;

public class ServiceDAO {

    // Retrieve all services
    public ArrayList<Service> getAllServices() {
        ArrayList<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM service ORDER BY id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));
                service.setDescription(rs.getString("description"));
                service.setPrice(rs.getDouble("price"));
                service.setCategory_id(rs.getInt("category_id"));
                service.setImage(rs.getString("image"));
                services.add(service);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all services: " + e.getMessage());
            e.printStackTrace();
        }

        return services;
    }

    // Retrieve service by ID
    public Service getServiceById(int id) {
        Service service = null;
        String sql = "SELECT * FROM service WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                service = new Service();
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));
                service.setDescription(rs.getString("description"));
                service.setPrice(rs.getDouble("price"));
                service.setCategory_id(rs.getInt("category_id"));
                service.setImage(rs.getString("image"));
            } else {
                System.out.println("No service found with ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving service by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return service;
    }
}
