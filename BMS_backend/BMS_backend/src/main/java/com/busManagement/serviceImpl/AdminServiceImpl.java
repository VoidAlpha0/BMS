package com.busManagement.serviceImpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Service;
import com.busManagement.Dao.*;
import com.busManagement.entity.Admin;
import com.busManagement.entity.BookingDetails;
import com.busManagement.entity.BusDetails;
import com.busManagement.entity.Passenger;
import com.busManagement.exception.AdminAlreadyExistException;
import com.busManagement.exception.AdminDoesnotExistException;
import com.busManagement.exception.BookingDoesNotFoundException;
import com.busManagement.exception.BusDetailsNotFoundException;
import com.busManagement.exception.NullAdminException;
import com.busManagement.exception.NullBusDetailsException;
import com.busManagement.service.AdminService;
import com.busManagement.utils.AdminAuth;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	@Autowired
	BusDetailsDao busDao;
	
	@Autowired
	PassengerDao passengerDao;
	
	@Autowired
	BookingDetailsDao bookingDao;
	
	@Autowired
	BookingDetailsDao bookingDetailsDao;

	//adding admin to the database
	@Override
	public Admin addAdmin(Admin admin) {
		if (admin == null)
			throw new NullAdminException("Please Enter Data");
		Integer adminId = (int) ((Math.random() * 900) + 100); //
		
		admin.setAdminId(adminId);
		Optional<Admin> checkAdmin = adminDao.findById(admin.getAdminId());
		if (checkAdmin.isPresent()) {
			throw new AdminAlreadyExistException("admin already exist exception");
		} else {
			adminDao.save(admin);
			System.out.println(adminId);
			return admin;
		}
	}
	
	
//for getting admin by ID
	@Override
	public Admin getAdmin(Integer adminId) {
		if (adminId == null)
			throw new NullAdminException("Please Enter Data");
		Optional<Admin> admin = adminDao.findById(adminId);
		if (!admin.isPresent()) {
			throw new AdminDoesnotExistException("admin does not exist ");
		}
		return admin.get();
	}
	
	@Override
	public Admin findByadminUName(String adminUname) {
		if(adminUname == null)
			throw new NullAdminException("Please Enter Data");
		Admin admin = adminDao.findByAdminUName(adminUname);
		if(admin==null) {
			throw new AdminDoesnotExistException("admin does not exist ");
		}
		return admin;
	}
	
	//FOR DELETING ADMIN
	@Override
	public void deleteAdmin(Integer adminId) {
		if (adminId == null)
			throw new NullAdminException("Please Enter Data");
		Optional<Admin> admin = adminDao.findById(adminId);
		if (!admin.isPresent()) {
			throw new AdminDoesnotExistException("admin Doesnot Exist Exception");
		}
		adminDao.deleteById(adminId);
	}
    
	//admin Login 
	@Override
	public Admin adminLogin(AdminAuth auth) {
	    if (auth == null) {
	        throw new NullAdminException("Please Enter Data");
	    }
	   
	    if (auth.getAdminUName() == null) {
	        throw new NullAdminException("Please Enter Admin ID helllo ");
	    }
	    System.out.println(auth.getAdminUName()+"this is admin uname");
	    Admin admin = adminDao.findByAdminUName(auth.getAdminUName());
	    
	    if (admin != null) {
	    	System.out.println(admin.getAdminId()+"this shows that it was able to find admin ");
	        if (admin.getPassword().equals(auth.getPassword())) {
	        	System.out.println(admin.getAdminId()+"this shows that password was correct ");
	            return admin;
	        } else {
	            throw new AdminDoesnotExistException("Invalid Password");
	        }
	    } else {
	        throw new AdminDoesnotExistException("Admin with ID: " + auth.getAdminId() + " does not exist");
	    }
	}
	
	
     
	
	//For getting all the bus details
	@Override
	public List<BusDetails> getAllBusDetails() {
		return busDao.findAll();
	}
    
	//For adding bus details
	@Override
	public BusDetails addBusDetails(BusDetails details) {
		if (details == null) {
			throw new NullBusDetailsException("Please Enter Data");
		}
		Integer busNumber = (int) ((Math.random() * 9000) + 1000);
		details.setBusNumber(busNumber);
		busDao.save(details);
		return details;
	}

	//Deleting Bus By ID
	@Override
	public void deleteBus(Integer busNumber) {
		if (busNumber == null)
			throw new NullBusDetailsException("Please Enter Data");
		Optional<BusDetails> details = busDao.findById(busNumber);
		if (!details.isPresent()) {
			throw new BusDetailsNotFoundException("Bus Details Not Found");
		}
		busDao.deleteById(busNumber);
	}
    
	//Updating the bus details By ID
	@Override
	public BusDetails updateBus(BusDetails details) {
		if (details == null)
			throw new NullBusDetailsException("Please Enter Data");
		Optional<BusDetails> busDetails = busDao.findById(details.getBusNumber());
		if (!busDetails.isPresent()) {
			throw new BusDetailsNotFoundException("Bus with busNumber: " + details.getBusNumber() + " not exists..");
		}
		busDao.save(details);
		return details;
	}
	
	
	//Getting all the passengers
	public List<Passenger> getAllPassengers(){
		return passengerDao.findAll();
	}
	
	//Getting list of passengers by ID
	public List<Passenger> getPassengersByBooking(Integer id){
		if (id == null) throw new BookingDoesNotFoundException("Please Enter Data");
		Optional<BookingDetails> details = bookingDao.findById(id);
		if (!details.isPresent())
			throw new BookingDoesNotFoundException("Booking Not Found");
		return details.get().getPassengers();
	}
	
	@Override
	public List<BookingDetails> getAllBookings() {
		return bookingDetailsDao.findAll();
	}


	@Override
	public BookingDetails updatebooking(BookingDetails details) {
		if (details == null)
			throw new NullBusDetailsException("Please Enter Data");
		Optional<BookingDetails> bookingDetails = bookingDetailsDao.findById(details.getBookingId());
		if(!bookingDetails.isPresent()) {
			throw new BookingDoesNotFoundException("Booking Not Found");
		}
		bookingDetailsDao.save(details);
		return details;
	}


	@Override
	public void updatebookingbyid(Integer bookingid) {
		if (bookingid == null)
			throw new NullBusDetailsException("Please Enter Data");
		BookingDetails details = bookingDetailsDao.findById(bookingid).get();
		details.setStatus(true);
		bookingDetailsDao.save(details);
		
	}


	public BusDetails getBusById(Integer id) {
		if(id == null)
			throw new BusDetailsNotFoundException(" Bus Id Null!!!");
		BusDetails bus= busDao.getById(id);
		if(bus==null) {
			throw new BusDetailsNotFoundException("No bus with Id");
		}
		return bus;
	}







}