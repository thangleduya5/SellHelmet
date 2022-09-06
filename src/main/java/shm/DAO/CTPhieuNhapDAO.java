package shm.DAO;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import shm.entity.CTPhieuNhap;
import shm.entity.PhieuDat;

public interface CTPhieuNhapDAO {
	ArrayList<CTPhieuNhap> getDetailImports(SessionFactory factory);
	ArrayList<CTPhieuNhap> searchAllDetailImports(SessionFactory factory, String name);
	int updateDetailImport(SessionFactory factory, CTPhieuNhap p);
	int insertDetailImport(SessionFactory factory, CTPhieuNhap p);
	int deleteDetailImport(SessionFactory factory, CTPhieuNhap p);
	CTPhieuNhap getDetailImportByID(SessionFactory factory, String id);
}
