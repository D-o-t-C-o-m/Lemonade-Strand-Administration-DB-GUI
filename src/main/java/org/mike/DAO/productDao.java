package org.mike.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mike.Utils.HibernateUtil;
import org.mike.domain.Product;

import java.util.List;
import java.util.Objects;

public class productDao {

public void saveProduct(Product product) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	if (Objects.isNull(session.find(Product.class, product.getId()))) {
		session.persist(product);
	} else {
		session.merge(product);
	}
	tx.commit();
	session.close();
}

//public void updateProduct(Product product) {
//	Session session = HibernateUtil.getSession();
//	Transaction tx = session.beginTransaction();
//	session.update(product);
//	tx.commit();
//	session.close();
//}

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
public List getAllProducts() {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	List products = session.createQuery("from Product", Product.class).getResultList();
	tx.commit();
	session.close();
	return products;
}
}
