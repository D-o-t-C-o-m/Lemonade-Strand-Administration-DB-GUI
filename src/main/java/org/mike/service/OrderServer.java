package org.mike.service;

import org.mike.DAO.DAO;
import org.mike.domain.Order;
import org.mike.dtos.DailySalesDTO;

import java.util.List;

public class OrderServer extends GenericServer<Order> {
	public OrderServer() {
		super(new DAO<>(Order.class));
	}

public List<DailySalesDTO> getDailyReport() {
	return null;
}
}
