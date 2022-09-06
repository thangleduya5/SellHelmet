package shm.DAO;

import org.hibernate.SessionFactory;

import shm.entity.HoaDon;


public interface HoaDonDAO {
	int insert(SessionFactory factory, HoaDon hoaDon);
	HoaDon getBill(SessionFactory factory, String id);
}
