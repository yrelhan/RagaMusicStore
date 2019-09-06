package com.emusicstore.controller;

import com.emusicstore.model.Cart;
import com.emusicstore.model.CartItem;
import com.emusicstore.model.Customer;
import com.emusicstore.model.Product;
import com.emusicstore.service.CartItemService;
import com.emusicstore.service.CartService;
import com.emusicstore.service.CustomerService;
import com.emusicstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/cart")
public class CartResources {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/{cartId}")
    public @ResponseBody
    Cart getCartById (@PathVariable(value = "cartId") int cartId) {
        return cartService.getCartById(cartId);
    }

    @RequestMapping(value = "/add/{customerId}/{pOrderId}/{productId}/{delivered}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem (@PathVariable(value ="customerId") int customerId,@PathVariable(value ="pOrderId") int pOrderId,
    		@PathVariable(value ="productId") int productId,@PathVariable(value="delivered") int delivered) {

    	if(delivered==0)
    	{
    		customerService.acceptOrder(pOrderId);
    		Customer customer = customerService.getCustomerById(customerId);
        	Cart cart = customer.getCart();
            Product product = productService.getProductById(productId);
            List<CartItem> cartItems = cart.getCartItems();
            for (int i=0; i<cartItems.size(); i++) {
                if(product.getProductId()==cartItems.get(i).getProduct().getProductId()){
                    CartItem cartItem = cartItems.get(i);
                    cartItem.setQuantity(cartItem.getQuantity()+1);
                    cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
                    cartItemService.addCartItem(cartItem);
                    return;
                }
            }

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
            cartItem.setCart(cart);
            cartItemService.addCartItem(cartItem);
    		
    	}
    	
    	//System.out.println("here ");
    	
    	
    	if(delivered==1)
    	{
    		customerService.dispatchOrder(pOrderId);
    		return;
    	}
    	
    	if(delivered==2)
    	{
    		customerService.deliverOrder(pOrderId);
    		return;
    	}
    	
    	/*else {
    		System.out.println("here 2 ");
    	}*/
    	//System.out.println("customerId "+customerId);
        /*Customer customer = customerService.getCustomerById(customerId);
    	Cart cart = customer.getCart();
        Product product = productService.getProductById(productId);
        List<CartItem> cartItems = cart.getCartItems();
        for (int i=0; i<cartItems.size(); i++) {
            if(product.getProductId()==cartItems.get(i).getProduct().getProductId()){
                CartItem cartItem = cartItems.get(i);
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
                cartItemService.addCartItem(cartItem);
                return;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setTotalPrice(product.getProductPrice()*cartItem.getQuantity());
        cartItem.setCart(cart);
        cartItemService.addCartItem(cartItem);*/
    }
    
    @RequestMapping(value = "/processing/{productId}/{quantity}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void process (@PathVariable(value ="productId") int productId, @PathVariable(value ="quantity") int quantity,
    		@AuthenticationPrincipal User activeUser) {

        Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
        if(customer!=null){
        Product product = productService.getProductById(productId);
        customerService.setProcessing(customer,product,quantity);
        }
    }
    
    
    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem (@PathVariable(value = "productId") int productId) {
        CartItem cartItem = cartItemService.getCartItemByProductId(productId);
        
        cartItemService.removeCartItem(cartItem);

    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void clearCart(@PathVariable(value = "cartId") int cartId) {
        Cart cart = cartService.getCartById(cartId);
        cartItemService.removeAllCartItems(cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload.")
    public void handleClientErrors (Exception e) {}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error.")
    public void handleServerErrors (Exception e) {}
}
