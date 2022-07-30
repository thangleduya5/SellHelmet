package shm.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.entity.CTDonHang;

public class CTDonHangDAOImpl implements CTDonHangDAO{

	@Override
	public List<CTDonHang> getDetailBills(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDonHang C WHERE C.pk.donHang.maDH =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return query.list();
	}

	@Override
	public int deleteDetailBill(SessionFactory factory, CTDonHang ctDonHang) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(ctDonHang);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@Override
	public int insertDetailBill(SessionFactory factory, CTDonHang ctDonHang) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ctDonHang);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public int updateDetailBill(SessionFactory factory, CTDonHang ctDonHang) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(ctDonHang);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@Override
	public CTDonHang getDetailBill(SessionFactory factory, String idBill, String idProduct) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDonHang D WHERE D.pk.donHang.maDH =:idBill AND D.pk.sanPham.maSP =:idProduct";
		Query query = session.createQuery(hql);
		query.setParameter("idBill", idBill);
		query.setParameter("idProduct", idProduct);
		List<CTDonHang> list= query.list();
		if(list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
