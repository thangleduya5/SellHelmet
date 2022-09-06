package shm.DAOImpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shm.DAO.QuyenDAO;
import shm.entity.Quyen;

public class QuyenDAOImpl implements QuyenDAO{

	@Override
	public ArrayList<Quyen> getAllRole(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Quyen";
		Query query = session.createQuery(hql);
		return (ArrayList<Quyen>) query.list();
	}

	@Override
	public Quyen getRole(SessionFactory factory, int idRole) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Quyen Where maQuyen =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", idRole);
		ArrayList<Quyen> list = (ArrayList<Quyen>) query.list();
		return list.isEmpty() ? null : list.get(0);
	}

}
