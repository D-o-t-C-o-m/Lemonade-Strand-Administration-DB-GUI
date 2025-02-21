package org.mike;

import org.mike.exceptions.IDNotUniqueException;
import org.mike.userinterface.UserInterface;
import org.mike.Utils.DAOManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;


public class Main {

public static void main(String[] args) throws IDNotUniqueException {
	System.out.println("Welcome to the Lemonade Stand Administration App. \n\n");
	System.out.println("Now Loading...");
	File logDir = new File("logs");
	if (!logDir.exists()) {
		logDir.mkdir();
		System.out.println("Created logs directory" + logDir.getAbsolutePath());
	}
	try {
		LogManager.getLogManager().readConfiguration(
				Main.class.getClassLoader().getResourceAsStream("logging.properties")
		);
	} catch (IOException e) {
		System.err.println("Could not load logging.properties file");
	}
	precache();

	UserInterface userInterface = new UserInterface();
	//GraphicalUI graphicalUI = new GraphicalUI();

	userInterface.runMenu();
}
public static void precache(){
	DAOManager daoManager = new DAOManager();
	System.out.print("10%..");
	daoManager.getLemonadeDAO().preloadCache();
	System.out.print("25%");
	daoManager.getOrderDAO().preloadCache();
	System.out.print("..50%");
	daoManager.getSupplierDAO().preloadCache();
	System.out.print("..75%");
	daoManager.getProductDAO().preloadCache();
	System.out.print("..100%\n");
}
}
