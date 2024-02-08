package com.example.demo.DaoTests;

import com.example.demo.Dao.*;
import com.example.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    private UserDao userDao;

    private static int current = 33;

    @BeforeEach
    public void setUp() {
        userDao = new UserDao();
    }

    @Test
    public void saveAndGetUserPositiveTest() {
        User newUser = new User();
		newUser.setEmail("all33@gmail.com");
		newUser.setUsername("Alex");
		newUser.setUsersurname("Flag2");
        newUser.setUserId(current);
		userDao.save(newUser);

        Optional<User> optionalUser = userDao.get(current);
        User user2 = optionalUser.orElse(null);
        current++;

        assertEquals(newUser, user2);
    }

    @Test
    public void getUserPositiveTest() {
        Optional<User> optionalUser = userDao.get(1);
		User user = optionalUser.orElse(null);

        User user2 = new User();
        user2.setEmail("user1@example.com");
        user2.setUsername("Alex");
        user2.setUsersurname("Ravlex");
        user2.setUserId(1);

        assertEquals(user, user2);
    }

    @Test
    public void deleteUserPositiveTest() {
        User user = new User();
        user.setEmail("userVas34@example.com");
        user.setUsername("Vasiliy");
        user.setUsersurname("Kasper");
        user.setUserId(current+1); //34
        userDao.save(user);

        userDao.delete(user.getUserId());
        Optional<User> deletedUser = userDao.get(user.getUserId());
        current++;

        assertFalse(deletedUser.isPresent(), "User should be deleted");
    }

    @Test
    public void getAllUsersPositiveTest() {
        User user1 = userDao.get(1).orElse(null);
        User user2 = userDao.get(9).orElse(null);

        List<User> users = userDao.getAll();

        assertNotNull(users);
        assertTrue(users.size() >= 2);
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    public void updateUserPositiveTest() {
        User user2 = userDao.get(7).orElse(null);
        String email = "KRABSTER777@example.com";
		user2.setEmail(email);
		userDao.update(user2);

        User userGet = userDao.get(7).orElse(null);
        assertEquals(userGet.getEmail(), email);
    }

}
