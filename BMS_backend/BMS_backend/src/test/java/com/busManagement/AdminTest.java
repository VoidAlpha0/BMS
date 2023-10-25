package com.busManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.busManagement.Dao.AdminDao;
import com.busManagement.entity.Admin;
import com.busManagement.serviceImpl.AdminServiceImpl;
import com.busManagement.utils.AdminAuth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdminTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminDao adminDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdminLogin_Success() {
        // Create a sample admin and authentication request
        Admin sampleAdmin = new Admin();
        sampleAdmin.setAdminUName("adminUsername");
        sampleAdmin.setPassword("adminPassword");

        AdminAuth auth = new AdminAuth();
        auth.setAdminUName("adminUsername");
        auth.setPassword("adminPassword");

        // Mock the behavior of the adminDao to return the sample admin
        when(adminDao.findByAdminUName("adminUsername")).thenReturn(sampleAdmin);

        // Perform the admin login
        Admin result = adminService.adminLogin(auth);

        // Verify that the result matches the expected admin
        assertEquals(sampleAdmin, result);
    }

   
}
