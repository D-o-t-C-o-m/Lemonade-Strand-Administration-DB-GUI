package org.mike;
import org.mike.DAO.productDAO;
import org.mike.domain.Supplier;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.service.SupplierServer;
import org.mike.userinterface.UserInterface;
import org.mike.DAO.lemonadeDAO;
import org.mike.DAO.DAO;


public class Main {

public static void main(String[] args) throws IDNotUniqueException {
	//TODO: Loading Screen?
	UserInterface userInterface = new UserInterface();

	precache();

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
