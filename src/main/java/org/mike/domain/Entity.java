package org.mike.domain;

import jakarta.persistence.MappedSuperclass;
import org.mike.userinterface.Identifiable;

@MappedSuperclass
public abstract class Entity implements Identifiable {

}
