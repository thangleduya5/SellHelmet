package shm.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.NhanVienDAO;
import shm.entity.NhanVien;
import shm.entity.SanPham;

public class NhanVienDAOImpl implements NhanVienDAO{

	@Override
	public NhanVien getStaff(SessionFactory factory, String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVien where tenDN = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", username);
		return (NhanVien) query.list().get(0);
	}
	
	public NhanVien getStaffByID(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVien where maNV = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (NhanVien) query.list().get(0);
	}

	@Override
	public Integer updateStaff(SessionFactory factory, NhanVien nhanVien) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nhanVien);
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
	public ArrayList<NhanVien> getAllStaff(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVien";
		Query query = session.createQuery(hql);
		return (ArrayList<NhanVien>) query.list();
	}

	@Override
	public Integer insertStaff(SessionFactory factory, NhanVien nhanVien) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nhanVien);
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
	public ArrayList<NhanVien> searchAllStaff(SessionFactory factory, String hoTen) {
		Session session = factory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("EXEC TimKiemNVTheoTen ?").addEntity(NhanVien.class);
		sqlQuery.setString(0, hoTen);
		return (ArrayList<NhanVien>) sqlQuery.list();
	}

	@Override
	public Integer deleteStaff(SessionFactory factory, NhanVien nhanVien) {
		Session session = factory.getCurrentSession();
		try {
			session.delete(nhanVien);
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} 
		return 1;
	}

	@Override
	public Integer getMaxNumberByName(SessionFactory factory, String username) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT max(substring(tenDN,length(tenDN),length(tenDN))) FROM TaiKhoan WHERE substring(tenDN,1,length(tenDN)-1) =:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", username);
		List<String> list = query.list();
		System.out.println(list);
		return list.get(0)==null ? 0 : Integer.parseInt(list.get(0));
	}

	@Override
	public ArrayList<NhanVien> getShippers(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVien Where taiKhoan.quyen.tenQuyen = 'Shipper' ";
		Query query = session.createQuery(hql);
		return (ArrayList<NhanVien>) query.list();
	}

}
