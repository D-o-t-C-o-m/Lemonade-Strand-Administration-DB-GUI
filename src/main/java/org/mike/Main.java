package org.mike;
import org.mike.DAO.productDAO;
import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.service.SupplierServer;
import org.mike.userinterface.UserInterface;
import org.mike.DAO.lemonadeDAO;
import org.mike.DAO.DAO;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;


public class Main {

public static void main(String[] args) throws IDNotUniqueException {
	System.out.println("Now Loading...");
	File logDir = new File("logs");
	if (!logDir.exists()) {
		logDir.mkdir();
	}
	try {
		LogManager.getLogManager().readConfiguration(
				Main.class.getClassLoader().getResourceAsStream("logging.properties")
		);
	} catch (IOException e) {
		System.err.println("Could not load logging.properties file");
	}
	precache();
	//TODO: Loading Screen?
	UserInterface userInterface = new UserInterface();
	//GraphicalUI graphicalUI = new GraphicalUI();
	System.out.println("Welcome to the Lemonade Stand Administration App.");
	userInterface.runMenu();
}
public static void precache(){
	lemonadeDAO lemonadeDAO = new lemonadeDAO();
	lemonadeDAO.preloadCache();
	productDAO productDAO = new productDAO();
	productDAO.preloadCache();
	DAO<Supplier> supplierDAO = new DAO<>(Supplier.class);
	supplierDAO.preloadCache();
}
}
