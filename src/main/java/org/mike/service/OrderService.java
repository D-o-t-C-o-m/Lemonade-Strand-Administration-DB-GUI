package org.mike.service;

import org.mike.domain.Lemonade;
import org.mike.domain.LemonadeRecipe;
import org.mike.domain.Product;
import org.mike.dtos.DailySalesDTO;
import org.mike.exceptions.ValidationException;
import org.mike.repository.OrderFileRepository;
import org.mike.domain.Order;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderService {

private final OrderFileRepository orderFileRepository;
private final ProductService productService;
private final LemonadeService lemonadeService;

public OrderService(OrderFileRepository orderFileRepository, LemonadeService lemonadeService, ProductService productService) {
	this.orderFileRepository = orderFileRepository;
	this.productService = productService;
	this.lemonadeService = lemonadeService;
}

public Order saveOrder (int id, int lemonadeId, int orderQuantity) throws FileNotFoundException {
	Lemonade lemonade = lemonadeService.findById(lemonadeId);
	List<LemonadeRecipe> recipe = lemonadeService.findLemonadeRecipe(lemonadeId);

	boolean isEnoughStock = isEnoughInStockForOrder(recipe, orderQuantity);
	if (isEnoughStock) {
		updateProductStock(recipe, orderQuantity);
		Order order = new Order (id, lemonade, orderQuantity, orderQuantity * lemonade.getTotalPrice(), new Date());
		return orderFileRepository.save(order);

	} else {
		throw new ValidationException("Not enough stock for order");
	}

}

private void updateProductStock(List<LemonadeRecipe> recipe, int orderQuantity) throws ValidationException, FileNotFoundException {
	for (LemonadeRecipe lemonadeRecipe : recipe) {
		for (Map.Entry<Product, Integer> entry : lemonadeRecipe.getProductQuantities().entrySet()) {
			Product product = entry.getKey();
			int requiredQuantity = entry.getValue() * orderQuantity;

			if (requiredQuantity <= product.getQuantity()) {
				int newQuantity = product.getQuantity() - requiredQuantity;
				product.setQuantity(newQuantity);
				productService.updateProduct(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getSupplier().getId());
			} else {
				throw new ValidationException("Not enough stock for product " + product.getName());
			}
		}
	}
}

private boolean isEnoughInStockForOrder(List<LemonadeRecipe> recipe, int orderQuantity) {
	for (LemonadeRecipe lemonadeRecipe : recipe) {
		for (Map.Entry<Product, Integer> entry : lemonadeRecipe.getProductQuantities().entrySet()) {
			Product product = entry.getKey();
			int productQuantityRequired = entry.getValue() * orderQuantity;
			if (product.getQuantity() < productQuantityRequired) {
				return false;
			}
		}
	}
	return true;
}
public List<DailySalesDTO> getDailyReport(){
	List<DailySalesDTO> report = new ArrayList<>();
	Iterable<Order> orders = orderFileRepository.findAll();
	for (Order order : orders) {
		Date orderDate = order.getDate();
		boolean alreadyAddedDay = false;
		for (DailySalesDTO day : report) {
			if (day.getDay().equals(orderDate)) {
				alreadyAddedDay = true;
				day.setSalesDollars(day.getSalesDollars() + order.getFinalPrice());
				day.setTotalSales(day.getTotalSales() + order.getQuantity());
			}
		}
		if (!alreadyAddedDay) {
			report.add(new DailySalesDTO(order.getDate(),order.getQuantity(),order.getFinalPrice()));
		}
	} return report;
}
}