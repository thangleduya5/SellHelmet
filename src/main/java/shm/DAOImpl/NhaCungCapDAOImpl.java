package shm.DAOImpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.NhaCungCapDAO;
import shm.entity.NhaCungCap;
import shm.entity.NhanVien;
import shm.entity.SanPham;

public class NhaCungCapDAOImpl implements NhaCungCapDAO{
	
	public ArrayList<NhaCungCap> getSuppliers(SessionFactory factory){
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaCungCap";
		Query query = session.createQuery(hql);
		return (ArrayList<NhaCungCap>) query.list();
	}

	@Override
	public int updateSupplier(SessionFactory factory, NhaCungCap p) {
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
	public int insertSupplier(SessionFactory factory, NhaCungCap p) {
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
	public int deleteSupplier(SessionFactory factory, NhaCungCap p) {
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
	public ArrayList<NhaCungCap> searchAllSuppliers(SessionFactory factory, String name) {
		Session session = factory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("EXEC TimKiemNCCTheoTen ?").addEntity(NhaCungCap.class);
		sqlQuery.setString(0, name);
		return (ArrayList<NhaCungCap>) sqlQuery.list();
	}

	@Override
	public NhaCungCap getSupplierByID(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaCungCap where maNCC = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (NhaCungCap) query.list().get(0);
	};
}
