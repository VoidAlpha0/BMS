package com.busManagement.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.busManagement.entity.Location;
import com.busManagement.exception.UserValidationException;
import com.busManagement.serviceImpl.LocationServiceImpl;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/location")
public class LocationController {
	@Autowired
	private LocationServiceImpl service;
	
	@PostMapping("/addlocation")
	public ResponseEntity<Location> addLocation(@Valid @RequestBody Location Loc, Errors error) {
		if (error.hasErrors()) {
			throw new UserValidationException("invalid data provided");
		}
		Location addedLocation = service.addLocation(Loc);
		return ResponseEntity.ok().body(addedLocation);
	}
	@PutMapping("/updateLocation/{id}")
	public ResponseEntity<Void> updateLocation(@Valid @PathVariable Long id, @RequestBody Location Loc) {
		service.updateLocation(Loc,id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		service.deleteLocation(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/getLocations")
	public ResponseEntity<List<Location>> getAllLocations(){
		List<Location> details = service.getAllLocations();
		return ResponseEntity.ok().body(details);
	}
	
	@GetMapping("/getLocation/{id}")
	public ResponseEntity<Location> getLocation(@PathVariable Long id){
		Location details = service.getLocation(id);
		return ResponseEntity.ok().body(details);
	}
	
	
}
