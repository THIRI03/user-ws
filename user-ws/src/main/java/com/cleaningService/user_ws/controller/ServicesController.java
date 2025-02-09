package com.cleaningService.user_ws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cleaningService.user_ws.model.Service;
import com.cleaningService.user_ws.DAO.ServiceDAO;

import java.util.ArrayList;

@RestController
@RequestMapping("/services")
public class ServicesController {

    // Retrieve all services
    @RequestMapping(method = RequestMethod.GET, path = "/getAllServices")
    public ArrayList<Service> getAllServices() {
        ArrayList<Service> serviceList = new ArrayList<>();
        try {
            ServiceDAO db = new ServiceDAO();
            serviceList = db.getAllServices(); // Ensure `getAllServices()` returns ArrayList<Service>
        } catch (Exception e) {
            System.err.println("Error in getAllServices: " + e.getMessage());
            e.printStackTrace();
        }
        return serviceList;
    }

 // **New Method: Retrieve services by category ID**
    @GetMapping("/getServicesByCategory/{categoryId}")
    public ArrayList<Service> getServicesByCategory(@PathVariable("categoryId") int categoryId) {
        ArrayList<Service> serviceList = new ArrayList<>();
        try {
            ServiceDAO db = new ServiceDAO();
            serviceList = db.getServicesByCategoryId(categoryId);  // Ensure this method exists in ServiceDAO
        } catch (Exception e) {
            System.err.println("Error in getServicesByCategory: " + e.getMessage());
            e.printStackTrace();
        }
        return serviceList;
    }
}
