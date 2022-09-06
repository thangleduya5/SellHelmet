package shm.DAO;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.SessionFactory;

import shm.entity.KhuyenMai;
import shm.entity.NhaCungCap;

public interface KhuyenMaiDAO {
	ArrayList<KhuyenMai> getPromotions(SessionFactory factory);
	ArrayList<KhuyenMai> searchAllPromotions(SessionFactory factory, String name);
	int updatePromotion(SessionFactory factory, KhuyenMai p);
	Boolean checkValidStartDate(SessionFactory factory, Date startDate);
	public Date getMaxEndDate(SessionFactory factory);
	int insertPromotion(SessionFactory factory, KhuyenMai p);
	int deletePromotion(SessionFactory factory, KhuyenMai p);
	KhuyenMai getPromotionByID(SessionFactory factory, Integer id);
}
