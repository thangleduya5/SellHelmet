package shm.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.KhachHangDAO;
import shm.common.Utils;
import shm.entity.KhachHang;
import shm.entity.TaiKhoan;

public class KhachHangDAOImpl implements KhachHangDAO{

	@Override
	public KhachHang getCustomer(SessionFactory factory, String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhachHang where tenDN = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", username);
		List<KhachHang> list = query.list();
		System.out.println("=================================================================="+list);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Integer updateCustomer(SessionFactory factory, KhachHang customer) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(customer);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			System.out.print(e);
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@Override
	public Integer insertCustomer(SessionFactory factory, KhachHang customer, TaiKhoan taiKhoan) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		customer.setMaKH("KH" + Utils.createID(factory, "SELECT max(substring(maKH,3,length(maKH))) + 1 FROM KhachHang"));
		try {
			session.save(taiKhoan);
			customer.setTaiKhoan(taiKhoan);
			session.save(customer);
			t.commit();
		} catch (HibernateException e) {
			t.rollback();
			System.out.print("hello: " +e);
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@Override
	public ArrayList<KhachHang> getAllCustomer(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhachHang";
		Query query = session.createQuery(hql);
		return (ArrayList<KhachHang>) query.list();
	}
	
	public Integer setStatus(SessionFactory factory, int status, String idCustomer) {
		Session session = factory.getCurrentSession();
		try {
			String hql = "UPDATE KhachHang SET trangThai = :status WHERE maKH = :id";
			Query query = session.createQuery(hql);
			query.setParameter("status", status);
			query.setParameter("id", idCustomer);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.print(e);
			return 0;
		}
		return 1;
	}
	
	public List<KhachHang> searchCustomers(SessionFactory factory, String nameCustomer) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhachHang where hoTen LIKE :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + nameCustomer + "%");
		List<KhachHang> list = query.list();
		return list;
	}

}
