package com.cleaningService.user_ws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cleaningService.user_ws.model.Category;
import com.cleaningService.user_ws.DAO.CategoryDAO;
import com.cleaningService.user_ws.util.DBConnection;

import java.util.ArrayList;

@RestController
@RequestMapping("/category")
public class CategoryController {

	 // Retrieve all categories
    @RequestMapping(method = RequestMethod.GET, path = "/getAllCategories")
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categoryList = new ArrayList<>();
        try {
            CategoryDAO db = new CategoryDAO();
            categoryList = db.getAllCategories(); // Ensure `getAllCategories()` returns ArrayList<Category>
        } catch (Exception e) {
            System.err.println("Error in getAllCategories: " + e.getMessage());
            e.printStackTrace();
        }
        return categoryList;
    }

    // Retrieve category by ID
    @RequestMapping(method = RequestMethod.GET, path = "/getCategory/{id}")
    public Category getCategoryById(@PathVariable("id") int id) {
        Category category = null;
        try {
            CategoryDAO db = new CategoryDAO();
            category = db.getCategoryById(id);
        } catch (Exception e) {
            System.err.println("Error in getCategoryById: " + e.getMessage());
            e.printStackTrace();
        }
        return category;
    }
}