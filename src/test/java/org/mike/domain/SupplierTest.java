package org.mike.domain;


public class SupplierTest {
public void ShouldGetCorrectValues_whenConstructorIsCalled() {
	Supplier supplier = new Supplier(1, "Lemonades", "Contact@lemonade.com");
	assert supplier.getId() == 1;
	assert supplier.getName().equals("Lemonades");
	assert supplier.getEmail().equals("Contact@lemonade.com");
}

public void shouldSetCorrectValues_whenSettersAreUse(){
	Supplier supplier = new Supplier(1, "Lemon","Lemon@ades.com");
	supplier.setId(2);
	supplier.setName("Lemonades");
	supplier.setEmail("Contact@lemonade.com");

	assert supplier.getId() == 2;
	assert supplier.getName().equals("Lemonades");
	assert supplier.getEmail().equals("Contact@lemonade.com");


	}
public void testAllDomain() {
	shouldSetCorrectValues_whenSettersAreUse();
	ShouldGetCorrectValues_whenConstructorIsCalled();
}
}
