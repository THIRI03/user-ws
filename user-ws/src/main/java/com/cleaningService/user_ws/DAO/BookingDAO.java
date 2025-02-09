//    JAD-CA2
//    Class-DIT/FT/2A/23
//    Student Name: Thiri Lae Win
//    Admin No.: P2340739

package com.cleaningService.user_ws.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cleaningService.user_ws.model.Booking;
import com.cleaningService.user_ws.util.DBConnection;


public class BookingDAO {
	public List<Booking> retrieveAllBookings(){
		List<Booking> bookings = new ArrayList<>();
		 
		String sql = "SELECT b.id, u.name AS customer_name, s.name AS service_name, b.booking_date, b.booking_time , "
				+ "b.status, b.special_request, b.service_address, b.duration "
				+"FROM bookings b JOIN users u ON b.userid = u.id JOIN service s ON b.serviceid = s.id "
				+"WHERE b.cleaner_id IS NULL "
				+"AND b.status = 'Not Completed' "
				+ "ORDER BY b.id";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getInt("id"));
				booking.setCustomerName(rs.getString("customer_name"));
				booking.setServiceName(rs.getString("service_name"));
				booking.setDuration(rs.getInt("duration"));
				booking.setSpecialRequest(rs.getString("special_request"));
				booking.setServiceAddress(rs.getString("service_address"));
				booking.setDate(rs.getString("booking_date"));
				booking.setTime(rs.getString("booking_time"));
				booking.setStatus(rs.getString("status"));
				
				bookings.add(booking);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bookings;
	}
	
	public List<Booking> retrieveAllBookingsByCleanerId(int cleanerId) {
	    List<Booking> bookings = new ArrayList<>();
	    
	    String sql = "SELECT b.id, u.name AS customer_name, s.name AS service_name, " +
	                 "b.booking_date, b.booking_time, b.status, b.special_request, " +
	                 "b.service_address, b.duration " +
	                 "FROM bookings b " +
	                 "JOIN users u ON b.userid = u.id " +
	                 "JOIN service s ON b.serviceid = s.id " +
	                 "WHERE b.cleaner_id = ? AND b.status != 'Completed'" +   // Filter by cleanerId
	                 "ORDER BY b.id ASC";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, cleanerId);  // Set the cleanerId parameter
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Booking booking = new Booking();
	            booking.setId(rs.getInt("id"));
	            booking.setCustomerName(rs.getString("customer_name"));
	            booking.setServiceName(rs.getString("service_name"));
	            booking.setDuration(rs.getInt("duration"));
	            booking.setSpecialRequest(rs.getString("special_request"));
	            booking.setServiceAddress(rs.getString("service_address"));
	            booking.setDate(rs.getString("booking_date"));
	            booking.setTime(rs.getString("booking_time"));
	            booking.setStatus(rs.getString("status"));

	            bookings.add(booking);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return bookings;
	}

	
	
	
	public boolean updateBookingCleaner(int id, int cleanerId) {
		boolean isUpdated = false;
		System.out.print(cleanerId);

		String sql = "UPDATE bookings SET cleaner_id = ? WHERE id = ?";
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setInt(1, cleanerId);
			stmt.setInt(2, id);
			
			int rowsAffected = stmt.executeUpdate();
			
			if(rowsAffected > 0) {
				isUpdated = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}
	
	public boolean updateBookingStatus(int bookingId, String status) {
		boolean isUpdated = false;
		
		String sql = "UPDATE bookings SET status = ? WHERE id = ?";
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, status);
			stmt.setInt(2, bookingId);
			
			int rowsAffected = stmt.executeUpdate();
			
			if(rowsAffected > 0) {
				isUpdated = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}
	
	
}
