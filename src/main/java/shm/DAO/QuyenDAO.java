package shm.DAO;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import shm.entity.Quyen;

public interface QuyenDAO {
	ArrayList<Quyen> getAllRole(SessionFactory factory);
	Quyen getRole(SessionFactory factory, int idRole);
}
