package shm.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.PhieuNhapDAO;
import shm.entity.CTPhieuDat;
import shm.entity.CTPhieuNhap;
import shm.entity.NhaCungCap;
import shm.entity.PhieuNhap;

public class PhieuNhapDAOImpl implements PhieuNhapDAO{

	@Override
	public ArrayList<PhieuNhap> getImports(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhieuNhap";
		Query query = session.createQuery(hql);
		return (ArrayList<PhieuNhap>) query.list();
	}

	@Override
	public ArrayList<PhieuNhap> searchAllImports(SessionFactory factory, String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PhieuNhap";
		Query query = session.createQuery(hql);
		return (ArrayList<PhieuNhap>) query.list();
	}

	@Override
	public int updateImport(SessionFactory factory, PhieuNhap p) {
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
	public int insertImport(SessionFactory factory, PhieuNhap p) {
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
	public int deleteImport(SessionFactory factory, PhieuNhap p) {
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
	public List<CTPhieuNhap> getImportByID(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTPhieuNhap C Where C.pk.phieuNhap.maPN=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return query.list();
	}

}
