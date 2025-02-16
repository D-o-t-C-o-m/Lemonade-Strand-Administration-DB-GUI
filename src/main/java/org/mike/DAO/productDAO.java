package org.mike.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mike.Utils.HibernateUtil;
import org.mike.domain.Product;
import org.mike.domain.Supplier;

public class productDAO extends DAO<Product> {

public productDAO() {
	super(Product.class);
}

@Override
public void update(Product product) {
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
}

