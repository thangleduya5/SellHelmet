package shm.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import shm.entity.KhachHang;
import shm.entity.TaiKhoan;

public interface KhachHangDAO {
	KhachHang getCustomer(SessionFactory factory, String username);
	Integer updateCustomer(SessionFactory factory, KhachHang customer);
	Integer insertCustomer(SessionFactory factory, KhachHang customer, TaiKhoan taiKhoan);
	ArrayList<KhachHang> getAllCustomer(SessionFactory factory);
	Integer setStatus(SessionFactory factory, int status, String idCustomer);
	public List<KhachHang> searchCustomers(SessionFactory factory, String nameCustomer);
}
