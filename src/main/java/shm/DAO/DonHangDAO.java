package shm.DAO;

import java.util.List;

import org.hibernate.SessionFactory;

import shm.entity.DonHang;

public interface DonHangDAO {
	DonHang getBillUnBuy(SessionFactory factory, String idCustomer);
	List<DonHang> getBills(SessionFactory factory, String idCustomer);

}
