package org.mike.domain;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Cacheable
@Table(name="products")
public class Product extends org.mike.domain.Entity {
@Id
//@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "productID")
private int productID;
@Column(name = "productName")
private String name;
@Column(name = "productDescription")
private String description;
@Column(name = "productQty")
private int quantity;
@Column(name = "productCost")
private double price;

@ManyToOne(cascade=CascadeType.ALL)
@JoinColumn(name = "supplierId")
private Supplier supplier;

//public Product(int id) {
//	this.productID = id;
//}

public Product(int id, String name, String description, double price, int quantity, Supplier supplier) {
	this.name = name;
	this.quantity = quantity;
	this.price = price;
	this.supplier = supplier;
	this.productID = id;
	this.description = description;
}

public Product() {

}

public void setProductID(int productID) {
	this.productID = productID;
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
			"id=" + this.productID +
			", name='" + name + '\'' +
			", description='" + description + '\'' +
			", price=" + price +
			", quantity=" + quantity +
			'}';
}

public int getId() {
	return productID;
}
}
