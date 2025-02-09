package org.mike.domain;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity {

protected int id;

// Constructor with id
public Entity(int id) {
	this.id = id;
}

// Default constructor
public Entity() {}

// Getter and setter for id
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
}
