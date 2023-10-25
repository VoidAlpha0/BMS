package com.busManagement.service;

import java.util.List;

import com.busManagement.entity.Admin;
import com.busManagement.entity.BookingDetails;
import com.busManagement.entity.BusDetails;
import com.busManagement.entity.Passenger;
import com.busManagement.utils.AdminAuth;

public interface AdminService {
	public Admin addAdmin(Admin admin); //adding admin

	public Admin getAdmin(Integer adminId);
	
	public Admin findByadminUName(String adminUname);

	public void deleteAdmin(Integer adminId);

	public Admin adminLogin(AdminAuth auth);

	public List<BusDetails> getAllBusDetails();

	public BusDetails addBusDetails(BusDetails details);

	public void deleteBus(Integer busNumber);

	public BusDetails updateBus(BusDetails details);
	
	public List<Passenger> getAllPassengers();
	
	public List<Passenger> getPassengersByBooking(Integer id);
	
	public List<BookingDetails> getAllBookings();
	
	public BookingDetails updatebooking (BookingDetails details);
	
	public void updatebookingbyid (Integer bookingid);
	
	public BusDetails getBusById(Integer id);

}