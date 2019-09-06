package com.emusicstore.dao;

import com.emusicstore.model.Customer;
import com.emusicstore.model.ProcessOrder;
import com.emusicstore.model.Product;

import java.util.List;
import java.util.Set;

public interface CustomerDao {

    void addCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    Customer getCustomerByUsername (String username);

    /**insert new product as processing with the specified customer
     * @param quantity */
	void setProcessing(Customer customer, Product product, int quantity);

    /**fetch all processing orders for all customers*/
	Set<ProcessOrder> getAllProcessingOrder();

	/**Change status to true of one processOrder*/
	void acceptOrder(int pOrderId);

	void dispatchOrder(int pOrderId);
	
	void deliverOrder(int pOrderId);
	/**fetch data from processOrder given the customer Name*/
	Set<ProcessOrder> getAllProcessingOrderOneCustomer(String customerName);

//	boolean checkorderpurchasing(int customerId, int pOrderId, int productId);

}
