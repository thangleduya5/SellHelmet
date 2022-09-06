package shm.DAOImpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shm.DAO.LoaiSPDAO;
import shm.entity.LoaiSP;
import shm.entity.NhanVien;

public class LoaiSPDAOImpl implements LoaiSPDAO{

	@Override
	public ArrayList<LoaiSP> getListCategory(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiSP";
		Query query = session.createQuery(hql);
		return (ArrayList<LoaiSP>) query.list();
	}
	public LoaiSP getCategoryByID(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiSP where maLoai = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (LoaiSP) query.list().get(0);
	}

}
