package org.mike.userinterface;

import org.mike.Utils.IdGenerator;
import org.mike.Utils.ServerManager;
import org.mike.domain.Lemonade;
import org.mike.domain.Order;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.Utils.DAOManager;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OrderMenu {
private final IdGenerator<Order> idGenerator;
private final Date date = new Date();
private final ServerManager serverManager = new ServerManager(new DAOManager());

public OrderMenu() {
	DAOManager daoManager = new DAOManager();
	this.idGenerator = new IdGenerator<>(daoManager.getOrderDAO());
}

public void runOrderOption(Scanner scanner) {
	System.out.println("Placing a new order.");

	int id = idGenerator.generateId();
	System.out.println("You are Order: " + id);
	scanner.nextLine();

	System.out.print("Lemonade id: ");
	int lemonadeId = scanner.nextInt();

	System.out.print("Quantity: ");
	int quantity = scanner.nextInt();

	Lemonade lemonade = serverManager.getLemonadeService().findById(lemonadeId);
	Order order = new Order(id, lemonade, quantity, lemonade.getPrice() * quantity, date);
	try {
		order = serverManager.getOrderService().save(order);
		System.out.printf("\nThe order with ID = %s has been saved \n", order.getId());
		System.out.println("The price is: " + order.getFinalPrice());
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the order: " + e.getMessage());
	}
}

public void runDailyReport() {
	System.out.println("Creating a Sales Report.");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	List<Order> allOrders = serverManager.getOrderService().findAll();

	Map<String, List<Order>> ordersByDate = allOrders.stream()
			.collect(Collectors.groupingBy(order -> dateFormat.format(order.getDate())));

	ordersByDate.forEach((date, orders) -> {
		int totalUnits = orders.stream().mapToInt(Order::getQuantity).sum();
		double totalSales = orders.stream().mapToDouble(Order::getFinalPrice).sum();

		String reportLine = "For the day %s the total items sold %d, for a total of %.2f\n";
		String reportLineFormatted = String.format(reportLine, date, totalUnits, totalSales);
		System.out.println(reportLineFormatted);
	});
}
}