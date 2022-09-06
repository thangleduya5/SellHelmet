package shm.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shm.DAO.HoaDonDAO;
import shm.entity.HoaDon;

public class HoaDonDAOImpl implements HoaDonDAO{

	@Override
	public int insert(SessionFactory factory, HoaDon hoaDon) {
		Session session = factory.getCurrentSession();
		try {
			session.save(hoaDon);
		} catch (Exception e) {
			System.out.println("loi tao don hang");
			return 0;
		} 
		return 1;
	}

	@Override
	public HoaDon getBill(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDon D WHERE D.maHD =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<HoaDon> list = query.list();
		return list.get(0);
	}

}
