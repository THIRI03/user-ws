package com.cleaningService.userws.dbaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public List<Booking> listAllBookings() throws SQLException {
        List<Booking> bookingList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bookings")) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setServiceAddress(rs.getString("service_address"));
                booking.setDuration(rs.getInt("duration"));
                booking.setSpecialRequest(rs.getString("special_request"));
                booking.setServiceId(rs.getInt("serviceid"));
                booking.setUserId(rs.getInt("userid"));
                bookingList.add(booking);
            }
        }

        return bookingList;
    }

    public Booking getBookingDetails(int bookingId) throws SQLException {
        Booking booking = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bookings WHERE id = ?")) {
            pstmt.setInt(1, bookingId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setServiceAddress(rs.getString("service_address"));
                booking.setDuration(rs.getInt("duration"));
                booking.setSpecialRequest(rs.getString("special_request"));
                booking.setServiceId(rs.getInt("serviceid"));
                booking.setUserId(rs.getInt("userid"));
            }
        }

        return booking;
    }

    public int insertBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (service_address, duration, special_request, serviceid, userid) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, booking.getServiceAddress());
            pstmt.setInt(2, booking.getDuration());
            pstmt.setString(3, booking.getSpecialRequest());
            pstmt.setInt(4, booking.getServiceId());
            pstmt.setInt(5, booking.getUserId());

            return pstmt.executeUpdate();
        }
    }

    public int updateBooking(Booking booking) throws SQLException {
        String sql = "UPDATE bookings SET service_address = ?, duration = ?, special_request = ?, serviceid = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, booking.getServiceAddress());
            pstmt.setInt(2, booking.getDuration());
            pstmt.setString(3, booking.getSpecialRequest());
            pstmt.setInt(4, booking.getServiceId());
            pstmt.setInt(5, booking.getId());

            return pstmt.executeUpdate();
        }
    }

    public int deleteBooking(int bookingId) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM bookings WHERE id = ?")) {
            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate();
        }
    }
}
