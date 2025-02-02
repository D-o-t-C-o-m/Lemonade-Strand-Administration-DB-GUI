package org.mike.domain;

public class Lemonade extends Entity{
private int id;
private String name;
private double totalPrice;

public Lemonade (int id) {
	super.id = id;
}
public Lemonade(int id,String name, double totalPrice) {
	this.name = name;
	super.id = id;
	this.totalPrice = totalPrice;
}
public String getName() {
	return name;
}
public double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}
public void setName(String name) {
	this.name = name;
}

}
