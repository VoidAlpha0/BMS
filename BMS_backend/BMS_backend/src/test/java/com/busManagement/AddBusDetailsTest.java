package com.busManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.busManagement.Dao.BusDetailsDao;
import com.busManagement.entity.BusDetails;
import com.busManagement.service.AdminService;

@SpringBootTest
public class AddBusDetailsTest {

	
	@Autowired
	private AdminService busService;
	
	@MockBean
	private BusDetailsDao repository;
	
	//Test to get all the bus details
	@Test
	public void testGetBusDetails() {
		when(repository.findAll()).thenReturn(Stream
				.of(new BusDetails("delhi", "jaipur", 25, "16-04-2022", "16-04-2022", 
						"23:00", "17:00", "Pass one", 7899.00)).collect(Collectors.toList()));
		assertEquals(1, busService.getAllBusDetails().size());
	}
	
	//Test to display all the bus details
	@Test
	public void testDisplayAllBusDetails() {
		when(repository.findAll()).thenReturn(Stream
				.of(new BusDetails("Banglore", "mumbai", 25, "17-05-2022", "17-05-2022", "23:00", 
						"17:00", "prasanna", 7899.00), 
						new BusDetails("bangalore", "hyderabad", 48, "12-05-2022", "13-05-2020", 
								"05:20", "23:05", "Pass one", 12899.00)).collect(Collectors.toList()));
		assertEquals(2, busService.getAllBusDetails().size());
	}

    //Test for adding bus details
	@Test
	public void testAddBusDetails() {
		
		BusDetails busObj = new BusDetails("pune", "delhi", 56, "18-05-2022", "18-05-2022", 
				"05:00", "23:55", "Pass one", 9899.55);
		when(repository.save(busObj)).thenReturn(busObj);
		assertEquals(busObj, busService.addBusDetails(busObj));
	}
}
