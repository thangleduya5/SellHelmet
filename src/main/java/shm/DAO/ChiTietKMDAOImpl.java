package shm.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ChiTietKMDAOImpl implements ChiTietKMDAO{

	@Override
	public Integer getDiscount(SessionFactory factory, String idProduct) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT C.giamGia FROM ChiTietKM C where C.pk.sanPham.maSP = :id AND (current_date() BETWEEN C.pk.khuyenMai.ngayBD AND C.pk.khuyenMai.ngayKT)";
		Query query = session.createQuery(hql);
		query.setParameter("id", idProduct);
		if(query.list().isEmpty())
			return 0;
		return  (Integer) query.list().get(0);
	}

}
