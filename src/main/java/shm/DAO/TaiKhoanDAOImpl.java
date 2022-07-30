package shm.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shm.entity.TaiKhoan;


public class TaiKhoanDAOImpl implements TaiKhoanDAO{

	@Override
	public String getRole(SessionFactory factory, String pass, String userName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoan C WHERE C.tenDN = :id AND C.matKhau = :pass";
		Query query = session.createQuery(hql);
		query.setParameter("id", userName);
		query.setParameter("pass", pass);
		List<TaiKhoan> list = query.list();
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
	
}
