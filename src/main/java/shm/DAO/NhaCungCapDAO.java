package shm.DAO;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import shm.entity.NhaCungCap;

public interface NhaCungCapDAO {
	
	ArrayList<NhaCungCap> getSuppliers(SessionFactory factory);
	
}
