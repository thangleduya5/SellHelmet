package shm.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shm.entity.SanPham;

public class SanPhamDAOImpl implements SanPhamDAO{

	@Override
	public ArrayList<SanPham> getListProduct(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM SanPham";
		Query query = session.createQuery(hql);
		return (ArrayList<SanPham>) query.list();
	}

	@Override
	public ArrayList<SanPham> getListHotSaleProduct(SessionFactory factory, int bigSaleOffPercent) {
		Session session = factory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("EXEC KhuyenMaiKhung ?").addEntity(SanPham.class);
		sqlQuery.setInteger(0, bigSaleOffPercent);
		return (ArrayList<SanPham>) sqlQuery.list();
	}

	@Override
	public ArrayList<SanPham> getListNewProduct(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("EXEC SPMOI").addEntity(SanPham.class);
		return (ArrayList<SanPham>) sqlQuery.list();
	}

	@Override
	public ArrayList<SanPham> getListHotProdduct(SessionFactory factory, int monthNumber) {
		Session session = factory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("EXEC SPHot ?").addEntity(SanPham.class);
		sqlQuery.setInteger(0, monthNumber);
		return (ArrayList<SanPham>) sqlQuery.list();
	}

	@Override
	public ArrayList<SanPham> getListProductByName(SessionFactory factory, String name) {
		Session session = factory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery("EXEC TimKiemSPTheoTen ?").addEntity(SanPham.class);
		sqlQuery.setString(0, name);
		return (ArrayList<SanPham>) sqlQuery.list();
	}

	@Override
	public ArrayList<SanPham> getListProductByNameBrand(SessionFactory factory, String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM SanPham S WHERE S.nhaCungCap.tenNCC =:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		return (ArrayList<SanPham>) query.list();
	}

	@Override
	public SanPham getProduct(SessionFactory factory, String maSP) {
		Session session = factory.getCurrentSession();
		String hql = "FROM SanPham S WHERE S.maSP =:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", maSP);
		return (SanPham) query.list().get(0);
	}

}
