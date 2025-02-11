package org.mike.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.mike.domain.Entity;
import org.mike.domain.Product;
import org.mike.exceptions.IDNotUniqueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mike.DAO.productDao;


public class ProductServer{
private static final Logger logger = LoggerFactory.getLogger(ProductServer.class);
private final productDao dao;

//public static void getAllProducts(){
//	String sql = "SELECT productId,productName,productDescription,productCost,productQty,supplierID  FROM products";
//
//	try (var conn = MySQLConnection.connect()) {
//		System.out.println("Connected to MySQL");
//		assert conn != null;
//		try (var stmt  = conn.createStatement();
//		     var rs    = stmt.executeQuery(sql)) {
//
//			System.out.println(rs.getMetaData().getColumnName(1) + "|" + rs.getMetaData().getColumnName(2) + "|" + rs.getMetaData().getColumnName(3) + "|" + rs.getMetaData().getColumnName(4) + "|" + rs.getMetaData().getColumnName(5) + "|" + rs.getMetaData().getColumnName(6));
//			while (rs.next()) {
//				System.out.println(
//						rs.getInt(1) + " "+
//								rs.getString("productName")  + " " +
//								rs.getString("ProductDescription") + " " +
//								rs.getDouble("ProductCost")  + " " +
//								rs.getString("ProductQty")  + " " +
//								rs.getString("supplierID")
//				);
//			}
//		}
//	} catch (SQLException ex) {
//		System.out.println(ex.getMessage());
//	}
//}
public ProductServer() throws IOException, IDNotUniqueException {
	this.dao = new productDao();
	//loadProductsFromFile();
}

public Product save (Product product) {
	dao.saveProduct(product);
	return product;
}


public Entity update(Entity entity) throws FileNotFoundException {
	return null;
}


public Product update(Product entity) throws FileNotFoundException {
	return null;
}


public void delete(int entityId) throws FileNotFoundException {

}


public List findAll() {
	return dao.getAllProducts();
}

public Entity findById(int entityId) {
	return null;
}
}

