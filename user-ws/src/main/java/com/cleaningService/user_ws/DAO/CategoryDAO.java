package com.cleaningService.user_ws.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cleaningService.user_ws.util.DBConnection;
import com.cleaningService.user_ws.model.Category;

public class CategoryDAO {

    // Retrieve all categories
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Category ctg = new Category();
                ctg.setId(rs.getInt("id"));
                ctg.setCategoryName(rs.getString("name"));
                ctg.setDescription(rs.getString("description"));
                ctg.setImage(rs.getString("image"));
                categories.add(ctg);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all categories: " + e.getMessage());
            e.printStackTrace();
        }

        return categories;
    }

    // Retrieve category by ID
    public Category getCategoryById(int categoryId) {
        Category category = null;
        String sql = "SELECT * FROM category WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setImage(rs.getString("image"));
            } else {
                System.out.println("No category found with ID " + categoryId);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving category by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return category;
    }
}