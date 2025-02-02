package org.mike.repository;

import org.mike.domain.Product;
import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductFileRepository extends GenericRepository<Product> {

private String filename;

public ProductFileRepository(String filename) throws IOException, IDNotUniqueException {
	super();
	this.filename = filename;
	super.fileExistenceCheck(filename);
	loadProductsFromFile();
}

@Override
public Product save(Product product) throws IDNotUniqueException, FileNotFoundException {
	Product savedProduct = super.save(product);
	writeToFile();
	return savedProduct;
}

@Override
public Product update(Product product) throws FileNotFoundException {
	Product updatedProduct = super.update(product);
	writeToFile();
	return updatedProduct;
}

@Override
public void delete(int Id) throws FileNotFoundException {
	super.delete(Id);
	writeToFile();
}

public List<Product> readProductsFromFile() {
	List<Product> products = new ArrayList<>();
	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader(filename));
		String line;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] parts = line.split(",");
			int id = Integer.parseInt(parts[0]);
			String name = parts[1];
			String description = parts[2];
			double price = Double.parseDouble(parts[3]);
			int quantity = Integer.parseInt(parts[4]);
			int supplierId = Integer.parseInt(parts[5]);

			Supplier supplier = new Supplier();
			supplier.setId(supplierId);

			Product product = new Product(id, name, description, price, quantity, supplier);
			products.add(product);

		}
		br.close();
	} catch (IOException e) {
		throw new RuntimeException(e);

	}
	return products;
}

private void writeToFile() {
	BufferedWriter bw;
	try {
		bw = new BufferedWriter(new FileWriter(filename));
		bw.write("Product ID|Name|Description|Price|Quantity|Supplier ID");
		bw.newLine();
		Iterable<Product> products = findAll();
		for (Product product : products) {
			String line = product.getId() + "," + product.getName() + "," + product.getDescription() + "," + product.getPrice() + "," + product.getQuantity() + "," + product.getSupplier().getId();
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
}

private void loadProductsFromFile() throws IDNotUniqueException, IOException {
	List<Product> products = readProductsFromFile();
	for (Product product : products) {
		this.save(product);
	}
}

}
