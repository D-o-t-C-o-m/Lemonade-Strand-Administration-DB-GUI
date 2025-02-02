package org.mike.domain;

public class Product extends Entity{
private String name;
private String description;
private int quantity;
private double price;
private Supplier supplier;

public Product(int id) {
	super.id = id;
}

public Product(int id, String name, String description, double price, int quantity, Supplier supplier) {
	this.name = name;
	this.quantity = quantity;
	this.price = price;
	this.supplier = supplier;
	super.id = id;
	this.description = description;
}


public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Supplier getSupplier() {
	return supplier;
}

public void setSupplier(Supplier supplier) {
	this.supplier = supplier;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

@Override
public String toString() {
	return "Product{" +
			"id=" + id +
			", name='" + name + '\'' +
			", description='" + description + '\'' +
			", price=" + price +
			", quantity=" + quantity +
			'}';
}
}
