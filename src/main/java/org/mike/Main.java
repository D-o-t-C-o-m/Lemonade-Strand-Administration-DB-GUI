package org.mike;
import org.mike.DAO.productDAO;
import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
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
	DAO<Supplier> supplierDAO = new DAO<>(Supplier.class);
	productDAO productDAO = new productDAO();
	lemonadeDAO lemonadeDAO = new lemonadeDAO();

	System.out.print("25%");
	supplierDAO.preloadCache();
	System.out.print("..50%");
	lemonadeDAO.preloadCache();
	System.out.print("..75%");
	productDAO.preloadCache();
	System.out.print("..100%\n");
}
}
