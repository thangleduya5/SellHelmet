package shm.DAOImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import shm.DAO.DonHangDAO;
import shm.common.Utils;
import shm.entity.DonHang;
import shm.entity.KhachHang;
import shm.entity.NhanVien;
import shm.entity.SanPham;
import shm.model.Revenue;

public class DonHangDAOImpl implements DonHangDAO{

	@Override
	public DonHang getBillUnBuy(SessionFactory factory, String idCustomer) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang D WHERE D.khachHang.maKH =:idCustomer AND D.trangThai = 0";
		Query query = session.createQuery(hql);
		query.setParameter("idCustomer", idCustomer);
		List<DonHang> list = query.list();
		return list.get(0);
	}

	@Override
	public List<DonHang> getBills(SessionFactory factory, String idCustomer) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang D WHERE D.trangThai <> 0  AND D.khachHang.maKH =:idCustomer";
		Query query = session.createQuery(hql);
		query.setParameter("idCustomer", idCustomer);
		List<DonHang> list = query.list();
		return list;
	}

	@Override
	public int update(SessionFactory factory, DonHang donHang) {
		Session session = factory.getCurrentSession();
		try {
			session.merge(donHang);
		} catch (Exception e) {
			System.out.println("update don hang loi ");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int insert(SessionFactory factory, KhachHang k) {
		DonHang dh = new DonHang("DH" + Utils.createID(factory, "SELECT max(cast(substring(maDH,3,length(maDH)) AS int)) + 1 FROM DonHang"), 0, k.getHoTen(), k.getDiaChi(), k.getSdt(), k.getEmail(), null, null, 0,
				null, null, k, null);
		Session session = factory.getCurrentSession();
		try {
			session.save(dh);
		} catch (Exception e) {
			System.out.println(dh.getMaDH());
			System.out.println("loi tao gio hang");
			System.out.println(e);
			return 0;
		} 
		return 1;
	}

	@Override
	public List<DonHang> getBillUnconfirm(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang WHERE trangThai = 1 AND nhanVienD.maNV = NULL";
		Query query = session.createQuery(hql);
		List<DonHang> list = query.list();
		return list;
	}
	
	@Override
	public List<DonHang> searchBillUnconfirm(SessionFactory factory, String from, String to) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang where trangThai = 1 AND nhanVienD.maNV = NULL AND ngayTao >=:from AND ngayTao <=:to";
		Query query = session.createQuery(hql);
		try {
			query.setParameter("from", new SimpleDateFormat("yyyy-MM-dd").parse(from));
			query.setParameter("to", new SimpleDateFormat("yyyy-MM-dd").parse(to));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<DonHang> list = query.list();
		return list;
	}
	
	@Override
	public List<DonHang> getBillCancel(SessionFactory factory, HttpSession sessions) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang WHERE trangThai = -1";
		Query query = session.createQuery(hql);
		List<DonHang> list = query.list();
		return list;
	}
	
	@Override
	public List<DonHang> searchBillCancel(SessionFactory factory, String from, String to) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang where trangThai = -1 AND ngayTao >=:from AND ngayTao <=:to";
		Query query = session.createQuery(hql);
		try {
			query.setParameter("from", new SimpleDateFormat("yyyy-MM-dd").parse(from));
			query.setParameter("to", new SimpleDateFormat("yyyy-MM-dd").parse(to));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<DonHang> list = query.list();
		return list;
	}
	
	@Override
	public List<Revenue> revenue(SessionFactory factory, String from, String to) {
		Session session = factory.getCurrentSession();
        String sql = "exec DoanhThu '" + from + "' , '" + to + "'";
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<Revenue> revenues = new ArrayList<Revenue>();
        List data = query.list();
	      for (Object object : data) {
		      Map row = (Map) object;
		      revenues.add(new Revenue((Integer)row.get("NAM"), (Integer)row.get("THANG"), ((BigDecimal)row.get("DOANHTHU")).longValue()));
	      }
        return revenues;
	}

	@Override
	public DonHang getBill(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang D WHERE D.maDH =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<DonHang> list = query.list();
		return list.get(0);
	}

	@Override
	public List<DonHang> getBillComplete(SessionFactory factory, HttpSession sessions) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang where trangThai = 2 ";
		NhanVien staff= (NhanVien) sessions.getAttribute("staff");
		if(staff.getTaiKhoan().getQuyen().getMaQuyen() == 4) {
			hql += " AND nhanVienG.maNV = '" + staff.getMaNV() +"'";
		}
		Query query = session.createQuery(hql);
		List<DonHang> list = query.list();
		return list;
	}
	
	@Override
	public List<DonHang> searchBillComplete(SessionFactory factory, HttpSession sessions, String from, String to) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang where trangThai = 2 AND ngayTao >=:from AND ngayTao <=:to";
		NhanVien staff= (NhanVien) sessions.getAttribute("staff");
		if(staff.getTaiKhoan().getQuyen().getMaQuyen() == 4) {
			hql += " AND nhanVienG.maNV = '" + staff.getMaNV() +"'";
		}
		Query query = session.createQuery(hql);
		try {
			query.setParameter("from", new SimpleDateFormat("yyyy-MM-dd").parse(from));
			query.setParameter("to", new SimpleDateFormat("yyyy-MM-dd").parse(to));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<DonHang> list = query.list();
		return list;
	}

	@Override
	public List<DonHang> getBillDelivering(SessionFactory factory, HttpSession sessions) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang where nhanVienD.maNV <>NULL AND nhanVienG.maNV <>NULL AND trangThai = 1 ";
		NhanVien staff= (NhanVien) sessions.getAttribute("staff");
		if(staff.getTaiKhoan().getQuyen().getMaQuyen() == 4) {
			hql += " AND nhanVienG.maNV = '" + staff.getMaNV() +"'";
		}
		Query query = session.createQuery(hql);
		List<DonHang> list = query.list();
		return list;
	}

	@Override
	public List<DonHang> searchBillDelivering(SessionFactory factory, HttpSession sessions, String from, String to) {
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang where nhanVienD.maNV <>NULL AND nhanVienG.maNV <>NULL AND trangThai = 1 AND ngayTao >=:from AND ngayTao <=:to";
		NhanVien staff= (NhanVien) sessions.getAttribute("staff");
		if(staff.getTaiKhoan().getQuyen().getMaQuyen() == 4) {
			hql += " AND nhanVienG.maNV = '" + staff.getMaNV() +"'";
		}
		Query query = session.createQuery(hql);
		try {
			query.setParameter("from", new SimpleDateFormat("yyyy-MM-dd").parse(from));
			query.setParameter("to", new SimpleDateFormat("yyyy-MM-dd").parse(to));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<DonHang> list = query.list();
		return list;
	}

}
