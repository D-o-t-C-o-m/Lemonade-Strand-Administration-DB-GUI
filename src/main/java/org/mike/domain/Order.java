package org.mike.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Order extends Entity {

private Lemonade lemonade;
private int quantity;
private double finalPrice;
private Date date;

public Order(int id, Lemonade lemonade, int quantity, double finalPrice, Date date) {
	super(id);
	this.lemonade = lemonade;
	this.quantity = quantity;
	this.finalPrice = finalPrice;
	this.date = date;
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
