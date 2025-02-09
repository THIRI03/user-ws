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

    // Retrieve service by ID
    @RequestMapping(method = RequestMethod.GET, path = "/getService/{id}")
    public Service getServiceById(@PathVariable("id") int id) {
        Service service = null;
        try {
            ServiceDAO db = new ServiceDAO();
            service = db.getServiceById(id);
        } catch (Exception e) {
            System.err.println("Error in getServiceById: " + e.getMessage());
            e.printStackTrace();
        }
        return service;
    }
}
