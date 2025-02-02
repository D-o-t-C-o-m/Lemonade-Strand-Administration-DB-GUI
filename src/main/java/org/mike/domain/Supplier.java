package org.mike.domain;

import java.util.Objects;

public class Supplier extends Entity{
private String name;
private String email;

public Supplier() {
}

public Supplier(Integer id, String name, String email) {
	super.id = id;
	this.name = name;
	this.email = email;
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
	return "Supplier{id=" + id + ", name='" + name + '\'' + ", email='" + email + "'" + "}";
}
}
