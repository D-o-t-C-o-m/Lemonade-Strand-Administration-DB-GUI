package org.mike.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mike.Utils.HibernateUtil;
import org.mike.domain.Product;
import org.mike.domain.Supplier;

import java.util.List;
import java.util.Objects;

public class productDAO {

public void saveProduct(Product product) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	Supplier supplier = product.getSupplier();
	session.merge(supplier);
	session.persist(product);
	tx.commit();
	session.close();
}

public void updateProduct(Product product) {
	Session session = HibernateUtil.getSession();
	Supplier supplier = product.getSupplier();
	Transaction tx = null;
	try (session) {
		tx = session.beginTransaction();
		session.merge(product);
		session.merge(supplier);
		tx.commit();
	} catch (Exception e) {
		if (tx != null) tx.rollback();
		throw e;
	}
}

public void deleteProduct(Product product) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	session.remove(product);
	tx.commit();
	session.close();
}

public Product getProduct(int id) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	Product product = session.get(Product.class, id);
	tx.commit();
	session.close();
	return product;
}

public List<Product> getAllProducts() {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	List<Product> products = session.createQuery("from Product", Product.class).getResultList();
	tx.commit();
	session.close();
	return products;
}

}
