package com.example.demo.DaoTests;

import com.example.demo.Dao.*;
import com.example.demo.model.User;
import com.example.demo.model.UserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsDaoTest {

    private UserDetailsDao userDetailsDao;

    @BeforeEach
    public void setUp() {
        userDetailsDao = new UserDetailsDao();
    }

    @Test
    public void getUserDetailsPositiveTest() {
        UserDetails detailsToCheck = new UserDetails();
        detailsToCheck.setAddress("St. Deribasivska 13");
        detailsToCheck.setJob("PM");
        detailsToCheck.setSalary(120000);

        Optional<UserDetails> userDetails = userDetailsDao.get(2);
		UserDetails userDetails2 = userDetails.orElse(null);

        assertEquals(detailsToCheck.getAddress(), userDetails2.getAddress());
        assertEquals(detailsToCheck.getSalary(), userDetails2.getSalary());
        assertEquals(detailsToCheck.getJob(), userDetails2.getJob());
    }

    @Test
    public void saveAndDeleteUserDetailsPositiveTest() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(9);
        userDetails.setAddress("St. Deribasivska 13");
        userDetails.setJob("PM");
        userDetails.setSalary(120000);

        // Save UserDetails to the database
        userDetailsDao.save(userDetails);

        // Retrieve UserDetails from the database
        Optional<UserDetails> savedUserDetails = userDetailsDao.get(9);
        UserDetails savedUserDetails2 = savedUserDetails.orElse(null);

        // Perform assertions
        assertNotNull(savedUserDetails2);
        assertEquals(userDetails.getUserId(), savedUserDetails2.getUserId());
        assertEquals(userDetails.getAddress(), savedUserDetails2.getAddress());
        assertEquals(userDetails.getSalary(), savedUserDetails2.getSalary());
        assertEquals(userDetails.getJob(), savedUserDetails2.getJob());

        userDetailsDao.delete(9);
    }

    @Test
    public void updateUserDetailsPositiveTest() {
        // Create UserDetails object to update
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);
        userDetails.setAddress("Bul. Academy Voltza 12");
        userDetails.setJob("Java Developer");
        userDetails.setSalary(6200);

        // Update UserDetails in the database
        userDetailsDao.update(userDetails);

        // Retrieve UserDetails from the database
        Optional<UserDetails> updatedUserDetails = userDetailsDao.get(1);
        UserDetails updatedUserDetails2 = updatedUserDetails.orElse(null);

        // Perform assertions
        assertNotNull(updatedUserDetails2);
        assertEquals(userDetails.getUserId(), updatedUserDetails2.getUserId());
        assertEquals(userDetails.getAddress(), updatedUserDetails2.getAddress());
        assertEquals(userDetails.getSalary(), updatedUserDetails2.getSalary());
        assertEquals(userDetails.getJob(), updatedUserDetails2.getJob());
    }

    @Test
    public void getAllUserDetailsPositiveTest() {
        // Retrieve all UserDetails from the database
        List<UserDetails> userDetailsList = userDetailsDao.getAll();

        // Perform assertions
        assertNotNull(userDetailsList);
        assertFalse(userDetailsList.isEmpty());

        for (UserDetails userDetails : userDetailsList) {
            System.out.println("User ID: " + userDetails.getUserId());
            System.out.println("Job: " + userDetails.getJob());
            System.out.println("Address: " + userDetails.getAddress());
            System.out.println("Salary: " + userDetails.getSalary());
            System.out.println("-------------------------");
        }
    }


}