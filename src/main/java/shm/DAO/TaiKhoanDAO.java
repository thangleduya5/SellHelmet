package shm.DAO;

import org.hibernate.SessionFactory;

public interface TaiKhoanDAO {
	String getRole(SessionFactory factory, String pass, String userName);
	Integer updatePass(SessionFactory factory, String newPass, String userName);
}
