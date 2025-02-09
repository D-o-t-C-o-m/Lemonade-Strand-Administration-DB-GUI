package org.mike.domain;
import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
@Table(name="lemonade")
public class Lemonade extends org.mike.domain.Entity {

@Column(name = "LemonadeID")
private int id;
@Column(name = "Name")
private String name;
@Column(name = "Price")
private double totalPrice;
public Lemonade() {}
public Lemonade (int id) {
	this.id = id;
}
public Lemonade(int id,String name, double totalPrice) {
	this.name = name;
	this.id = id;
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
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


}
