package com.emusicstore.controller;

import com.emusicstore.model.Cart;
import com.emusicstore.model.Customer;
import com.emusicstore.model.CustomerOrder;
import com.emusicstore.model.ProcessOrder;
import com.emusicstore.service.CartService;
import com.emusicstore.service.CustomerOrderService;
import com.emusicstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @RequestMapping("/{cartId}")
    public String createOrder(@PathVariable("cartId") int cartId) {
    	
        CustomerOrder customerOrder = new CustomerOrder();
        Cart cart=cartService.getCartById(cartId);
        customerOrder.setCart(cart);

        Customer customer = cart.getCustomer();
        customerOrder.setCustomer(customer);
        customerOrder.setBillingAddress(customer.getBillingAddress());
        customerOrder.setShippingAddress(customer.getShippingAddress());

        customerOrderService.addCustomerOrder(customerOrder);

        return "redirect:/checkout?cartId="+cartId;
    }
    
    @RequestMapping("/list")
    public String getOrders(@AuthenticationPrincipal User activeUser, ModelMap model) {
        Customer customer = customerService.getCustomerByUsername (activeUser.getUsername());
        Set<ProcessOrder> pOrders =customerService.getAllProcessingOrderOneCustomer(customer.getCustomerName());
        if(pOrders!=null)
        model.addAttribute("pOrders", pOrders);
    	return "order_Management_customer";
    }

    	
}
