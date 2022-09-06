package shm.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.CTPhieuDatDAO;
import shm.DAO.PhieuDatDAO;
import shm.entity.CTPhieuDat;
import shm.entity.NhaCungCap;
import shm.entity.PhieuDat;

public class PhieuDatDAOImpl implements PhieuDatDAO{

	CTPhieuDatDAO ctPhieuDatDAO = new CTPhieuDatDAOImpl();
	
	@Override
	public ArrayList<PhieuDat> getOrders(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhieuDat";
		Query query = session.createQuery(hql);
		return (ArrayList<PhieuDat>) query.list();
	}

	@Override
	public ArrayList<PhieuDat> searchAllOrders(SessionFactory factory, String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhieuDat";
		Query query = session.createQuery(hql);
		return (ArrayList<PhieuDat>) query.list();
	}

	@Override
	public int updateOrder(SessionFactory factory, PhieuDat p) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(p);
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@Override
	public int insertOrder(SessionFactory factory, PhieuDat p) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(p);
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@Override
	public int deleteOrder(SessionFactory factory, PhieuDat p) {
		ArrayList<CTPhieuDat> orderDetails = ctPhieuDatDAO.getDetailOrderByID(factory, p.getMaPD());
		Session session = factory.getCurrentSession();
		try {
			for(CTPhieuDat orderDetail: orderDetails) {
				session.delete(orderDetail);
			}
			session.delete(p);
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		return 1;
	}

	@Override
	public PhieuDat getOrderByID(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhieuDat P Where P.maPD =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<PhieuDat> list = query.list();
		return list.get(0);
	}

}
