//    JAD-CA2
//    Class-DIT/FT/2A/23
//    Student Name: Thiri Lae Win
//    Admin No.: P2340739

package com.cleaningService.user_ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cleaningService.user_ws.DAO.BookingDAO;
import com.cleaningService.user_ws.model.Booking;
import com.cleaningService.user_ws.model.Cleaner;

@RestController
public class BookingController {

	@RequestMapping(method=RequestMethod.GET, path="/getBooking")
	public List<Booking> getAllUsers(){

		List <Booking> myList = new ArrayList<>();
		
		try {
			BookingDAO bookingDAO = new BookingDAO();

			myList = bookingDAO.retrieveAllBookings();
			
			
		}catch(Exception e) {
			System.out.println("Error :" + e);
		}
		return myList;
	}
	
	@RequestMapping(
			path="/reserveBooking/{bookingId}",
			consumes="application/json",
			method= RequestMethod.PUT)
	public boolean reserveBooking(@PathVariable int bookingId, @RequestBody Cleaner cleaner) {
		boolean isUpdated = false;
		
		try {
			BookingDAO db = new BookingDAO();
			int cleanerId = cleaner.getId();
			System.out.print(cleanerId);
			isUpdated = db.updateBookingCleaner(bookingId, cleanerId);
			System.out.println("...in BookingController - done reserveBooking..." + isUpdated);
		}catch(Exception e){
			System.out.print(e.toString());
		}
		return isUpdated;
	}
	

	@RequestMapping(method=RequestMethod.GET, path="/getBooking/{cleanerId}")
	public List <Booking> getBookingByCleanerId(@PathVariable int cleanerId) {
		List <Booking> myList = new ArrayList<>();
		try {
			BookingDAO bookingDAO = new BookingDAO();

			myList = bookingDAO.retrieveAllBookingsByCleanerId(cleanerId);
			
			
		}catch(Exception e) {
			System.out.println("Error :" + e);
		}
		return myList;
	}
	
	
	
	@RequestMapping(
			path="/updateBookingStatus/{bookingId}",
			consumes="application/json",
			method= RequestMethod.PUT)
	public boolean updateBookingStatus(@PathVariable int bookingId, @RequestBody Booking booking) {
		boolean isUpdated = false;
		
		try {
			BookingDAO db = new BookingDAO();
			String status = booking.getStatus();
			isUpdated = db.updateBookingStatus(bookingId, status);
			System.out.println("...in BookingController - done updateBookingStatus..." + isUpdated);
		}catch(Exception e){
			System.out.print(e.toString());
		}
		return isUpdated;
	}
}
