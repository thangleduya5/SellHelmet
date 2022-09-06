package shm.DAO;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import shm.entity.NhaCungCap;
import shm.entity.PhieuDat;

public interface PhieuDatDAO {
	ArrayList<PhieuDat> getOrders(SessionFactory factory);
	ArrayList<PhieuDat> searchAllOrders(SessionFactory factory, String name);
	int updateOrder(SessionFactory factory, PhieuDat p);
	int insertOrder(SessionFactory factory, PhieuDat p);
	int deleteOrder(SessionFactory factory, PhieuDat p);
	PhieuDat getOrderByID(SessionFactory factory, String id);
}
