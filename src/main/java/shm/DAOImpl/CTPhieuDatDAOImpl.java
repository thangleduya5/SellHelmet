package shm.DAOImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.CTPhieuDatDAO;
import shm.entity.CTPhieuDat;
import shm.entity.NhaCungCap;

public class CTPhieuDatDAOImpl implements CTPhieuDatDAO{

	@Override
	public ArrayList<CTPhieuDat> getDetailOrders(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTPhieuDat";
		Query query = session.createQuery(hql);
		return (ArrayList<CTPhieuDat>) query.list();
	}

	@Override
	public ArrayList<CTPhieuDat> searchAllDetailOrders(SessionFactory factory, String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTPhieuDat";
		Query query = session.createQuery(hql);
		return (ArrayList<CTPhieuDat>) query.list();
	}

	@Override
	public int updateDetailOrder(SessionFactory factory, CTPhieuDat p) {
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
	public int insertDetailOrder(SessionFactory factory, CTPhieuDat p) {
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
	public int deleteDetailOrder(SessionFactory factory, CTPhieuDat p) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(p);
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
	public ArrayList<CTPhieuDat> getDetailOrderByID(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTPhieuDat C Where C.pk.phieuDat.maPD=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (ArrayList<CTPhieuDat>) query.list();
	}

}
