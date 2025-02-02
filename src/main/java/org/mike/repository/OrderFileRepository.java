package org.mike.repository;

import org.mike.domain.Lemonade;
import org.mike.domain.Order;
import org.mike.exceptions.IDNotUniqueException;
import org.mike.service.LemonadeService;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderFileRepository extends GenericRepository<Order>{
private String filename;
private LemonadeService lemonadeService;

public OrderFileRepository(String filename, LemonadeService lemonadeService) throws FileNotFoundException, IDNotUniqueException {
		super();
		this.filename = filename;
		this.lemonadeService = lemonadeService;
		super.fileExistenceCheck(filename);
		loadOrdersFromFile();
	}

	private void loadOrdersFromFile() throws IDNotUniqueException, FileNotFoundException {
			List<Order> order = readOrdersFromFile();
			for (Order orders : order) {
				this.save(orders);
			}

		}

		public List<Order> readOrdersFromFile() {
			List<Order> orders = new ArrayList<>();
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(filename));
				String line;
				br.readLine();
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(",");
					int id = Integer.parseInt(parts[0]);
					int lemonadeId = Integer.parseInt(parts[1]);
					int qty = Integer.parseInt(parts[2]);
					double price  = Double.parseDouble(parts[3]);

					Lemonade lemonade = lemonadeService.findById(lemonadeId);

					String readDate = (parts[4]);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date date = formatter.parse(readDate);

					Order order = new Order(id, lemonade, qty, price, date);
					orders.add(order);
				}
				br.close();
			} catch (IOException | ParseException e) {
				throw new RuntimeException(e);
			}
			return orders;
		}

		public void writeToFile() {
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(filename));
				bw.write("ID|Lemonade|Quantity|FinalPrice|Date");
				bw.newLine();

				Iterable<Order> orders = findAll();

				for (Order order : orders) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = dateFormat.format(order.getDate());
					String line = order.getId() + "," + order.getLemonade().getId()+","+order.getQuantity()+","+ order.getFinalPrice()+","+formattedDate;
					bw.write(line);
					bw.newLine();
				}
				bw.close();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public Order save(Order order) throws IDNotUniqueException, FileNotFoundException {
			Order savedOrder = super.save(order);
			writeToFile();
			return savedOrder;
		}

		@Override
		public Order update(Order order) throws FileNotFoundException {
			Order updatedOrder = super.update(order);
			writeToFile();
			return updatedOrder;
		}

		@Override
		public void delete(int OrderID) throws FileNotFoundException {
			super.delete(OrderID);
			writeToFile();
		}

	}
