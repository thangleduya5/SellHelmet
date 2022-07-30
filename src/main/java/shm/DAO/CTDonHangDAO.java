package shm.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.entity.CTDonHang;


public interface CTDonHangDAO {
	List<CTDonHang> getDetailBills(SessionFactory factory, String id);
	int deleteDetailBill(SessionFactory factory, CTDonHang ctDonHang);
	int insertDetailBill(SessionFactory factory, CTDonHang ctDonHang);
	int updateDetailBill(SessionFactory factory, CTDonHang ctDonHang);
	CTDonHang getDetailBill(SessionFactory factory, String idBill, String idProduct);
}
