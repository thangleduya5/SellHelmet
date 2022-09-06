package shm.DAO;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import shm.entity.CTPhieuDat;
import shm.entity.PhieuDat;

public interface CTPhieuDatDAO {
	ArrayList<CTPhieuDat> getDetailOrders(SessionFactory factory);
	ArrayList<CTPhieuDat> searchAllDetailOrders(SessionFactory factory, String name);
	int updateDetailOrder(SessionFactory factory, CTPhieuDat p);
	int insertDetailOrder(SessionFactory factory, CTPhieuDat p);
	int deleteDetailOrder(SessionFactory factory, CTPhieuDat p);
	ArrayList<CTPhieuDat> getDetailOrderByID(SessionFactory factory, String id);
}
