package shm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import shm.DAO.CTDonHangDAO;
import shm.DAO.DonHangDAO;
import shm.DAO.HoaDonDAO;
import shm.DAO.NhaCungCapDAO;
import shm.DAO.NhanVienDAO;
import shm.DAO.SanPhamDAO;
import shm.DAOImpl.CTDonHangDAOImpl;
import shm.DAOImpl.DonHangDAOImpl;
import shm.DAOImpl.HoaDonDAOImpl;
import shm.DAOImpl.NhaCungCapDAOImpl;
import shm.DAOImpl.NhanVienDAOImpl;
import shm.DAOImpl.SanPhamDAOImpl;
import shm.common.Utils;
import shm.entity.CTDonHang;
import shm.entity.DonHang;
import shm.entity.HoaDon;
import shm.entity.KhachHang;
import shm.entity.NhaCungCap;
import shm.entity.NhanVien;
import shm.entity.SanPham;
import shm.model.Revenue;
import shm.pdf.BillPDFView;
import shm.pdf.ProductPDFView;
import shm.pdf.StaffPDFView;
import shm.pdf.StatisticPDFView;
import shm.pdf.SupplierPDFView;


@Controller
@Transactional
@RequestMapping("pdf/")
public class PDFController {
	@Autowired SessionFactory factory;
	SanPhamDAO sanPhamDAO = new SanPhamDAOImpl();
	NhanVienDAO nhanVienDAO = new NhanVienDAOImpl();
	NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAOImpl();
	DonHangDAO donHangDAO = new DonHangDAOImpl();
	HoaDonDAO hoaDonDAO = new HoaDonDAOImpl();
	CTDonHangDAO ctDonHangDAO = new CTDonHangDAOImpl();

	@RequestMapping("staff")
	public ModelAndView staffListReport(HttpServletRequest req, HttpSession session) {
		List<NhanVien> staffs= nhanVienDAO.getAllStaff(factory);
		return new ModelAndView(new StaffPDFView(), "staffs", staffs);
	}
	
	@RequestMapping("product")
	public ModelAndView phoneListReport(HttpServletRequest req) {
		List<SanPham> products= sanPhamDAO.getListProduct(factory);
		return new ModelAndView(new ProductPDFView(), "products", products);
	}
	
	@RequestMapping("supplier")
	public ModelAndView supplierListReport(HttpServletRequest req) {
		List<NhaCungCap> suppliers= nhaCungCapDAO.getSuppliers(factory);
		return new ModelAndView(new SupplierPDFView(), "suppliers", suppliers);
	}
	
	@RequestMapping("statistic")
	public ModelAndView statisticListReport(HttpServletRequest req) {
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		List<Revenue> revenues = new Utils().getRevenue(donHangDAO.revenue(factory, from, to), from, to); 
		return new ModelAndView(new StatisticPDFView(), "revenues", revenues);
	}
	
	@RequestMapping("bill")
	public ModelAndView billReport(HttpServletRequest req) {
		String id = req.getParameter("id");
		HoaDon billl = hoaDonDAO.getBill(factory, id);
		DonHang bill = billl.getDonHang();
		System.out.println(bill.getCtDonHangs().size());
		bill.setCtDonHangs(ctDonHangDAO.getDetailBills(factory, bill.getMaDH()));
		System.out.println(bill.getCtDonHangs());
		return new ModelAndView(new BillPDFView(), "billl", billl);
	}

}
