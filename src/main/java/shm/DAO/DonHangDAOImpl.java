package shm.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shm.entity.DonHang;

public class DonHangDAOImpl implements DonHangDAO{

	@Override
	public DonHang getBillUnBuy(SessionFactory factory, String idCustomer) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang D WHERE D.khachHang.maKH =:idCustomer AND D.trangThai = 0";
		Query query = session.createQuery(hql);
		query.setParameter("idCustomer", idCustomer);
		List<DonHang> list = query.list();
		return list.get(0);
	}

	@Override
	public List<DonHang> getBills(SessionFactory factory, String idCustomer) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang D WHERE (D.trangThai = 1 OR D.trangThai = 2) AND D.khachHang.maKH =:idCustomer";
		Query query = session.createQuery(hql);
		query.setParameter("idCustomer", idCustomer);
		List<DonHang> list = query.list();
		return list;
	}

}
