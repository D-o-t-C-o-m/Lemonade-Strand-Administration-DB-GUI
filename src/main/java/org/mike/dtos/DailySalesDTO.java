package org.mike.dtos;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DailySalesDTO {
private Date day;
private int totalSales;
private double salesDollars;

public DailySalesDTO(Date day, int totalSales, double salesDollars) {
	this.day = day;
	this.totalSales = totalSales;
	this.salesDollars = salesDollars;
}
public Date getDay() {
	return day;
}
public void setDay(Date day) {
	this.day = day;
}
public int getTotalSales() {
	return totalSales;
}
public void setTotalSales(int totalSales) {
	this.totalSales = totalSales;
}
public double getSalesDollars() {
	return salesDollars;
}
public void setSalesDollars(double salesDollars) {
	this.salesDollars = salesDollars;
}

public String getDayString(){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	return sdf.format(day);
}
}
