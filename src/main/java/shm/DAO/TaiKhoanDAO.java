package shm.DAO;

import org.hibernate.SessionFactory;

import shm.entity.TaiKhoan;

public interface TaiKhoanDAO {
	String getRole(SessionFactory factory, String pass, String userName);
	TaiKhoan getAccount(SessionFactory factory, String username);
	Integer updatePass(SessionFactory factory, String newPass, String userName);
	Integer deleteAccount(SessionFactory factory, TaiKhoan taiKhoan);
	Integer insertAccount(SessionFactory factory, TaiKhoan taiKhoan);
	Integer updateAccount(SessionFactory factory, TaiKhoan taiKhoan);
}
