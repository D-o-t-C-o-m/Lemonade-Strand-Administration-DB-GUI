package org.mike;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.userinterface.UserInterface;

import java.io.IOException;


public class Main {

public static void main(String[] args) throws IOException, IDNotUniqueException {
	//TODO: Make a method that runs all the server fetches to cache the data before the interface starts
	//TODO: Loading Screen?

	UserInterface userInterface = new UserInterface();

	//GraphicalUI graphicalUI = new GraphicalUI();
	System.out.println("Welcome to the Lemonade Stand Administration App.");
	userInterface.runMenu();
}

}
