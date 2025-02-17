package org.mike.domain;

import jakarta.persistence.*;

@jakarta.persistence.Entity
@Table(name = "lemonade")
@Cacheable
public class Lemonade extends Entity {
@Id
@Column(name = "lemonadeID")
private int id;

@Column(name = "Name")
private String name;

@Column(name = "Price")
private double price;

public Lemonade(int id, String name, double price) {
	this.id = id;
	this.name = name;
	this.price = price;
}

public Lemonade() {
}

@Override
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}
}