package com.busManagement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.busManagement.Dao.BusDetailsDao;
import com.busManagement.entity.BusDetails;
import com.busManagement.service.AdminService;
@SpringBootTest
public class BusDetailsTest {

	@Autowired
	private AdminService busService;

	@MockBean
	private BusDetailsDao repository;

	// Test for adding bus details
	@Test
	public void testAddBusDetails() {
		BusDetails busObj = new BusDetails("Mumbai", "Pune", 40, "2023-10-28", "2023-10-28", "08:00 AM", "12:00 PM",
				"XYZ Bus", 450.0);
		when(repository.save(busObj)).thenReturn(busObj);

		assertEquals(busObj, busService.addBusDetails(busObj));
	}

	// Test to get all the bus details
	@Test
    public void testGetBusDetails() {
        when(repository.findAll()).thenReturn(Stream.of(new BusDetails("Delhi", "Jaipur", 30, "2023-10-29", "2023-10-29","10:00 AM", "02:00 PM", "ABC Bus", 550.0)).collect(Collectors.toList()));

        assertEquals(1, busService.getAllBusDetails().size());
    }

	// Test to display all the bus details
	@Test
    public void testDisplayAllBusDetails() {
        when(repository.findAll()).thenReturn(Stream
                .of(new BusDetails("Bangalore", "Chennai", 32, "2023-10-30", "2023-10-30",
                        "08:30 AM", "01:30 PM", "XYZ Bus", 500.0),
                    new BusDetails("Hyderabad", "Vizag", 28, "2023-10-30", "2023-10-30",
                        "09:00 AM", "03:30 PM", "PQR Bus", 600.0))
                .collect(Collectors.toList()));

        assertEquals(2, busService.getAllBusDetails().size());
    }
	
	@Test
	public void testViewBusDetails() {
		when(repository.findAll()).thenReturn(Stream
				.of(new BusDetails("Banglore", "mumbai", 25, "17-05-2022", "17-05-2022", "23:00", 
						"17:00", "prasanna", 7899.00), 
						new BusDetails("bangalore", "hyderabad", 48, "12-05-2022", "13-05-2020", 
								"05:20", "23:05", "Pass one", 12899.00)).collect(Collectors.toList()));
		assertEquals(2, busService.getAllBusDetails().size());
	}
	
	//update bus details
	
	@Test
	public void testUpdateBusDetails() {
	    // Create a BusDetails object with initial data
	    BusDetails initialBusDetails = new BusDetails("Mumbai", "Pune", 40, "2023-10-28", "2023-10-28", "08:00 AM", "12:00 PM", "XYZ Bus", 450.0);
	    initialBusDetails.setBusNumber(1); // Set a specific bus number

	    // Mock the behavior of the repository's findById method
	    when(repository.findById(1)).thenReturn(Optional.of(initialBusDetails));

	    // Create a BusDetails object with updated data
	    BusDetails updatedBusDetails = new BusDetails("Mumbai", "Pune", 35, "2023-10-28", "2023-10-28", "09:00 AM", "01:00 PM", "ABC Bus", 500.0);
	    updatedBusDetails.setBusNumber(1); // Set the same bus number as the initial one

	    // Mock the behavior of the repository's save method
	    when(repository.save(updatedBusDetails)).thenReturn(updatedBusDetails);

	    // Call the service method to update the bus details
	    BusDetails result = busService.updateBus(updatedBusDetails);

	    // Assert that the updated bus details match the result
	    assertEquals(updatedBusDetails, result);
	}
	
	//delete bus details
	
	@Test
	public void testDeleteBusDetails() {
	    // Create a BusDetails object with a specific bus number
	    BusDetails busDetailsToDelete = new BusDetails("Mumbai", "Pune", 40, "2023-10-28", "2023-10-28", "08:00 AM", "12:00 PM", "XYZ Bus", 450.0);
	    busDetailsToDelete.setBusNumber(1); // Set a specific bus number

	    // Mock the behavior of the repository's findById method
	    when(repository.findById(1)).thenReturn(Optional.of(busDetailsToDelete));

	    // Mock the behavior of the repository's deleteById method
	    doNothing().when(repository).deleteById(1);

	    // Call the service method to delete the bus details
	    busService.deleteBus(1);

	    // Assert that the bus details were deleted successfully
	    verify(repository, times(1)).deleteById(1);
	}
	
}
