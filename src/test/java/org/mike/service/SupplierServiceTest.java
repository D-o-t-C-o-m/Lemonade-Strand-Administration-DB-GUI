package org.mike.service;

import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.repository.GenericRepository;


import java.io.FileNotFoundException;

public class SupplierServiceTest {
private SupplierService supplierService;


private void setUp() {
	GenericRepository<Supplier> supplierRepository = new GenericRepository<Supplier>();
	supplierService = new SupplierService(supplierRepository);
}

public void shouldSaveSupplier_whenSavedIsCalled() throws ValidationException, IDNotUniqueException, FileNotFoundException {
	setUp();

	Supplier savedSupplier = supplierService.saveSupplier(1, "Lemonades", "Contact@lemonades.com");

	assert savedSupplier != null;
	assert savedSupplier.getId() == 1;
	assert savedSupplier.getName().equals("Lemonades");
	assert supplierService.findById(1).getName().equals("Lemonades");
}

public void shouldUpdateSupplier_whenUpdateIsCalled() throws FileNotFoundException {
	setUp();
	Supplier supplierToUpdate = new Supplier(1, "Lemonades", "Contact@lemonades.com");
	Supplier updatedSupplier = supplierService.updateSupplier(1, "hom", "Hom@Burgher");

	assert updatedSupplier != null;
	assert updatedSupplier.getId() == 1;
	assert updatedSupplier.getName().equals("Hom");
	assert updatedSupplier.getEmail().equals("hom@Burgher");

}

public void shouldDeleteSupplier_whenDeletedIsCalled() throws ValidationException, IDNotUniqueException, FileNotFoundException {
	setUp();
	Supplier supplierToDelete = supplierService.saveSupplier(1, "Lemonades", "Contact@lemonades.com");
	supplierService.deleteSupplier(supplierToDelete.getId());

	assert supplierService.findById(1) == null;
}

public void shouldFindAllSupplier_whenFindAllIsCalled() throws ValidationException, IDNotUniqueException, FileNotFoundException {
	setUp();
	supplierService.saveSupplier(1, "Lemonades", "Contact@lemonades.com");
	supplierService.saveSupplier(2, "Leo nades", "Coct@leonades.com");

	Supplier firstSupplier = supplierService.findById(1);
	Supplier secondSupplier = supplierService.findById(2);

	assert firstSupplier.getId() == 1;
	assert secondSupplier.getId() == 2;

}

public void shouldNotSaveTheElement_whenWeAddNotUniqueElement() throws IDNotUniqueException {
	GenericRepository<Supplier> supplierRepository = new GenericRepository<Supplier>();
	Supplier firstSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");
	Supplier secondSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");

	try {
		Supplier firstSavedSupplier = supplierRepository.save(firstSupplierToSave);
		Supplier secondSavedSupplier = supplierRepository.save(secondSupplierToSave);
		assert false;
	} catch (IDNotUniqueException e) {
		assert true;
	} catch (FileNotFoundException e) {
		throw new RuntimeException(e);
	}
}

public void testAllService() throws IDNotUniqueException, ValidationException, FileNotFoundException {
	shouldSaveSupplier_whenSavedIsCalled();
	shouldUpdateSupplier_whenUpdateIsCalled();
	shouldDeleteSupplier_whenDeletedIsCalled();
	shouldFindAllSupplier_whenFindAllIsCalled();
	shouldNotSaveTheElement_whenWeAddNotUniqueElement();
}
}
