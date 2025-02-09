package org.mike.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mike.Utils.HibernateUtil;
import org.mike.domain.Product;

public class productDao {
public void saveProduct(Product product) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	session.save(product);
	tx.commit();
	session.close();
}
}
