package org.mike.repository;

import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;

import java.io.FileNotFoundException;

public class SupplierRepositoryTest {

public void shouldSaveOneElement_whenSaveIsCalled() throws IDNotUniqueException, FileNotFoundException {
	GenericRepository<Supplier> supplierRepository = new GenericRepository<Supplier>();
	Supplier supplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");

	Supplier firstSavedSuppler = supplierRepository.save(supplierToSave);

	assert firstSavedSuppler != null;
	assert firstSavedSuppler.getId() == 1;
	assert firstSavedSuppler.getName().equals("Lemonades");
	assert supplierRepository.findById(2) == null;
}

public void shouldSaveTwoElements_whenSaveIsCalled() throws IDNotUniqueException, FileNotFoundException {
	GenericRepository<Supplier> supplierRepository = new GenericRepository<Supplier>();

	Supplier firstSupplierToSave = new Supplier(1, "Lemonades", "contact@lemonades.com");
	Supplier firstSavedSuppler = supplierRepository.save(firstSupplierToSave);

	Supplier secondSupplierToSave = new Supplier(2, "Water", "contact@Water.com");
	Supplier secondSavedSuppler = supplierRepository.save(secondSupplierToSave);

	assert firstSavedSuppler.getId() == 1;
	assert firstSavedSuppler.getName().equals("Lemonades");
	assert supplierRepository.findById(3) == null;

	assert secondSavedSuppler != null;
	assert secondSavedSuppler.getId() == 2;
	assert secondSavedSuppler.getName().equals("Water");

	assert supplierRepository.findById(1) != null;
	assert supplierRepository.findById(2) == null;
}


public void shouldUpdateSupplier_whenUpdateIsCalled() throws IDNotUniqueException, FileNotFoundException {
	GenericRepository<Supplier> supplierRepository = new GenericRepository<Supplier>();

	Supplier supplierToUpdate = new Supplier(1, "Lemonades", "contact@lemonades.com");
	supplierRepository.save(supplierToUpdate);

	supplierToUpdate.setName("Hamburger");
	supplierToUpdate.setEmail("hamburger@hamburger.com");

	Supplier updatedSupplier = supplierRepository.update(supplierToUpdate);
	assert updatedSupplier != null;
	assert updatedSupplier.getId() == 1;
	assert updatedSupplier.getName().equals("Hamburger");
	assert updatedSupplier.getEmail().equals("hamburger@hamburger.com");


}

public void shouldDeleteSupplier_whenDeleteIsCalled() throws IDNotUniqueException, FileNotFoundException {
	GenericRepository<Supplier> supplierRepository = new GenericRepository<Supplier>();
	Supplier supplierToDelete = new Supplier(1, "Water", "contact@Water.com");

	supplierRepository.save(supplierToDelete);
	supplierRepository.delete(supplierToDelete.getId());

	Supplier deletedSupplier = supplierRepository.findById(1);

	assert deletedSupplier != null;

}

public void shouldFindAllSupplier_whenFindIsCalled() throws IDNotUniqueException, FileNotFoundException {
	GenericRepository<Supplier> supplierRepository = new GenericRepository<Supplier>();
	Supplier supplierToFind = new Supplier(1, "Water", "contact@Water.com");
	supplierRepository.save(supplierToFind);
	Supplier supplierToFind2 = new Supplier(2, "Water", "contact@Water.com");
	supplierRepository.save(supplierToFind2);

	Supplier firstSupplier = supplierRepository.findById(1);
	Supplier secondSupplier = supplierRepository.findById(2);

	assert firstSupplier.getId() == 1;
	assert secondSupplier.getId() == 2;
}

public void testAllRepository() throws IDNotUniqueException, FileNotFoundException {
	shouldSaveOneElement_whenSaveIsCalled();
	shouldSaveTwoElements_whenSaveIsCalled();
	shouldUpdateSupplier_whenUpdateIsCalled();
	shouldDeleteSupplier_whenDeleteIsCalled();
	shouldFindAllSupplier_whenFindIsCalled();

}
}
