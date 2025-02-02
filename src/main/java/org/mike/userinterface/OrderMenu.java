package org.mike.userinterface;

import org.mike.domain.Order;
import org.mike.dtos.DailySalesDTO;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.exceptions.ValidationException;
import org.mike.service.OrderService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OrderMenu {
private OrderService orderService;
public OrderMenu(OrderService orderService) {
	this.orderService = orderService;
}
public void runOrderOption(Scanner scanner) {
	System.out.println("Placing a new order.");

	Random random = new Random();
	int id = random.nextInt(999);
	System.out.println("You are Order: " + id);
	scanner.nextLine();

	System.out.print("Lemonade id: ");
	int lemonadeId = scanner.nextInt();

	System.out.print("Quantity: ");
	int quantity = scanner.nextInt();

	try {
		Order order = orderService.saveOrder(id, lemonadeId, quantity);
		System.out.printf("The order with ID=%s has been saved \n", order.getId());
	} catch (ValidationException | IDNotUniqueException e) {
		System.out.println("Error with saving the order: " + e.getMessage());
	} catch (FileNotFoundException e) {
		throw new RuntimeException(e);
	}
}

public void runDailyReport(){
		System.out.println("You want to create a daily report.");

		List<DailySalesDTO> report = orderService.getDailyReport();
		for(DailySalesDTO day: report){
			String reportLine = "For the day %s the total items sold %d, for a total of %.2f\n";
			String reportLineFormatted = String.format(reportLine, day.getDayString(), day.getTotalSales(), day.getSalesDollars());
			System.out.println(reportLineFormatted);
		}
	}
}

