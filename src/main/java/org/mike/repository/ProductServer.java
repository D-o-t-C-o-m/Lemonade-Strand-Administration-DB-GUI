package org.mike.repository;

import java.sql.SQLException;
import org.mike.Utils.MySQLConnection;



public class ProductServer {

public static void getAllProducts(){
	String sql = "SELECT productId,productName,productDescription,productCost,productQty,supplierID  FROM products";

	try (var conn = MySQLConnection.connect()) {
		System.out.println("Connected to MySQL");
		assert conn != null;
		try (var stmt  = conn.createStatement();
		     var rs    = stmt.executeQuery(sql)) {

			System.out.println(rs.getMetaData().getColumnName(1) + "|" + rs.getMetaData().getColumnName(2) + "|" + rs.getMetaData().getColumnName(3) + "|" + rs.getMetaData().getColumnName(4) + "|" + rs.getMetaData().getColumnName(5) + "|" + rs.getMetaData().getColumnName(6));
			while (rs.next()) {
				System.out.println(
						rs.getInt(1) + " "+
								rs.getString("productName")  + " " +
								rs.getString("ProductDescription") + " " +
								rs.getDouble("ProductCost")  + " " +
								rs.getString("ProductQty")  + " " +
								rs.getString("supplierID")
				);
			}
		}
	} catch (SQLException ex) {
		System.out.println(ex.getMessage());
	}
}

public static void main(String[] args) {
	getAllProducts();
 }
}

