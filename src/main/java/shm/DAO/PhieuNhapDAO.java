package shm.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import shm.entity.CTPhieuNhap;
import shm.entity.PhieuDat;
import shm.entity.PhieuNhap;

public interface PhieuNhapDAO {
	ArrayList<PhieuNhap> getImports(SessionFactory factory);
	ArrayList<PhieuNhap> searchAllImports(SessionFactory factory, String name);
	int updateImport(SessionFactory factory, PhieuNhap p);
	int insertImport(SessionFactory factory, PhieuNhap p);
	int deleteImport(SessionFactory factory, PhieuNhap p);
	List<CTPhieuNhap> getImportByID(SessionFactory factory, String id);
}
