package com.example.demo;

import com.example.demo.Dao.*;
import com.example.demo.Services.OrderService;
import com.example.demo.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//Setting UserDao
		UserDao userDao = new UserDao();

		//to get user with id = 1
//		Optional<User> optionalUser = userDao.get(1);
//		User user = optionalUser.orElse(null); // transforms Optional into User object or returns null (or can be new User())
//		String username = user.getUsername();
//		System.out.println("User ID: " + user.getUserId());
//		System.out.println("Username: " + user.getUsername());
//		System.out.println("Usersurname: " + user.getUsersurname());
//		System.out.println("Email: " + user.getEmail());

		//to add NEW user
//		User newUser = new User();
//		newUser.setEmail("shapowal111@gmail.com");
//		newUser.setUsername("Bohdan");
//		newUser.setUsersurname("Shapowal");
//		userDao.save(newUser);

		//to delete user by id
//		userDao.delete(25);

		//to display ALL users
//		List<User> users = userDao.getAll();
//		System.out.println("List of Users:");
//		for (User usera : users) {
//			System.out.println("User ID: " + usera.getUserId());
//			System.out.println("Username: " + usera.getUsername());
//			System.out.println("Usersurname: " + usera.getUsersurname());
//			System.out.println("Email: " + usera.getEmail());
//			System.out.println("-------------------------");
//		}

		//to update user using User object
//		User user2 = userDao.get(7).orElse(null);
//		user2.setEmail("user3@example.com");
//		userDao.update(user2);

		//===============================================================================================
		//UserDetails

		UserDetailsDao userDetailsDao = new UserDetailsDao();


		//get user by user_id
//		Optional<UserDetails> userDetails = userDetailsDao.get(2);
//		UserDetails user2Details = userDetails.orElse(null);
//		System.out.println("User ID: " + user2Details.getUserId());
//		System.out.println("Job: " + user2Details.getJob());
//		System.out.println("Address: " + user2Details.getAddress());
//		System.out.println("Salary: " + user2Details.getSalary());

		//save
//		UserDetails userDetails = new UserDetails();
//		userDetails.setUserId(9);
//		userDetails.setAddress("St. Deribasivska 13");
//		userDetails.setJob("PM");
//		userDetails.setSalary(120000);
//		userDetailsDao.save(userDetails);

		//update user_details
//		UserDetails userDetails = new UserDetails();
//		userDetails.setUserId(1);
//		userDetails.setAddress("Bul. Academy Voltza 12");
//		userDetails.setJob("Java Developer");
//		userDetails.setSalary(6200);
//		userDetailsDao.update(userDetails);

		//delete
//		userDetailsDao.delete(911);


		//get ALL
//		List<UserDetails> users = userDetailsDao.getAll();
//		System.out.println("List of Users:");
//		for (UserDetails usera : users) {
//			System.out.println("User ID: " + usera.getUserId());
//			System.out.println("Job: " + usera.getJob());
//			System.out.println("Address: " + usera.getAddress());
//			System.out.println("Salary: " + usera.getSalary());
//			System.out.println("-------------------------");
//			}


		//===============================================================================================
		//Products
		ProductsDao productsDao = new ProductsDao();


		//get single product
//		Optional<Products> productsOptional = pd.get(2);
//		Products product = productsOptional.orElse(null);
//		System.out.println("Product ID: " + product.getProductId());
//		System.out.println("Name: " + product.getProductName());
//		System.out.println("Price: " + product.getPrice());

		//get all products
//		List<Products> products = productsDao.getAll();
//		System.out.println("List of Users:");
//		for (Products product : products) {
//			System.out.println("Product ID: " + product.getProductId());
//			System.out.println("Name: " + product.getProductName());
//			System.out.println("Price: " + product.getPrice());
//			System.out.println("-------------------------");
//			}

		//update product
//		Products updatedProduct = new Products(11,"Beef", 220.00);
//		productsDao.update(updatedProduct);

		//delete product
//		productsDao.delete(11);

		//add product
//		Products newProduct = new Products();
//		newProduct.setProductName("Beef");
//		newProduct.setPrice(212.99);
//		productsDao.save(newProduct);

		//===============================================================================================
		ShoppingCartDao shoppingCartDao = new ShoppingCartDao();

		List<ShoppingCart> items = shoppingCartDao.getAllProducts(4);

		for (ShoppingCart item : items) {
			System.out.println("Cart ID: " + item.getCartId());
			System.out.println("User ID: " + item.getUserId());
			System.out.println("Product ID: " + item.getProductId());
			System.out.println("-------------------------");
		}

		System.out.println();

//		ShoppingCart shoppingCart = new ShoppingCart(0, 1,12);
//		shoppingCartDao.save(shoppingCart);

		//delete shopping cart unit
//		shoppingCartDao.delete(1,12);



		//===============================================================================================
		//Orders

		OrdersDao ordersDao = new OrdersDao();

		//get all orders of 1 user
//		List<Orders> orders = ordersDao.get(1);
//		System.out.println("List of Users:");
//		for (Orders order : orders) {
//			System.out.println("Order ID: " + order.getOrderId());
//			System.out.println("User ID: " + order.getUserId());
//			System.out.println("Products list: " + order.getProductList());
//			System.out.println("Total amount: " + order.getTotalAmount());
//			System.out.println("-------------------------");
//		}

		//get all orders
//		List<Orders> orders = ordersDao.getAll();
//		System.out.println("List of Users:");
//		for (Orders order : orders) {
//			System.out.println("Order ID: " + order.getOrderId());
//			System.out.println("User ID: " + order.getUserId());
//			System.out.println("Products list: " + order.getProductList());
//			System.out.println("Total amount: " + order.getTotalAmount());
//			System.out.println("-------------------------");
//		}

		//---------------======================------------------==============--------------===============
		//to form order

//		String s = shoppingCartDao.getStringOfProductsInShoppingCart(1);
//		System.out.println(s);
//
//		double d = shoppingCartDao.calculateTotalSum(1);
//		System.out.println(d);
//
//		ordersDao.saveOrderFromShoppingCart(s,d,1);

//		OrderService.formOrder(9);



	}
}
