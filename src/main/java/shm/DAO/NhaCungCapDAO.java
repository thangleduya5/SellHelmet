package shm.DAO;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import shm.entity.NhaCungCap;
import shm.entity.NhanVien;
import shm.entity.SanPham;

public interface NhaCungCapDAO {
	
	ArrayList<NhaCungCap> getSuppliers(SessionFactory factory);
	ArrayList<NhaCungCap> searchAllSuppliers(SessionFactory factory, String name);
	int updateSupplier(SessionFactory factory, NhaCungCap p);
	int insertSupplier(SessionFactory factory, NhaCungCap p);
	int deleteSupplier(SessionFactory factory, NhaCungCap p);
	NhaCungCap getSupplierByID(SessionFactory factory, String id);
}
