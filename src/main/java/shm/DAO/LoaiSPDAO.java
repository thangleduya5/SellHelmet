package shm.DAO;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import shm.entity.LoaiSP;

public interface LoaiSPDAO {
	ArrayList<LoaiSP> getListCategory(SessionFactory factory);
	LoaiSP getCategoryByID(SessionFactory factory, String id);
}
