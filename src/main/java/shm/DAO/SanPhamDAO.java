package shm.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import shm.entity.SanPham;

public interface SanPhamDAO {
	ArrayList<SanPham> getListProduct(SessionFactory factory);
	ArrayList<SanPham> getListHotSaleProduct(SessionFactory factory, int bigSaleOffPercent);
	ArrayList<SanPham> getListNewProduct(SessionFactory factory);
	ArrayList<SanPham> getListHotProdduct(SessionFactory factory, int monthNumber);
	ArrayList<SanPham> getListProductByName(SessionFactory factory, String name);
	ArrayList<SanPham> getListProductByNameBrand(SessionFactory factory, String name);
	ArrayList<SanPham> getListProductByIDBrand(SessionFactory factory, String id);
	List<String> getProductBrands(SessionFactory factory, String idBrand);
	List<String> getProductCategorys(SessionFactory factory, String idCategory);
	List<String> getProductPrices(SessionFactory factory, String price); 
	SanPham getProduct(SessionFactory factory, String maSP);
	int update(SessionFactory factory, SanPham p);
	int insert(SessionFactory factory, SanPham p);
	int delete(SessionFactory factory, SanPham p);
}
