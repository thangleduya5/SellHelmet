package shm.DAO;

import org.hibernate.SessionFactory;

public interface ChiTietKMDAO {
	Integer getDiscount(SessionFactory factory, String maSP);
}
