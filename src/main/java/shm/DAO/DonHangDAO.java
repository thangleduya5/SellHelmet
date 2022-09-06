package shm.DAO;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;

import shm.entity.DonHang;
import shm.entity.KhachHang;
import shm.model.Revenue;

public interface DonHangDAO {
	DonHang getBillUnBuy(SessionFactory factory, String idCustomer);
	DonHang getBill(SessionFactory factory, String id);
	List<DonHang> getBills(SessionFactory factory, String idCustomer);
	int update(SessionFactory factory, DonHang donHang);
	int insert(SessionFactory factory, KhachHang khachHang);
	List<DonHang> getBillUnconfirm(SessionFactory factory);
	List<DonHang> getBillCancel(SessionFactory factory, HttpSession session);
	List<DonHang> getBillComplete(SessionFactory factory, HttpSession session);
	List<DonHang> getBillDelivering(SessionFactory factory, HttpSession session);
	List<DonHang> searchBillUnconfirm(SessionFactory factory, String from, String to);
	List<DonHang> searchBillCancel(SessionFactory factory, String from, String to);
	List<DonHang> searchBillComplete(SessionFactory factory, HttpSession session, String from, String to);
	List<DonHang> searchBillDelivering(SessionFactory factory, HttpSession session, String from, String to);
	List<Revenue> revenue(SessionFactory factory, String from, String to);
}
