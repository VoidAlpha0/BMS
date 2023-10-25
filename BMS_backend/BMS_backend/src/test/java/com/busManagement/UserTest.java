package com.busManagement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.busManagement.Dao.UserDao;
import com.busManagement.entity.User;
import com.busManagement.service.UserService;

import com.busManagement.utils.UserAuth;
@SpringBootTest
public class UserTest {
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserDao dao;
		
	//Test for getallusers
	  @Test
	    public void testGetAllUsers() {
	        // Arrange
	        List<User> userList = new ArrayList<>();
	        userList.add(new User(1, "User1", "User1", "password1", 1234567890L, "user1@example.com"));
	        userList.add(new User(2,"User2","User2", "password2", 9876543210L, "user2@example.com"));
	        when(dao.findAll()).thenReturn(userList);

	        // Act
	        List<User> result = userService.getAllUsers();

	        // Assert
	        assertEquals(2, result.size());
	    }
	  
	  
	//Test for adduser
		@Test
		public void testAddUser() {
			User user = new User();
			user.setUserName("John");
			user.setPhone(987654321l);
			user.setEmail("john@gmail.com");
			user.setPassword("john@12345");
			when(dao.save(user)).thenReturn(user);
			assertEquals(user, userService.addUser(user));
		}
		//Test for userlogin

		@Test
		public void testUserLogin() {
			User user = new User();
			user.setUserId(123);
			user.setPassword("Password");

			UserAuth auth = new UserAuth();
			auth.setUserId(123);
			auth.setPassword("Password");

			when(dao.findById(123)).thenReturn(java.util.Optional.of(user));
			assertEquals(user, userService.userLogin(auth));
		}
		
		
		//testupdateuser
		@Test
	    public void testUpdateUser() {
	        // Arrange
	        User user = new User();
	        user.setUserId(1);
	        user.setUserName("John");
	        user.setPhone(987654321L);
	        user.setEmail("john@gmail.com");
	        user.setPassword("john@12345");

	        when(dao.findById(1)).thenReturn(Optional.of(user));
	        when(dao.save(user)).thenReturn(user);

	        // Act
	        userService.updateUser(user);

	        // Assert
	        User updatedUser = userService.getUser(1);
	        assertEquals("John", updatedUser.getUserName());
	        assertEquals(987654321L, updatedUser.getPhone());
	        assertEquals("john@gmail.com", updatedUser.getEmail());
	        assertEquals("john@12345", updatedUser.getPassword());
	    }
		
		//delete user
		   @Test
		    public void testDeleteUser() {
		        // Arrange
		        User user = new User();
		        user.setUserId(1);

		        when(dao.findById(1)).thenReturn(Optional.of(user));

		        // Act
		        userService.deleteUser(1);

		        // Assert
		        verify(dao).deleteById(1);
		    }
		   
		   
	
}