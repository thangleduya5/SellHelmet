package shm.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.ChiTietKMDAO;
import shm.entity.ChiTietKM;
import shm.entity.NhaCungCap;


public class ChiTietKMDAOImpl implements ChiTietKMDAO{

	@Override
	public Integer getDiscount(SessionFactory factory, String idProduct) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT C.giamGia FROM ChiTietKM C where C.pk.sanPham.maSP = :id AND (current_date() BETWEEN C.pk.khuyenMai.ngayBD AND C.pk.khuyenMai.ngayKT)";
		Query query = session.createQuery(hql);
		query.setParameter("id", idProduct);
		if(query.list().isEmpty())
			return 0;
		return  (Integer) query.list().get(0);
	}

	@Override
	public List<ChiTietKM> getDetailPromotions(SessionFactory factory, Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietKM WHERE pk.khuyenMai.maKM =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (ArrayList<ChiTietKM>) query.list();
	}

	@Override
	public int deleteDetailPromotion(SessionFactory factory, ChiTietKM p) {
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
	public int insertDetailPromotion(SessionFactory factory, ChiTietKM p) {
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
	public int updateDetailPromotion(SessionFactory factory, ChiTietKM p) {
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
	public ChiTietKM getDetailPromotion(SessionFactory factory, String maSP, Integer maKM) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietKM WHERE pk.khuyenMai.maKM =:maKM AND pk.sanPham.maSP =:maSP";
		Query query = session.createQuery(hql);
		query.setParameter("maKM", maKM);
		query.setParameter("maSP", maSP);
		List<ChiTietKM> list = query.list();
		return  list.get(0);
	}

}
