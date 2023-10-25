package com.busManagement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.busManagement.Dao.LocationDao;
import com.busManagement.entity.Location;
import com.busManagement.serviceImpl.LocationServiceImpl;

	
	@SpringBootTest
	public class LocationTest {

		@Autowired
		private LocationServiceImpl locImpl;
		
		@MockBean
		private LocationDao repository;
		
		// ADDS a Location to
		@Test
		public void AddLocationTest() {
			Location lobj = new Location("dsfas","sd","sc");
			when(repository.save(lobj)).thenReturn(lobj);
			assertEquals(lobj, locImpl.addLocation(lobj));
		}
		
        //update location 
	    @Test
	    public void UpdateLocationTest() {
	        long locationId = 1; 
	        Location existingLocation = new Location("ExistingTerminal", "ExistingCity", "ExistingState");
	        Location updatedLocation = new Location("UpdatedTerminal", "UpdatedCity", "UpdatedState");

	        when(repository.findById(locationId)).thenReturn(java.util.Optional.of(existingLocation));
	        when(repository.save(existingLocation)).thenReturn(updatedLocation);

	        Location result = locImpl.updateLocation(updatedLocation, locationId);
	        assertEquals(updatedLocation, result);
	    }
	    
	    //delete location
	    @Test
	    public void DeleteLocationTest() {
	        long locationId = 1; 
	        Location existingLocation = new Location("ExistingTerminal", "ExistingCity", "ExistingState");

	        when(repository.findById(locationId)).thenReturn(java.util.Optional.of(existingLocation));

	        locImpl.deleteLocation(locationId);
	        verify(repository).deleteById(locationId);
	    }
	    
	    // GET all Locations test case
	    @Test
	    public void GetAllLocationsTest() {
	        List<Location> locationList = new ArrayList<>();
	        locationList.add(new Location("Location1", "City1", "State1"));
	        locationList.add(new Location("Location2", "City2", "State2"));

	        when(repository.findAll()).thenReturn(locationList);

	        List<Location> result = locImpl.getAllLocations();
	        assertEquals(locationList, result);
	    }
		
		
}
