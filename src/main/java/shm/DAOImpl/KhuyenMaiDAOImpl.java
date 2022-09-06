package shm.DAOImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.KhuyenMaiDAO;
import shm.entity.KhuyenMai;

public class KhuyenMaiDAOImpl implements KhuyenMaiDAO{

	@Override
	public ArrayList<KhuyenMai> getPromotions(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai";
		Query query = session.createQuery(hql);
		return (ArrayList<KhuyenMai>) query.list();
	}

	@Override
	public ArrayList<KhuyenMai> searchAllPromotions(SessionFactory factory, String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai";
		Query query = session.createQuery(hql);
		return (ArrayList<KhuyenMai>) query.list();
	}

	@Override
	public int updatePromotion(SessionFactory factory, KhuyenMai p) {
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
	public int insertPromotion(SessionFactory factory, KhuyenMai p) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(p);
			t.commit();
		} catch (Exception e) {
			System.out.println("loi o day: " + e);
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@Override
	public int deletePromotion(SessionFactory factory, KhuyenMai p) {
		Session session = factory.getCurrentSession();
		try {
			session.delete(p);
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		return 1;
	}

	@Override
	public KhuyenMai getPromotionByID(SessionFactory factory, Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai WHERE maKM =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<KhuyenMai> list = query.list();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Boolean checkValidStartDate(SessionFactory factory, Date startDate) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai WHERE ngayKT >=:ngayBD";
		Query query = session.createQuery(hql);
		query.setParameter("ngayBD", startDate);
		return query.list().isEmpty() ? true : false;
	}
	
	@Override
	public Date getMaxEndDate(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT max(ngayKT) FROM KhuyenMai";
		Query query = session.createQuery(hql);
		return (Date) query.list().get(0);
	}

}
