package org.mike.repository;

import org.mike.domain.Lemonade;
import org.mike.exceptions.IDNotUniqueException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LemonadeFileRepository  extends GenericRepository<Lemonade>{
private String filename;

public LemonadeFileRepository(String filename) throws IOException, IDNotUniqueException {
	super();
	this.filename = filename;
	super.fileExistenceCheck(filename);
	loadLemonadesFromFile();
}

@Override
public Lemonade save(Lemonade lemonade) throws IDNotUniqueException, FileNotFoundException {
	Lemonade savedLemonade = super.save(lemonade);
	writeToFile();
	return savedLemonade;
}

@Override
public Lemonade update(Lemonade lemonade) throws FileNotFoundException {
	Lemonade updatedLemonade = super.update(lemonade);
	writeToFile();
	return updatedLemonade;
}

@Override
public void delete(int Id) throws FileNotFoundException {
	super.delete(Id);
	writeToFile();
}

public List<Lemonade> readLemonadesFromFile() {
	List<Lemonade> lemonades = new ArrayList<>();
	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader(filename));
		String line;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String[] parts = line.split(",");
			int id = Integer.parseInt(parts[0]);
			String name = parts[1];
			double price = Double.parseDouble(parts[2]);

			Lemonade lemonade = new Lemonade(id,name,price);
			lemonades.add(lemonade);

		}
		br.close();
	} catch (IOException e) {
		throw new RuntimeException(e);

	}
	return lemonades;
}

private void writeToFile() {
	BufferedWriter bw;
	try {
		bw = new BufferedWriter(new FileWriter(filename));
		bw.write("Lemonade ID|Name|Price");
		bw.newLine();
		Iterable<Lemonade> lemonades = findAll();
		for (Lemonade lemonade : lemonades) {
			String line = lemonade.getId() + "," + lemonade.getName() + ","  + lemonade.getTotalPrice();
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
}

private void loadLemonadesFromFile() throws IDNotUniqueException, IOException {
	List<Lemonade> lemonades = readLemonadesFromFile();
	for (Lemonade lemonade : lemonades) {
		this.save(lemonade);
	}
}

}

