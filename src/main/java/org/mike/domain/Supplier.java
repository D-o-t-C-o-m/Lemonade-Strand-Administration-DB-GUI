package org.mike.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@Table (name="supplier_info")
@Cacheable
public class Supplier extends org.mike.domain.Entity {

@Id
@Column(name = "supplierID")
private int supplierID;
@Column(name = "supplierName")
private String name;
@Column(name = "supplierEmail")
private String email;


public Supplier(Integer id, String name, String email) {
	this.supplierID = id;
	this.name = name;
	this.email = email;
}

public Supplier() {

}

public int getId() {
	return supplierID;
}
public void setId(int id) {
	this.supplierID = id;
}
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}


@Override
public boolean equals(Object o) {
	if (o == null || getClass() != o.getClass()) return false;
	Supplier supplier = (Supplier) o;
	return getId() == supplier.getId() && Objects.equals(getName(), supplier.getName()) && Objects.equals(getEmail(), supplier.getEmail());
}

@Override
public String toString() {
	return "Supplier{id=" + supplierID + ", name='" + name + '\'' + ", email='" + email + "'" + "}";
}
}
