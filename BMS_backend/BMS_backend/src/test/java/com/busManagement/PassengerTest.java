package com.busManagement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.busManagement.Dao.PassengerDao;
import com.busManagement.entity.Passenger;
import com.busManagement.service.AdminService;
import com.busManagement.service.UserService;

@SpringBootTest
public class PassengerTest {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AdminService adminService;

    @MockBean
    private PassengerDao passengerDao;
    
 // Test to display all passengers
    @Test
    public void testDisplayAllPassengers() {
        Passenger passenger1 = new Passenger();
        passenger1.setPassengerId(1);
        passenger1.setName("Alice");
        passenger1.setAge(25);
        passenger1.setLuggage(15.0);

        Passenger passenger2 = new Passenger();
        passenger2.setPassengerId(2);
        passenger2.setName("Bob");
        passenger2.setAge(30);
        passenger2.setLuggage(20.0);

        when(passengerDao.findAll()).thenReturn(Stream.of(passenger1, passenger2).collect(Collectors.toList()));

        assertEquals(2, adminService.getAllPassengers().size());
    }
 
    // Test to update a passenger
    @Test
    public void testUpdatePassenger() {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(1);
        passenger.setName("Alice");
        passenger.setAge(25);
        passenger.setLuggage(15.0);

        when(passengerDao.findById(1)).thenReturn(Optional.of(passenger));
        when(passengerDao.save(passenger)).thenReturn(passenger);

        passenger.setAge(26); // Updated age

        Passenger updatedPassenger = userService.updatePassenger(passenger);

        assertEquals(26, updatedPassenger.getAge());
    }


}