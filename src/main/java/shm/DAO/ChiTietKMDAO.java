package shm.DAO;

import java.util.List;

import org.hibernate.SessionFactory;

import shm.entity.CTDonHang;
import shm.entity.ChiTietKM;

public interface ChiTietKMDAO {
	Integer getDiscount(SessionFactory factory, String maSP);
	List<ChiTietKM> getDetailPromotions(SessionFactory factory, Integer id);
	ChiTietKM getDetailPromotion(SessionFactory factory, String maSP, Integer maKM);
	int deleteDetailPromotion(SessionFactory factory, ChiTietKM p);
	int insertDetailPromotion(SessionFactory factory, ChiTietKM p);
	int updateDetailPromotion(SessionFactory factory, ChiTietKM p);
}
