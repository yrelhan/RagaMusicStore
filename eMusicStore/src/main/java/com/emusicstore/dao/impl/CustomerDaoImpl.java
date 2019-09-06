package com.emusicstore.dao.impl;

import com.emusicstore.dao.CustomerDao;
import com.emusicstore.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CustomerDaoImpl implements CustomerDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImpl(SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }
    public void addCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        customer.getBillingAddress().setCustomer(customer);
        customer.getShippingAddress().setCustomer(customer);

        session.saveOrUpdate(customer);
        session.saveOrUpdate(customer.getBillingAddress());
        session.saveOrUpdate(customer.getShippingAddress());

        Users newUser = new Users();
        newUser.setUsername(customer.getUsername());
        newUser.setPassword(customer.getPassword());
        newUser.setEnabled(true);
        newUser.setCustomerId(customer.getCustomerId());

        Authorities newAuthority = new Authorities();
        newAuthority.setUsername(customer.getUsername());
        newAuthority.setAuthority("ROLE_USER");
        session.saveOrUpdate(newUser);
        session.saveOrUpdate(newAuthority);

        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        customer.setCart(newCart);
        session.saveOrUpdate(customer);
        session.saveOrUpdate(newCart);

    }

    public Customer getCustomerById (int customerId) {
        Session session = sessionFactory.getCurrentSession();
        return (Customer) session.get(Customer.class, customerId);
    }

    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Customer");
        List<Customer> customerList = query.list();

        return customerList;
    }

    public Customer getCustomerByUsername (String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Customer where username = ?");
        query.setString(0, username);

        return (Customer) query.uniqueResult();
    }

	@Override
	public void setProcessing(Customer customer, Product product, int quantity) {
        Session session = sessionFactory.getCurrentSession();
        /*Query query = session.createQuery("from ProcessOrder where productid = ? and customerid = ?");
        query.setInteger(0, product.getProductId());
        query.setInteger(1, customer.getCustomerId());
        ProcessOrder pOrder =(ProcessOrder) query.uniqueResult();
        if(pOrder==null){*/
        ProcessOrder _pOrder = new ProcessOrder();
        _pOrder.setCustomerid(customer.getCustomerId());
        _pOrder.setShippingAdress(customer.getShippingAddress().toString());
        _pOrder.setCustomerName(customer.getCustomerName());
        _pOrder.setProductid(product.getProductId());
        _pOrder.setProductname(product.getProductName());
        _pOrder.setStatus(0);
        _pOrder.setQuantity(quantity);
        session.saveOrUpdate(_pOrder);
        /*}
        else{
        	pOrder.setQuantity(pOrder.getQuantity()+1);	
        	session.saveOrUpdate(pOrder);
        }*/
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ProcessOrder> getAllProcessingOrder() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcessOrder");
        return (Set<ProcessOrder>) query.list().parallelStream().collect(Collectors.toSet());
	}

	@Override
	public void acceptOrder(int pOrderId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcessOrder where id = ?");
        query.setInteger(0, pOrderId);
        ProcessOrder pOrder =(ProcessOrder) query.uniqueResult();
        if(pOrder!=null){
        	pOrder.setStatus(1);	
        	session.saveOrUpdate(pOrder);
        }
	}
	
	@Override
	public void dispatchOrder(int pOrderId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcessOrder where id = ?");
        query.setInteger(0, pOrderId);
        ProcessOrder pOrder =(ProcessOrder) query.uniqueResult();
        if(pOrder!=null){
        	pOrder.setStatus(2);	
        	session.saveOrUpdate(pOrder);
        }
	}
	@Override
	public void deliverOrder(int pOrderId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcessOrder where id = ?");
        query.setInteger(0, pOrderId);
        ProcessOrder pOrder =(ProcessOrder) query.uniqueResult();
        if(pOrder!=null){
        	pOrder.setStatus(3);	
        	session.saveOrUpdate(pOrder);
        }
	}

	@Override
	public Set<ProcessOrder> getAllProcessingOrderOneCustomer(String customerName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcessOrder where customerName = ?");
        query.setString(0, customerName);
        return (Set<ProcessOrder>) query.list().parallelStream().collect(Collectors.toSet());
	}
/*	@Autowired
	CartItemDao _cartitemdao;
	@Override
	public boolean checkorderpurchasing(int customerId, int pOrderId, int productId) {
		CartItem _cartitem = _cartitemdao.getCartItemByProductId(productId);
		if(_cartitem!=null ){
		//test
			return true;
		}
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CartItem0 where cartItemId = ?");
		//check my messages in teamviewer
        query.setInteger(0,)
        
		return false;
	}
*/}
