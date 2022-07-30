package shm.DAO;

import org.hibernate.SessionFactory;

import shm.entity.KhachHang;

public interface KhachHangDAO {
	KhachHang getCustomer(SessionFactory factory, String username);
	Integer updateCustomer(SessionFactory factory, KhachHang customer);

}
