package shm.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.SanPhamDAO;
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

	@Override
	public int update(SessionFactory factory, SanPham p) {
		Session session = factory.getCurrentSession();
		try {
			session.merge(p);
		} catch (Exception e) {
			System.out.println("update sp loi");
			System.out.println(e);
			return 0;
		} 
		return 1;
	}

	@Override
	public int insert(SessionFactory factory, SanPham p) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(p);
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@Override
	public int delete(SessionFactory factory, SanPham p) {
		Session session = factory.getCurrentSession();
		try {
			session.delete(p);
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		} 
		return 1;
	}

	@Override
	public ArrayList<SanPham> getListProductByIDBrand(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM SanPham S WHERE S.nhaCungCap.maNCC =:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", id);
		return (ArrayList<SanPham>) query.list();
	}

	public List<String> getProductBrands(SessionFactory factory, String idBrand) {

		Session session = factory.getCurrentSession();
		String hql = "SELECT P.maSP FROM SanPham P WHERE P.nhaCungCap.maNCC =:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", idBrand);
		List<String> list = query.list();
		return list;

	}
	
	public List<String> getProductPrices(SessionFactory factory, String price) {

		Session session = factory.getCurrentSession();
		String hql= "SELECT P.maSP FROM SanPham P WHERE ";
		switch(price) {
			case "<2":
				hql += "P.gia < 200000";
				break;
			case "2->5":
				hql += "P.gia >= 200000 AND P.gia <= 500000";
				break;
			case "5->1":
				hql += "P.gia >= 500000 AND P.gia <= 1000000";
				break;
			case "1->5":
				hql += "P.gia >= 10000000 AND P.gia <= 5000000";
				break;
			default:
				hql += "P.gia > 5000000";
		}
		Query query = session.createQuery(hql);
		List<String> list = query.list();
		return list;

	}

	@Override
	public List<String> getProductCategorys(SessionFactory factory, String idCategory) {
		
		Session session = factory.getCurrentSession();
		String hql = "SELECT P.maSP FROM SanPham P WHERE P.loaiSP.maLoai =:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", idCategory);
		List<String> list = query.list();
		return list;
	}

}
