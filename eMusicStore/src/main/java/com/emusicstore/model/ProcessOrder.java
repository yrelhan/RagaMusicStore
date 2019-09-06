package com.emusicstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;



@Entity
public class ProcessOrder implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private int id;

    private String productname;
    
    private int customerid;
    
    private String customerName;
    
    private String shippingAdress;

    private int productid;

    private int quantity;

    private int status;
    
    @Column(name="status1", columnDefinition ="integer default 5")
    private int status1;

	public ProcessOrder() {
	}

	public ProcessOrder(int id, String productname, int customerid, int productid, int quantity,
                        int status, String customerName, String shippingAdress) {
		this.id = id;
		this.productname = productname;
		this.customerName = customerName;
		this.customerid = customerid;
		this.productid = productid;
		this.quantity = quantity;
		this.shippingAdress =shippingAdress;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getCustomerid() {
		return customerid;	
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getShippingAdress() {
		return shippingAdress;
	}

	public void setShippingAdress(String shippingAdress) {
		this.shippingAdress = shippingAdress;
	}

	public int getStatus1() {
		return status1;
	}

	public void setStatus1(int status1) {
		this.status1 = status1;
	}
}
