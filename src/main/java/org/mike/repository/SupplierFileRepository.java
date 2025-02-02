package org.mike.repository;

import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierFileRepository extends GenericRepository<Supplier> {
private String filename;

public SupplierFileRepository(String filename) throws FileNotFoundException, IDNotUniqueException {
	super();
	this.filename = filename;
	super.fileExistenceCheck(filename);
	loadSuppliersFromFile();
}

private void loadSuppliersFromFile() throws IDNotUniqueException, FileNotFoundException {
	List<Supplier> suppliers = readSupplierFromFile();
	for (Supplier supplier : suppliers) {
		this.save(supplier);
	}

}

public List<Supplier> readSupplierFromFile() {
	List<Supplier> suppliers = new ArrayList<>();
	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader(filename));
		String line;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] parts = line.split("\\|");
			int id = Integer.parseInt(parts[0]);
			String name = parts[1];
			String email = parts[2];
			Supplier supplier = new Supplier(id, name, email);
			suppliers.add(supplier);
		}
		br.close();
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
	return suppliers;
}

public void writeToFile() {
	BufferedWriter bw;
	try {
		bw = new BufferedWriter(new FileWriter(filename));
		bw.write("ID|Name|Email");
		bw.newLine();

		Iterable<Supplier> suppliers = findAll();

		for (Supplier supplier : suppliers) {
			String line = supplier.getId() + "|" + supplier.getName() + "|" + supplier.getEmail();
			bw.write(line);
			bw.newLine();
		}
		bw.close();

	} catch (IOException e) {
		throw new RuntimeException(e);
	}
}

@Override
public Supplier save(Supplier supplier) throws IDNotUniqueException, FileNotFoundException {
	Supplier savedSupplier = super.save(supplier);
	writeToFile();
	return savedSupplier;
}

@Override
public Supplier update(Supplier supplier) throws FileNotFoundException {
	Supplier updatedSupplier = super.update(supplier);
	writeToFile();
	return updatedSupplier;
}

@Override
public void delete(int SupplierID) throws FileNotFoundException {
	super.delete(SupplierID);
	writeToFile();
}

}
