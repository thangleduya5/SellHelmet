package shm.DAOImpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.CTPhieuNhapDAO;
import shm.entity.CTPhieuNhap;
import shm.entity.NhaCungCap;

public class CTPhieuNhapDAOImpl implements CTPhieuNhapDAO{

	@Override
	public ArrayList<CTPhieuNhap> getDetailImports(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTPhieuNhap";
		Query query = session.createQuery(hql);
		return (ArrayList<CTPhieuNhap>) query.list();
	}

	@Override
	public ArrayList<CTPhieuNhap> searchAllDetailImports(SessionFactory factory, String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTPhieuNhap";
		Query query = session.createQuery(hql);
		return (ArrayList<CTPhieuNhap>) query.list();
	}

	@Override
	public int updateDetailImport(SessionFactory factory, CTPhieuNhap p) {
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
	public int insertDetailImport(SessionFactory factory, CTPhieuNhap p) {
		Session session = factory.getCurrentSession();
		try {
			session.save(p);
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} 
		return 1;
	}

	@Override
	public int deleteDetailImport(SessionFactory factory, CTPhieuNhap p) {
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
	public CTPhieuNhap getDetailImportByID(SessionFactory factory, String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
