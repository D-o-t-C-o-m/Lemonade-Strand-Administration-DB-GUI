package org.mike.repository;

import java.sql.SQLException;

import org.mike.MySQLConnection;
import org.mike.domain.Product;
import org.mike.service.SupplierService;

//extends GenericRepository<Product> implements RepositoryInterface<Product>
public class ProductServer {
private SupplierService supplierService;

public static void getAll(){
	String sql = "SELECT  * /*productId, productName, productDescription, productCost, productQty, supplierID*/" +
			"FROM products";

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
//				int productId = rs.getInt(1);
//				String productName = rs.getString(2);
//				String productDescription = rs.getString(3);
//				double productCost = rs.getDouble(4);
//				int productQty = rs.getInt(5);
//				int supplierID = rs.getInt(6);
//				Product product = new Product(productId, productName, productDescription, productCost, productQty, supplierService.findById(supplierID));

			}
		}
	} catch (SQLException ex) {
		System.out.println(ex.getMessage());
	}
}

public static void main(String[] args) {
	getAll();
 }
}

