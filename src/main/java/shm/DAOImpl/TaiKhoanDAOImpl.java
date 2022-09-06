package shm.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.TaiKhoanDAO;
import shm.entity.TaiKhoan;


public class TaiKhoanDAOImpl implements TaiKhoanDAO{

	@Override
	public String getRole(SessionFactory factory, String pass, String userName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan C WHERE C.tenDN = :id AND C.matKhau = :pass";
		Query query = session.createQuery(hql);
		query.setParameter("id", userName);
		query.setParameter("pass", pass);
		System.out.println(hql);
		List<TaiKhoan> list = query.list();
		System.out.println(hql);
		System.out.println(list);
		return list.isEmpty() ? "" : list.get(0).getQuyen().getTenQuyen();
	}

	@Override
	public Integer updatePass(SessionFactory factory, String newPass, String userName) {
		Session session = factory.getCurrentSession();
		try {
			String hql = "UPDATE TaiKhoan SET matKhau = :pass WHERE tenDN = :id";
			Query query = session.createQuery(hql);
			query.setParameter("pass", newPass);
			query.setParameter("id", userName);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.print(e);
			return 0;
		} 
		return 1;
	}

	@Override
	public Integer deleteAccount(SessionFactory factory, TaiKhoan taiKhoan) {
		Session session = factory.getCurrentSession();
		try {
			session.delete(taiKhoan);
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} 
		return 1;
	}

	@Override
	public Integer insertAccount(SessionFactory factory, TaiKhoan taiKhoan) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(taiKhoan);
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
	public Integer updateAccount(SessionFactory factory, TaiKhoan taiKhoan) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(taiKhoan);
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
	public TaiKhoan getAccount(SessionFactory factory, String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan C WHERE C.tenDN = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", username);
		List<TaiKhoan> list = query.list();
		return list.isEmpty() ? null : list.get(0);
	}
	
}
