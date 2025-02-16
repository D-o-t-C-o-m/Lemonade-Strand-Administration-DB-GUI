package org.mike.domain;

import jakarta.persistence.*;

import java.util.Date;
@jakarta.persistence.Entity
@Cacheable
@Table(name="orders")
public class Order extends Entity {
@Id
@Column(name="ID")
private int id;
@Column(name="Lemonade")
private Lemonade lemonade;
@Column(name="Quantity")
private int quantity;
@Column(name="FinalPrice")
private double finalPrice;
@Column(name="Date")
private Date date;

public Order(int id, Lemonade lemonade, int quantity, double finalPrice, Date date) {
	this.id = id;
	this.lemonade = lemonade;
	this.quantity = quantity;
	this.finalPrice = finalPrice;
	this.date = date;
}

@Override
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getQuantity() {
	return quantity;
}
public double getFinalPrice() {
	return finalPrice;
}
public Date getDate() {
	return date;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public void setFinalPrice(double finalPrice) {
	this.finalPrice = finalPrice;
}
public void setDate(Date date) {
	this.date = date;
}
public Lemonade getLemonade() {
	return lemonade;
}
public void setLemonade(Lemonade lemonade) {
	this.lemonade = lemonade;
}
}
