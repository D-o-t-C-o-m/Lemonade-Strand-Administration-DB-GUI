package org.mike;

import org.mike.domain.SupplierTest;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.repository.ProductFileRepositoryTest;
import org.mike.repository.SupplierFileRepositoryTest;
import org.mike.repository.SupplierRepositoryTest;
import org.mike.service.ProductServiceTest;
import org.mike.service.SupplierServiceTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainTest {
public static void main(String[] args) {
	runAllTests();
}

public static void runAllTests() {
	try {
		System.out.println("Running Supplier tests");
		SupplierTest supplierTest = new SupplierTest();
		supplierTest.testAllDomain();

		System.out.println("Running Supplier Repository tests");
		SupplierRepositoryTest supplierRepository = new SupplierRepositoryTest();
		supplierRepository.testAllRepository();

		System.out.println("Running Supplier File Repository tests");
		SupplierFileRepositoryTest supplierFileRepository = new SupplierFileRepositoryTest("test-supplier.csv");
		supplierFileRepository.testAllSupplierFileRepository();

		System.out.println("Running Supplier Service tests");
		SupplierServiceTest supplierServiceTest = new SupplierServiceTest();
		supplierServiceTest.testAllService();

		System.out.println("Running Product File Repository tests");
		ProductFileRepositoryTest productFileRepositoryTest = new ProductFileRepositoryTest("test-product.csv");
		productFileRepositoryTest.testAllProductFileRepository();

		System.out.println("Running Product Service tests");
		ProductServiceTest productServiceTest = new ProductServiceTest();
		productServiceTest.testAllProductService();


		System.out.println("All tests have run successfully");
	} catch (ValidationException | IDNotUniqueException | FileNotFoundException e) {
		System.out.println("Test failed because public S setName() {this.name = name;}" + e.getMessage());
	} catch (IOException e) {
		throw new RuntimeException(e);
	}

}
}
