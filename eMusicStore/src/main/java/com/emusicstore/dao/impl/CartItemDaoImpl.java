package com.emusicstore.dao.impl;

import com.emusicstore.dao.CartItemDao;
import com.emusicstore.model.Cart;
import com.emusicstore.model.CartItem;
import com.emusicstore.model.ProcessOrder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CartItemDaoImpl implements CartItemDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void addCartItem(CartItem cartItem) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cartItem);
        session.flush();
    }

    public void removeCartItem (CartItem cartItem) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cartItem);
        session.flush();
    }

    
    public void removeAllCartItemsCart(Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();
        
        Session session = sessionFactory.getCurrentSession();
        int cusid=cart.getCustomer().getCustomerId();
        
        for (CartItem item : cartItems) {
        	int pid=item.getProduct().getProductId();
        	Query query=session.createQuery("from ProcessOrder where customerid= ? and productid= ?");
        	query.setInteger(0, cusid);
        	query.setInteger(1, pid);
        	ProcessOrder pOrder1 =(ProcessOrder) query.uniqueResult();
        	if(pOrder1!=null){
            	pOrder1.setStatus1(4);	
            	session.saveOrUpdate(pOrder1);
            }
        	/*Query query1=session.createQuery("from Product where productid= ?");
        	query1.setInteger(0, pid);
        	Product prod =(Product) query1.uniqueResult();
        	if(prod!=null){
        		int quant=item.getQuantity();
        		int quant1=prod.getUnitInStock();
        		prod.setUnitInStock(quant1-quant);
        		session.saveOrUpdate(prod);
        	}*/
        	//query.executeUpdate();
            removeCartItem(item);
        }
    }
    
    public void removeAllCartItems(Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem item : cartItems) {
        	//int pid=item.getProduct().getProductId();
        	//Query query=session.createQuery("Update processorder p set p.status=5 where customerid=? and productid=?");
            removeCartItem(item);
        }
    }

    public CartItem getCartItemByProductId (int productId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CartItem where productId = ?");
        query.setInteger(0, productId);
        session.flush();

        return (CartItem) query.uniqueResult();
    }
}
