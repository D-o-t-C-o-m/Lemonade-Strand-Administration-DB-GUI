package org.mike.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mike.Utils.HibernateUtil;
import org.mike.domain.Supplier;

import java.util.List;
import java.util.Objects;

public class supplierDAO {

public void saveSupplier(Supplier supplier) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	if (Objects.isNull(session.find(Supplier.class, supplier.getId()))) {
		session.persist(supplier);
	} else {
		session.merge(supplier);
	}
	tx.commit();
	session.close();
}

//public void updateProduct(Supplier supplier) {
//	Session session = HibernateUtil.getSession();
//	Transaction tx = session.beginTransaction();
//	session.update(supplier);
//	tx.commit();
//	session.close();
//}

public void deleteProduct(Supplier supplier) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	session.remove(supplier);
	tx.commit();
	session.close();
}

public Supplier getSupplier(int id) {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	Supplier supplier = session.get(Supplier.class, id);
	tx.commit();
	session.close();
	return supplier;
}

public List<Supplier> getAllSuppliers() {
	Session session = HibernateUtil.getSession();
	Transaction tx = session.beginTransaction();
	List<Supplier> products = session.createQuery("from Supplier", Supplier.class).getResultList();
	tx.commit();
	session.close();
	return products;
}

}
