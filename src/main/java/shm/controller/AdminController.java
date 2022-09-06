package shm.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.expr.NewArray;
import shm.DAO.CTDonHangDAO;
import shm.DAO.CTPhieuDatDAO;
import shm.DAO.CTPhieuNhapDAO;
import shm.DAO.ChiTietKMDAO;
import shm.DAO.DonHangDAO;
import shm.DAO.HoaDonDAO;
import shm.DAO.KhachHangDAO;
import shm.DAO.KhuyenMaiDAO;
import shm.DAO.LoaiSPDAO;
import shm.DAO.NhaCungCapDAO;
import shm.DAO.PhieuDatDAO;
import shm.DAO.PhieuNhapDAO;
import shm.DAO.SanPhamDAO;
import shm.DAOImpl.CTDonHangDAOImpl;
import shm.DAOImpl.CTPhieuDatDAOImpl;
import shm.DAOImpl.CTPhieuNhapDAOImpl;
import shm.DAOImpl.ChiTietKMDAOImpl;
import shm.DAOImpl.DonHangDAOImpl;
import shm.DAOImpl.HoaDonDAOImpl;
import shm.DAOImpl.KhachHangDAOImpl;
import shm.DAOImpl.KhuyenMaiDAOImpl;
import shm.DAOImpl.LoaiSPDAOImpl;
import shm.DAOImpl.NhaCungCapDAOImpl;
import shm.DAOImpl.NhanVienDAOImpl;
import shm.DAOImpl.PhieuDatDAOImpl;
import shm.DAOImpl.PhieuNhapDAOImpl;
import shm.DAOImpl.QuyenDAOImpl;
import shm.DAOImpl.SanPhamDAOImpl;
import shm.DAOImpl.TaiKhoanDAOImpl;
import shm.common.Utils;
import shm.entity.CTDonHang;
import shm.entity.CTPhieuDat;
import shm.entity.CTPhieuDatPK;
import shm.entity.CTPhieuNhap;
import shm.entity.CTPhieuNhapPK;
import shm.entity.ChiTietKM;
import shm.entity.ChiTietKMPK;
import shm.entity.DonHang;
import shm.entity.HoaDon;
import shm.entity.KhachHang;
import shm.entity.KhuyenMai;
import shm.entity.NhaCungCap;
import shm.entity.NhanVien;
import shm.entity.PhieuDat;
import shm.entity.PhieuNhap;
import shm.entity.SanPham;
import shm.entity.TaiKhoan;
import shm.model.BasePath;
import shm.model.Product;
import shm.model.Revenue;

@Transactional
@Controller
@RequestMapping("Admin/")
public class AdminController {

	@Autowired
	BasePath basePath;

	@Autowired
	SessionFactory factory;

	TaiKhoanDAOImpl taiKhoanDAOImpl = new TaiKhoanDAOImpl();
	NhanVienDAOImpl nhanVienDAOImpl = new NhanVienDAOImpl();
	KhachHangDAO khachHangDAO = new KhachHangDAOImpl();
	QuyenDAOImpl quyenDAOImpl = new QuyenDAOImpl();
	DonHangDAO donHangDAO = new DonHangDAOImpl();
	CTDonHangDAO ctDonHangDAO = new CTDonHangDAOImpl();
	SanPhamDAO sanPhamDAO = new SanPhamDAOImpl();
	LoaiSPDAO loaiSPDAO = new LoaiSPDAOImpl();
	NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAOImpl();
	PhieuDatDAO phieuDatDAO = new PhieuDatDAOImpl();
	PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAOImpl();
	CTPhieuDatDAO ctPhieuDatDAO = new CTPhieuDatDAOImpl();
	CTPhieuNhapDAO ctPhieuNhapDAO = new CTPhieuNhapDAOImpl();
	KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAOImpl();
	ChiTietKMDAO chiTietKMDAO = new ChiTietKMDAOImpl();
	HoaDonDAO hoaDonDAO = new HoaDonDAOImpl();

	// --------------------- login info ---------------------//

	@RequestMapping("login")
	public String login(HttpSession session) {
		session.removeAttribute("staff");
		return "Admin/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, ModelMap model, HttpSession session) {
		String idAdmin = request.getParameter("name");
		String passAdmin = request.getParameter("password");
		String role = taiKhoanDAOImpl.getRole(factory, passAdmin, idAdmin);
		if (!role.equals("") && !role.equals("customer")) {
			NhanVien staff = nhanVienDAOImpl.getStaff(factory, idAdmin);
			session.setAttribute("staff", staff);
			return "redirect:/Admin/info.htm";
		} else {
			model.addAttribute("message", "Đăng nhập thất bại, vui lòng điền đúng thông tin đăng nhập!");
		}
		return "Admin/login";
	}

	@RequestMapping("info")
	public String info(HttpSession session, ModelMap model) throws ParseException {
		return "Admin/info";
	}

	@RequestMapping(value = "changePass")
	public String editStaffPass() {
		return "Admin/changePass";
	}

	@RequestMapping(value = "changePass", params = "btnUpdatePass", method = RequestMethod.POST)
	public String editStaffPass(HttpServletRequest request, HttpSession session, ModelMap model) {
		String newPass = request.getParameter("newPass");
		String oldPass = request.getParameter("oldPass");
		String newPassReset = request.getParameter("newPassReset");
		NhanVien nv = ((NhanVien) session.getAttribute("staff"));
		Boolean flag = true;
		if (!nv.getTaiKhoan().getMatKhau().equals(oldPass)) {
			model.addAttribute("oldPassEr", "Mật khẩu cũ không chính xác");
			flag = false;
		}
		if (newPass.equals("")) {
			model.addAttribute("newPassEr", "Vui lòng nhập mật khẩu mới");
			flag = false;
		}
		if (!newPass.equals(newPassReset)) {
			model.addAttribute("newPassResetEr", "Mật khẩu nhập lại không khớp");
			flag = false;
		}
		if (!flag) {
			model.addAttribute("oldPass", oldPass);
			model.addAttribute("newPass", newPass);
			model.addAttribute("newPassReset", newPassReset);
			return "Admin/changePass";
		}
		Integer temp = taiKhoanDAOImpl.updatePass(factory, newPass, nv.getTaiKhoan().getTenDN());
		if (temp == 0) {
			model.addAttribute("message", "Thay đổi mật khẩu thất bại");
		}
		return "Admin/info";
	}

	// --------------------- staff manager ---------------------//

	@RequestMapping("staff")
	public String staff(HttpServletRequest request, ModelMap model, HttpSession session) {

		showStaffs(request, model, nhanVienDAOImpl.getAllStaff(factory));

		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("staff", new NhanVien());
		session.setAttribute("roles", quyenDAOImpl.getAllRole(factory));
		return "Admin/staff";
	}

	@RequestMapping(value = "staff", params = "btnSearch")
	public String searchStaff(HttpServletRequest request, ModelMap model) {
		String name = request.getParameter("name");
		showStaffs(request, model, nhanVienDAOImpl.searchAllStaff(factory, name));
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("staff", new NhanVien());
		model.addAttribute("name", name);
		return "Admin/staff";
	}

	@RequestMapping(value = "staff", params = "btnAdd", method = RequestMethod.POST)
	public String addStaff(HttpServletRequest request, ModelMap model, @ModelAttribute("staff") NhanVien staff,
			BindingResult errors) {
		int maQuyen = Integer.parseInt(request.getParameter("quyen"));
		if (validateStaff(request, staff, errors)) {
			TaiKhoan k = new TaiKhoan(Utils.createUserName(factory, staff.getHoTen()), "1111",
					quyenDAOImpl.getRole(factory, maQuyen));
			System.out.println(k.getTenDN());
			Integer temp = taiKhoanDAOImpl.insertAccount(factory, k);
			staff.setTaiKhoan(k);
			staff.setMaNV(
					"NV" + Utils.createID(factory, "SELECT max(substring(maNV,3,length(maNV))) + 1 FROM NhanVien"));
			Integer temp1 = nhanVienDAOImpl.insertStaff(factory, staff);
			if (temp1 != 0) {
				model.addAttribute("message", "Thêm thành công");
				model.addAttribute("staff", new NhanVien());

			} else {
				model.addAttribute("message", "Thêm thất bại, vui lòng kiểm tra lại thông tin" + staff);
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		showStaffs(request, model, nhanVienDAOImpl.getAllStaff(factory));

		return "Admin/staff";
	}

	@RequestMapping(value = "staff", params = "btnEdit", method = RequestMethod.POST)
	public String editStaff(HttpServletRequest request, ModelMap model, @ModelAttribute("staff") NhanVien staff,
			BindingResult errors) {
		int maQuyen = Integer.parseInt(request.getParameter("quyen"));
		if (!validateStaff(request, staff, errors)) {
			System.out.println("Chao edit post");
			showStaffs(request, model, nhanVienDAOImpl.getAllStaff(factory));
			return "Admin/staff";
		}
		TaiKhoan account = taiKhoanDAOImpl.getAccount(factory, staff.getTaiKhoan().getTenDN());
		account.setQuyen(quyenDAOImpl.getRole(factory, maQuyen));
		Integer temp0 = taiKhoanDAOImpl.updateAccount(factory, account);
		Integer temp = nhanVienDAOImpl.updateStaff(factory, staff);
		if (temp != 0) {
			model.addAttribute("message", "Sửa thành công");
			model.addAttribute("staff", new NhanVien());
			model.addAttribute("btnStatus", "btnAdd");
		} else {
			model.addAttribute("message", "Sửa thất bại" + staff);
			model.addAttribute("btnStatus", "btnEdit");
		}
		showStaffs(request, model, nhanVienDAOImpl.getAllStaff(factory));
		return "Admin/staff";
	}

	@RequestMapping(value = "staff/{id}.htm", params = "linkEdit")
	public String editStaff(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		showStaffs(request, model, nhanVienDAOImpl.getAllStaff(factory));
		System.out.println("Chao edit");
		model.addAttribute("btnStatus", "btnEdit");
		NhanVien s = nhanVienDAOImpl.getStaffByID(factory, id);
		model.addAttribute("staff", s);

		return "Admin/staff";
	}

	@RequestMapping(value = "staff/{id}.htm", params = "linkDelete")
	public String deleteStaff(HttpServletRequest request, ModelMap model, @ModelAttribute("staff") NhanVien staff,
			@PathVariable("id") String id) {
		NhanVien nhanVien = nhanVienDAOImpl.getStaffByID(factory, id);
		TaiKhoan taiKhoan = taiKhoanDAOImpl.getAccount(factory, nhanVien.getTaiKhoan().getTenDN());
		Integer temp = nhanVienDAOImpl.deleteStaff(factory, nhanVien);
		Integer temp1 = taiKhoanDAOImpl.deleteAccount(factory, taiKhoan);
		if (temp != 0 && temp1 != 0) {
			model.addAttribute("message", "Xóa thành công");
		} else {
			model.addAttribute("message", "Xóa thất bại");
		}
		return "redirect:/Admin/staff.htm";
	}

	@RequestMapping(value = "staff/{id}.htm", params = "linkReset")
	public String resetStaff(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		Integer temp = taiKhoanDAOImpl.updatePass(factory, "1111",
				nhanVienDAOImpl.getStaffByID(factory, id).getTaiKhoan().getTenDN());
		if (temp != 0) {
			model.addAttribute("message", "Reset thành công");

		} else {
			model.addAttribute("message", "Reset thất bại");
		}

		model.addAttribute("staff", new NhanVien());
		model.addAttribute("btnStatus", "btnAdd");
		showStaffs(request, model, nhanVienDAOImpl.getAllStaff(factory));

		return "Admin/staff";
	}

	public Boolean validateStaff(HttpServletRequest request, NhanVien staff, BindingResult errors) {
		String checkname = "([\\p{L}\\s]+){1,30}";
		String checkphone = "[0-9]{10}";
		String checkemail = "^[A-Za-z0-9+_.-]+@(.+)$";
		String checkaddress = "([\\p{L}\\s\\d\\,]+){1,100}";

		if (staff.getHoTen().trim().matches(checkname) == false) {
			errors.rejectValue("hoTen", "staff", "Họ tên không được để trống, chứa ký tự đặc biệt hoặc quá 30 ký tự!");
		}
		if (staff.getSdt().trim().matches(checkphone) == false) {
			errors.rejectValue("sdt", "staff", "số điện thoại phải có 10 số!");
		}
		if (staff.getEmail().trim().matches(checkemail) == false) {
			errors.rejectValue("email", "staff", "email không đúng định dạng!");
		}
		if (staff.getDiaChi().trim().matches(checkaddress) == false) {
			errors.rejectValue("diaChi", "staff",
					"Địa chỉ không được để trống, chứa ký tự đặc biệt hoặc quá 100 ký tự!");
		}
		if (errors.hasErrors()) {
			return false;
		}
		return true;
	}

	public void showStaffs(HttpServletRequest request, ModelMap model, List<NhanVien> staffs) {
		PagedListHolder pagedListHolder = new PagedListHolder(staffs);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
	}

	// --------------------- customer manager ---------------------//

	@RequestMapping("customer")
	public String customer(HttpServletRequest request, ModelMap model) {
		showCustomer(request, model, khachHangDAO.getAllCustomer(factory));
		return "Admin/customer";
	}

	@RequestMapping(value = "customer", params = "btnSearch")
	public String searchCustomer(HttpServletRequest request, ModelMap model) {
		showCustomer(request, model, khachHangDAO.searchCustomers(factory, request.getParameter("name")));
		return "Admin/customer";
	}

	@RequestMapping(value = "customer/{id}.htm", params = "linkBlock")
	public String blockCustomer(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		if (khachHangDAO.setStatus(factory, 0, id) == 1) {
			model.addAttribute("message", "Block thành công");
		}
		showCustomer(request, model, khachHangDAO.getAllCustomer(factory));
		return "redirect:/Admin/customer.htm";
	}

	@RequestMapping(value = "customer/{id}.htm", params = "linkUnBlock")
	public String unBlockCustomer(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		if (khachHangDAO.setStatus(factory, 1, id) == 1) {
			model.addAttribute("message", "UnBlock thành công");
		}
		showCustomer(request, model, khachHangDAO.getAllCustomer(factory));
		return "redirect:/Admin/customer.htm";
	}

	public void showCustomer(HttpServletRequest request, ModelMap model, List<KhachHang> customers) {
		PagedListHolder pagedListHolder = new PagedListHolder(customers);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
	}

	// --------------------- bill manager ---------------------//

	@RequestMapping("billUnConfirm")
	public String billUnConfirm(HttpServletRequest request, ModelMap model) {
		List<DonHang> bills = donHangDAO.getBillUnconfirm(factory);
		Collections.sort(bills);
		showBill(request, model, bills);
		model.addAttribute("nameBill", "billUnConfirm");
		return "Admin/bill";
	}

	@RequestMapping("billCancel")
	public String billCancel(HttpServletRequest request, ModelMap model, HttpSession session) {
		List<DonHang> bills = donHangDAO.getBillCancel(factory, session);
		Collections.sort(bills);
		showBill(request, model, bills);
		model.addAttribute("nameBill", "billCancel");
		return "Admin/bill";
	}

	@RequestMapping("billComplete")
	public String billComplete(HttpServletRequest request, ModelMap model, HttpSession session) {
		List<DonHang> bills = donHangDAO.getBillComplete(factory, session);
		Collections.sort(bills);
		showBill(request, model, bills);
		model.addAttribute("nameBill", "billComplete");
		return "Admin/bill";
	}

	@RequestMapping("billDelivery")
	public String billDelivery(HttpServletRequest request, ModelMap model, HttpSession session) {
		List<DonHang> bills = donHangDAO.getBillDelivering(factory, session);
		Collections.sort(bills);
		showBill(request, model, bills);
		model.addAttribute("nameBill", "billDelivery");
		return "Admin/bill";
	}

	@RequestMapping(value = "billUnConfirm", params = "btnSearch")
	public String searchBillUnConfirm(HttpServletRequest request, ModelMap model) {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		showBill(request, model, donHangDAO.searchBillUnconfirm(factory, from, to));
		model.addAttribute("nameBill", "billUnConfirm");
		model.addAttribute("from", "from");
		model.addAttribute("to", "to");
		return "Admin/bill";
	}

	@RequestMapping(value = "billComplete", params = "btnSearch")
	public String searchBillComplete(HttpServletRequest request, HttpSession session, ModelMap model) {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		showBill(request, model, donHangDAO.searchBillComplete(factory, session, from, to));
		model.addAttribute("nameBill", "billComplete");
		model.addAttribute("from", from);
		model.addAttribute("to", to);
		return "Admin/bill";
	}

	@RequestMapping(value = "billCancel", params = "btnSearch")
	public String searchbillCancel(HttpServletRequest request, ModelMap model) {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		showBill(request, model, donHangDAO.searchBillCancel(factory, from, to));
		model.addAttribute("nameBill", "billCancel");
		model.addAttribute("from", "from");
		model.addAttribute("to", "to");
		return "Admin/bill";
	}

	@RequestMapping(value = "billDelivery", params = "btnSearch")
	public String searchbillDelivery(HttpServletRequest request, ModelMap model, HttpSession session) {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		showBill(request, model, donHangDAO.searchBillDelivering(factory, session, from, to));
		model.addAttribute("nameBill", "billDelivery");
		model.addAttribute("from", "from");
		model.addAttribute("to", "to");
		return "Admin/bill";
	}

	public void showBill(HttpServletRequest request, ModelMap model, List<DonHang> bills) {
		PagedListHolder pagedListHolder = new PagedListHolder(bills);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
	}

	@RequestMapping(value = "billDetail/{id}.htm")
	public String billDetail(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		List<CTDonHang> ctDonHangs = ctDonHangDAO.getDetailBills(factory, id);
		model.addAttribute("list", ctDonHangs);
		model.addAttribute("listNV", nhanVienDAOImpl.getShippers(factory));
		model.addAttribute("bill", donHangDAO.getBill(factory, id));
		return "Admin/billDetail";
	}

	@RequestMapping(value = "billDetail/{id}.htm", params = "btnBrower")
	public String billDetailBrower(HttpServletRequest request, ModelMap model, HttpSession session,
			@PathVariable("id") String id) {
		DonHang bill = donHangDAO.getBill(factory, id);
		bill.setNhanVienG(nhanVienDAOImpl.getStaffByID(factory, request.getParameter("maNVG")));
		bill.setNhanVienD((NhanVien) session.getAttribute("staff"));
		bill.setNgayNhan(new Date());
		donHangDAO.update(factory, bill);
		return "redirect:/Admin/billUnConfirm.htm";
	}

	@RequestMapping(value = "billDetail/{id}.htm", params = "btnCancel")
	public String billDetailCancel(HttpServletRequest request, ModelMap model, HttpSession session,
			@PathVariable("id") String id) {
		DonHang bill = donHangDAO.getBill(factory, id);
		bill.setNhanVienD((NhanVien) session.getAttribute("staff"));
		bill.setTrangThai(-1);
		donHangDAO.update(factory, bill);
		return "redirect:/Admin/billUnConfirm.htm";
	}

	@RequestMapping(value = "billDetail/{id}.htm", params = "btnConfirm")
	public String billDetailConfirm(HttpServletRequest request, ModelMap model, HttpSession session,
			@PathVariable("id") String id) {
		System.out.println("vao day");
		DonHang bill = donHangDAO.getBill(factory, id);
		bill.setNgayNhan(new Date());
		bill.setTrangThai(2);
		donHangDAO.update(factory, bill);
		NhanVien staff = (NhanVien) session.getAttribute("staff");
		HoaDon billl = new HoaDon(
				"HD" + Utils.createID(factory,
						"SELECT max(cast(substring(maHD,3,length(maHD)) AS int)) + 1 FROM HoaDon"),
				new Date(), Utils.getMST(), staff, bill, null);
		hoaDonDAO.insert(factory, billl);
		return "redirect:/pdf/bill.htm?id=" + billl.getMaHD();
	}

	// --------------------- statistic manager ---------------------//
	@RequestMapping(value = "statistic")
	public String statistic() {
		return "Admin/statistic";
	}

	@RequestMapping(value = "statistic", params = "btnSearch")
	public String statistic(HttpServletRequest request, ModelMap model) {
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		List<Revenue> list = getRevenue(from, to);
		model.addAttribute("list", list);
		model.addAttribute("from", from);
		model.addAttribute("to", to);
		Integer sum = 0;
		for (Revenue r : list) {
			sum = sum + r.getTotal().intValue();
		}
		model.addAttribute("sum", sum);
		return "Admin/statistic";
	}

	public List<Revenue> getRevenue(String from, String to) {
		List<Revenue> revenues = donHangDAO.revenue(factory, from, to);
		Integer start = Integer.parseInt(from.substring(5, 7));
		Integer end = Integer.parseInt(to.substring(5, 7));
		List<Revenue> list = new ArrayList<Revenue>();
		Integer yearFrom = Integer.parseInt(from.substring(0, 4));
		Integer yearTo = Integer.parseInt(to.substring(0, 4));
		for (int i = yearFrom; i <= yearTo; i++) {
			int s = 1, e = 12;
			if (i == yearFrom)
				s = start;
			if (i == yearTo)
				e = end;
			Boolean flag = false;
			for (Revenue r : revenues) {
				if (r.getYear() == i) {
					flag = true;
					break;
				}
			}
			if (flag) {
				for (int j = s; j <= e; j++) {
					Boolean f = false;
					for (Revenue r : revenues) {
						if (r.getYear() == i && r.getMonth() == j) {
							f = true;
							list.add(new Revenue(i, j, r.getTotal()));
							break;
						}
					}
					if (!f)
						list.add(new Revenue(i, j, 0L));
				}
			} else {
				list.add(new Revenue(i, 0, 0L));
			}
		}
		return list;
	}

	// --------------------- product manager ---------------------//

	@RequestMapping("productList")
	public String productList(HttpServletRequest request, ModelMap model) {
		showProduct(request, model, sanPhamDAO.getListProduct(factory));
		return "Admin/productList";
	}

	@RequestMapping(value = "productList", params = "btnSearch")
	public String SearchProductList(HttpServletRequest request, ModelMap model) {
		String name = request.getParameter("name");
		showProduct(request, model, sanPhamDAO.getListProductByName(factory, name));
		model.addAttribute("name", name);
		return "Admin/productList";
	}

	public void showProduct(HttpServletRequest request, ModelMap model, List<SanPham> products) {
		PagedListHolder pagedListHolder = new PagedListHolder(products);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
	}

	@RequestMapping("productInsert")
	public String productInsert(ModelMap model) {
		model.addAttribute("loaiSPs", loaiSPDAO.getListCategory(factory));
		model.addAttribute("nhaCungCaps", nhaCungCapDAO.getSuppliers(factory));
		model.addAttribute("product", new SanPham());
		model.addAttribute("btnStatus", "btnAdd");
		return "Admin/product";
	}

	@RequestMapping(value = "productInsert", params = "btnAdd", method = RequestMethod.POST)
	public String productInsert(HttpServletRequest request, ModelMap model, @ModelAttribute("product") SanPham product,
			BindingResult errors, @RequestParam("image") MultipartFile image) {
		if (validateProduct(request, product, errors)) {
			try {
				image.transferTo(new File(basePath.getPathName() + File.separator + image.getOriginalFilename()));
				String a = image.getOriginalFilename();
				product.setHinhAnh(a);
				product.setLoaiSP(loaiSPDAO.getCategoryByID(factory, product.getLoaiSP().getMaLoai()));
				product.setNhaCungCap(nhaCungCapDAO.getSupplierByID(factory, product.getNhaCungCap().getMaNCC()));
				int temp = sanPhamDAO.insert(factory, product);
				if (temp != 0) {
					model.addAttribute("message", "Thêm thành công");
					model.addAttribute("product", new SanPham());
				} else {
					model.addAttribute("message", "Thêm thất bại, vui lòng kiểm tra lại thông tin" + product);
				}
				model.addAttribute("btnStatus", "btnAdd");

			} catch (Exception e) {
				model.addAttribute("message", "file có thể đã tồn tại, vui lòng kiểm tra lại");
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("loaiSPs", loaiSPDAO.getListCategory(factory));
		model.addAttribute("nhaCungCaps", nhaCungCapDAO.getSuppliers(factory));
		return "Admin/product";
	}

	@RequestMapping(value = "productList/{id}.htm", params = "linkDelete")
	public String productDelete(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {

		Integer temp1 = sanPhamDAO.delete(factory, sanPhamDAO.getProduct(factory, id));
		if (temp1 != 0) {
			model.addAttribute("message", "Xóa thành công");
		} else {
			model.addAttribute("message", "Xóa thất bại");
		}
		return "redirect:/Admin/productList.htm";
	}

	@RequestMapping(value = "productEdit/{id}.htm", params = "linkEdit")
	public String editProduct(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		SanPham product = sanPhamDAO.getProduct(factory, id);
		model.addAttribute("product", product);
		model.addAttribute("loaiSPs", loaiSPDAO.getListCategory(factory));
		model.addAttribute("nhaCungCaps", nhaCungCapDAO.getSuppliers(factory));
		model.addAttribute("btnStatus", "btnEdit");
		return "Admin/product";
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "productEdit", params = "btnEdit", method = RequestMethod.POST)
	public String productEdit(HttpServletRequest request, ModelMap model, @ModelAttribute("product") SanPham product,
			BindingResult errors, @RequestParam("image") MultipartFile image) {
		try {
			if (!image.getOriginalFilename().equals("")) {
				image.transferTo(new File(basePath.getPathName() + File.separator + image.getOriginalFilename()));
				String a = image.getOriginalFilename();
				product.setHinhAnh(a);
			}

			product.setLoaiSP(loaiSPDAO.getCategoryByID(factory, product.getLoaiSP().getMaLoai()));
			product.setNhaCungCap(nhaCungCapDAO.getSupplierByID(factory, product.getNhaCungCap().getMaNCC()));
			int temp = sanPhamDAO.update(factory, product);
			System.out.println("temp: " + temp);
			if (temp != 0)
				return "redirect:/Admin/productList.htm?message=suathanhcong";
			else {
				model.addAttribute("message", "Sửa thất bại, vui lòng kiểm tra lại thông tin" + product);
				model.addAttribute("loaiSPs", loaiSPDAO.getListCategory(factory));
				model.addAttribute("nhaCungCaps", nhaCungCapDAO.getSuppliers(factory));
				return "Admin/product";
			}

		} catch (Exception e) {
			model.addAttribute("message", "file có thể đã tồn tại, vui lòng kiểm tra lại");
			model.addAttribute("loaiSPs", loaiSPDAO.getListCategory(factory));
			model.addAttribute("nhaCungCaps", nhaCungCapDAO.getSuppliers(factory));
			return "Admin/product";
		}
	}

	public Boolean validateProduct(HttpServletRequest request, SanPham product, BindingResult errors) {
		String checkid = "([\\p{L}\\s]+){1,10}";
		String checkname = "([\\p{L}\\s]+){1,30}";
		String checkdercript = ".{1,1000}";

		if (!product.getMaSP().trim().matches(checkid)) {
			errors.rejectValue("maSP", "product",
					"Mã không được để trống, không quá 10 ký tự, không chứa kí tự đặc biệt");
		}
		if (!product.getTenSP().trim().matches(checkname)) {
			errors.rejectValue("tenSP", "product",
					"Tên không được để trống, không quá 30 ký tự, không chứa kí tự đặc biệt");
		}
		if (!product.getMoTa().trim().matches(checkdercript)) {
			errors.rejectValue("moTa", "product", "Mô tả không được để trống, không quá 1000 ký tự");
		}
		if (errors.hasErrors()) {
			return false;
		}
		return true;
	}

	// --------------------- supplier manager ---------------------//

	@RequestMapping("supplier")
	public String supplier(HttpServletRequest request, ModelMap model, HttpSession session) {
		showSuppliers(request, model, nhaCungCapDAO.getSuppliers(factory));
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("supplier", new NhaCungCap());
		return "Admin/supplier";
	}

	@RequestMapping(value = "supplier", params = "btnSearch")
	public String searchSupplier(HttpServletRequest request, ModelMap model) {
		String name = request.getParameter("name");
		showSuppliers(request, model, nhaCungCapDAO.searchAllSuppliers(factory, name));
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("supplier", new NhaCungCap());
		model.addAttribute("name", name);
		return "Admin/supplier";
	}

	@RequestMapping(value = "supplier", params = "btnAdd", method = RequestMethod.POST)
	public String addSupplier(HttpServletRequest request, ModelMap model,
			@ModelAttribute("supplier") NhaCungCap supplier, BindingResult errors) {
		if (validateSuppiler(request, supplier, errors)) {
			Integer temp1 = nhaCungCapDAO.insertSupplier(factory, supplier);
			if (temp1 != 0) {
				model.addAttribute("message", "Thêm thành công");
				model.addAttribute("staff", new NhaCungCap());
			} else {
				model.addAttribute("message", "Thêm thất bại, vui lòng kiểm tra lại thông tin" + supplier);
			}
		}
		model.addAttribute("btnStatus", "btnAdd");
		showSuppliers(request, model, nhaCungCapDAO.getSuppliers(factory));

		return "Admin/supplier";
	}

	@RequestMapping(value = "supplier", params = "btnEdit", method = RequestMethod.POST)

	public String editSupplier(HttpServletRequest request, ModelMap model,
			@ModelAttribute("supplier") NhaCungCap supplier, BindingResult errors) {
		if (!validateSuppiler(request, supplier, errors)) {
			System.out.println("Chao edit post");
			showSuppliers(request, model, nhaCungCapDAO.getSuppliers(factory));
			return "Admin/supplier";
		}
		Integer temp = nhaCungCapDAO.updateSupplier(factory, supplier);
		if (temp != 0) {
			model.addAttribute("message", "Sửa thành công");
			model.addAttribute("supplier", new NhaCungCap());
			model.addAttribute("btnStatus", "btnAdd");
		} else {
			model.addAttribute("message", "Sửa thất bại" + supplier);
			model.addAttribute("btnStatus", "btnEdit");
		}
		showSuppliers(request, model, nhaCungCapDAO.getSuppliers(factory));
		return "Admin/supplier";
	}

	@RequestMapping(value = "supplier/{id}.htm", params = "linkEdit")
	public String editSupplier(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		showSuppliers(request, model, nhaCungCapDAO.getSuppliers(factory));
		System.out.println("Chao edit");
		model.addAttribute("btnStatus", "btnEdit");
		NhaCungCap supplier = nhaCungCapDAO.getSupplierByID(factory, id);
		model.addAttribute("supplier", supplier);
		return "Admin/supplier";
	}

	@RequestMapping(value = "supplier/{id}.htm", params = "linkDelete")
	public String deleteSupplier(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		NhaCungCap supplier = nhaCungCapDAO.getSupplierByID(factory, id);
		Integer temp = nhaCungCapDAO.deleteSupplier(factory, supplier);
		System.out.println("xin chao xoa");
		if (temp != 0) {
			model.addAttribute("message", "Xóa thành công");
		} else {
			model.addAttribute("message", "Xóa thất bại");
		}
		return "redirect:/Admin/supplier.htm";
	}

	public void showSuppliers(HttpServletRequest request, ModelMap model, List<NhaCungCap> suppliers) {
		PagedListHolder pagedListHolder = new PagedListHolder(suppliers);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
	}

	public Boolean validateSuppiler(HttpServletRequest request, NhaCungCap supplier, BindingResult errors) {
		String checkname = "([\\p{L}\\s]+){1,50}";
		String checkphone = "[0-9]{10,13}";
		String checkemail = "^[A-Za-z0-9+_.-]+@(.+)$";
		String checkaddress = "([\\p{L}\\s\\d\\,]+){1,200}";

		if (supplier.getTenNCC().trim().matches(checkname) == false) {
			errors.rejectValue("tenNCC", "supplier",
					"Họ tên không được để trống, chứa ký tự đặc biệt hoặc quá 50 ký tự!");
		}
		if (supplier.getSdt().trim().matches(checkphone) == false) {
			errors.rejectValue("sdt", "supplier", "số điện thoại không đúng!");
		}
		if (supplier.getEmail().trim().matches(checkemail) == false) {
			errors.rejectValue("email", "supplier", "email không đúng định dạng!");
		}
		if (supplier.getDiaChi().trim().matches(checkaddress) == false) {
			errors.rejectValue("diaChi", "supplier",
					"Địa chỉ không được để trống, chứa ký tự đặc biệt hoặc quá 200 ký tự!");
		}
		if (errors.hasErrors()) {
			return false;
		}
		return true;
	}

	// --------------------- Order Manager ---------------------//

	@RequestMapping("product-supplier")
	public void getProductsOfSupplier(HttpServletRequest request, ModelMap model, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String maNCC = request.getParameter("maNCC");
		System.out.println("mancc: " + maNCC);
		ArrayList<SanPham> products = sanPhamDAO.getListProductByIDBrand(factory, maNCC);
		mapper.writeValue(response.getOutputStream(), products);
	}

	@RequestMapping("order")
	public String getOrderPage(HttpServletRequest request, ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		ArrayList<NhaCungCap> suppliers = new ArrayList<NhaCungCap>();
		for (NhaCungCap s : nhaCungCapDAO.getSuppliers(factory)) {
			if (s.getSanPhams().size() > 0)
				suppliers.add(s);
		}
		model.addAttribute("suppliers", suppliers);
		return "Admin/order";
	}

	@RequestMapping("orderDetail/{id}.htm")
	public String getOrderDetailPage(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		model.addAttribute("ctPhieuDat", ctPhieuDatDAO.getDetailOrderByID(factory, id));
		return "Admin/orderDetail";
	}

	@RequestMapping(value = "order", params = "btnAdd", method = RequestMethod.POST)
	public String addOrder(HttpServletRequest request, ModelMap model, HttpSession session) {
		String[] sanPhams = request.getParameterValues("sanPham");
		String[] soLuongs = request.getParameterValues("soLuong");
		String[] gias = request.getParameterValues("gia");
		String nccId = request.getParameter("nhaCungCap");

		PhieuDat p = new PhieuDat();
		p.setMaPD("PD"
				+ Utils.createID(factory, "SELECT max(cast(substring(maPD,3,length(maPD)) AS int)) + 1 FROM PhieuDat"));
		p.setNgayTao(new Date());
		p.setNhaCungCap(nhaCungCapDAO.getSupplierByID(factory, nccId));
		p.setNhanVien(nhanVienDAOImpl.getStaffByID(factory, ((NhanVien) session.getAttribute("staff")).getMaNV()));
		System.out.println("==============================" + p.getMaPD());
		System.out.println("==============================" + p.getNhaCungCap().getTenNCC());
		System.out.println("");
		System.out.println();

		phieuDatDAO.insertOrder(factory, p);

		for (int i = 0; i < sanPhams.length; i++) {
			CTPhieuDatPK pk = new CTPhieuDatPK();
			pk.setPhieuDat(p);
			pk.setSanPham(sanPhamDAO.getProduct(factory, sanPhams[i]));

			CTPhieuDat ct = new CTPhieuDat();
			ct.setPk(pk);
			ct.setGia(Long.valueOf(gias[i]));
			ct.setSl(Integer.valueOf(soLuongs[i]));

			ctPhieuDatDAO.insertDetailOrder(factory, ct);
		}
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("suppliers", nhaCungCapDAO.getSuppliers(factory));
		return "Admin/order";
	}

	@RequestMapping(value = "order/{id}.htm", params = "linkDelete")
	public String deleteOrder(HttpServletRequest request, ModelMap model, HttpSession session,
			@PathVariable("id") String id) {
		phieuDatDAO.deleteOrder(factory, phieuDatDAO.getOrderByID(factory, id));
		return "redirect:/Admin/orderMng.htm";
	}

	@RequestMapping(value = "order", params = "btnSearch")
	public String searchOrder(HttpServletRequest request, ModelMap model) {
//        showOrderMng(request, model, phieuDatDAO.getOrderByID(factory, null));
		model.addAttribute("btnStatus", "btnAdd");

		return "Admin/productType";
	}

	@RequestMapping("orderMng")
	public String getOrderMngPage(HttpServletRequest request, ModelMap model) {
		showOrderMng(request, model, phieuDatDAO.getOrders(factory));
		return "Admin/orderMng";
	}

	public void showOrderMng(HttpServletRequest request, ModelMap model, List<PhieuDat> phieuDats) {
		PagedListHolder pagedListHolder = new PagedListHolder(phieuDats);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
	}

	// --------------------- Promotion Manager ---------------------//

	@RequestMapping("promotionList")
	public String promotionList(HttpServletRequest request, ModelMap model) {
		List<KhuyenMai> promotions = khuyenMaiDAO.getPromotions(factory);
		Collections.sort(promotions);
		showPromotion(request, model, promotions);
		return "Admin/promotionList";
	}

	@RequestMapping(value = "promotionList/{id}.htm", params = "linkDelete")
	public String deletePromotion(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id) {
		KhuyenMai promotion = khuyenMaiDAO.getPromotionByID(factory, id);

		List<ChiTietKM> promotionDetails = chiTietKMDAO.getDetailPromotions(factory, id);
		for (ChiTietKM k : promotionDetails) {
			chiTietKMDAO.deleteDetailPromotion(factory, k);
		}
		khuyenMaiDAO.deletePromotion(factory, promotion);
		return "redirect:/Admin/promotionList.htm?status=1";
	}

	@RequestMapping("promotionDetail/{id}.htm")
	public String promotionDetailList(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id) {
		List<ChiTietKM> promotionDetails = chiTietKMDAO.getDetailPromotions(factory, id);
		showPromotionDetail(request, model, promotionDetails);
		List<SanPham> products = sanPhamDAO.getListProduct(factory);
		for(ChiTietKM k: promotionDetails) {
			for(SanPham s: products) {
				if(k.getPk().getSanPham().getMaSP().equals(s.getMaSP())) {
					products.remove(s);
					break;
				}
					
			}
		}
		model.addAttribute("dsSanPham", products);
		model.addAttribute("btnStatus", "btnSave");

		return "Admin/promotionDetail";
	}
	
	@RequestMapping(value = "promotionDetail/{id}.htm", method = RequestMethod.POST, params = "btnSave")
	public String promotionDetailAdd(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id) {
		String maSP = request.getParameter("maSP");
		String giamGia = request.getParameter("giamGia");
		ChiTietKMPK k = new ChiTietKMPK(khuyenMaiDAO.getPromotionByID(factory, id), sanPhamDAO.getProduct(factory, maSP));
		ChiTietKM p = new ChiTietKM(k, Integer.parseInt(giamGia));
		chiTietKMDAO.insertDetailPromotion(factory, p);
		return "redirect:/Admin/promotionDetail/{id}.htm";
	}
	
	@RequestMapping(value = "promotionDetail/{id}.htm", method = RequestMethod.POST, params = "btnEdit")
	public String promotionDetailUpdate(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id) {
		String maSP = request.getParameter("maSP");
		String giamGia = request.getParameter("giamGia");
		ChiTietKMPK k = new ChiTietKMPK(khuyenMaiDAO.getPromotionByID(factory, id), sanPhamDAO.getProduct(factory, maSP));
		ChiTietKM p = new ChiTietKM(k, Integer.parseInt(giamGia));
		chiTietKMDAO.updateDetailPromotion(factory, p);
		return "redirect:/Admin/promotionDetail/{id}.htm";
	}
	
	@RequestMapping(value = "promotionDetail/{id}.htm", params = "linkDelete")
	public String promotionDetailDelete(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id) {
		String maSP = request.getParameter("maSP");
		chiTietKMDAO.deleteDetailPromotion(factory, chiTietKMDAO.getDetailPromotion(factory, maSP, id));
		return "redirect:/Admin/promotionDetail/{id}.htm";
	}
	
	@RequestMapping(value = "promotionDetail/{id}.htm", params = "linkEdit")
	public String promotionDetailEdit(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id) {
		String maSP = request.getParameter("maSP");
		List<ChiTietKM> promotionDetails = chiTietKMDAO.getDetailPromotions(factory, id);
		showPromotionDetail(request, model, promotionDetails);
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		list.add(sanPhamDAO.getProduct(factory, maSP));
		model.addAttribute("dsSanPham", list);
		model.addAttribute("giamGia", request.getParameter("giamGia"));
		model.addAttribute("btnStatus", "btnEdit");
		return "Admin/promotionDetail";
	}

	@RequestMapping(value = "promotionList", params = "btnSearch")
	public String SearchPromotionList(HttpServletRequest request, ModelMap model) {
//		showPhones(request, model, Phone.searchPhones(factory, request.getParameter("name")));
		return "Admin/promotionList";
	}

	public void showPromotion(HttpServletRequest request, ModelMap model, List<KhuyenMai> promotions) {
		PagedListHolder pagedListHolder = new PagedListHolder(promotions);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
	}

	public void showPromotionDetail(HttpServletRequest request, ModelMap model, List<ChiTietKM> promotions) {
		PagedListHolder pagedListHolder = new PagedListHolder(promotions);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
	}

	@RequestMapping("promotionInsert")
	public String getPromotionPage(HttpServletRequest request, ModelMap model) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("maxED", khuyenMaiDAO.getMaxEndDate(factory));
		model.addAttribute("dsSanPham", sanPhamDAO.getListProduct(factory));
		return "Admin/promotion";
	}

	@RequestMapping(value = "promotionInsert", params = "btnAdd", method = RequestMethod.POST)
	public String addPromotion(HttpServletRequest request, ModelMap model, HttpSession session) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ngayBD = request.getParameter("ngayBD");
		String ngayKT = request.getParameter("ngayKT");
		String moTa = request.getParameter("moTa");
		String[] maSPs = request.getParameterValues("maSP");
		String[] giamGias = request.getParameterValues("giamGia");

		KhuyenMai p = new KhuyenMai();
		p.setNgayBD(formatter.parse(ngayBD));
		p.setNgayKT(formatter.parse(ngayKT));
		p.setMoTa(moTa);
		p.setNhanVien(nhanVienDAOImpl.getStaffByID(factory, ((NhanVien) session.getAttribute("staff")).getMaNV()));
		System.out.println(p.getNhanVien().getHoTen());

		khuyenMaiDAO.insertPromotion(factory, p);

		for (int i = 0; i < maSPs.length; i++) {
			ChiTietKMPK pk = new ChiTietKMPK();
			pk.setKhuyenMai(p);
			pk.setSanPham(sanPhamDAO.getProduct(factory, maSPs[i]));

			ChiTietKM ct = new ChiTietKM();

			ct.setPk(pk);
			ct.setGiamGia(Integer.valueOf(giamGias[i]));

			chiTietKMDAO.insertDetailPromotion(factory, ct);
		}
		return "redirect:/Admin/promotionInsert.htm";
	}

	// --------------------- Import Manager ---------------------//

	@RequestMapping("import/{id}.htm")
	public String getImportPage(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("ctPhieuDat", ctPhieuDatDAO.getDetailOrderByID(factory, id));
		model.addAttribute("maPD", id);
		return "Admin/import";
	}

	@RequestMapping("importDetail/{id}.htm")
	public String getimproDetailPage(HttpServletRequest request, ModelMap model, @PathVariable("id") String id) {
		PhieuNhap imports = phieuDatDAO.getOrderByID(factory, id).getPhieuNhap();
		List<CTPhieuNhap> importDetails = (List<CTPhieuNhap>) phieuNhapDAO.getImportByID(factory, imports.getMaPN());
		model.addAttribute("ctPhieuNhap", importDetails);
		return "Admin/importDetail";
	}

	@RequestMapping(value = "import/{id}.htm", params = "btnAdd", method = RequestMethod.POST)
	public String addImport(HttpServletRequest request, ModelMap model, @PathVariable("id") String id,
			HttpSession session) {
		String[] maSPs = request.getParameterValues("maSP");
		String[] soLuongs = request.getParameterValues("sl");
		String[] gias = request.getParameterValues("gia");
		long tongTien = 0;
		for (int i = 0; i < soLuongs.length; i++) {
			tongTien = tongTien + Long.valueOf(soLuongs[i]) * Long.valueOf(gias[i]);
		}

		PhieuNhap p = new PhieuNhap();
		p.setMaPN("PN" + Utils.createID(factory,
				"SELECT max(cast(substring(maPN,3,length(maPN)) AS int)) + 1 FROM PhieuNhap"));
		p.setNgayTao(new Date());
		p.setTongTien(tongTien);
		p.setPhieuDat(phieuDatDAO.getOrderByID(factory, id));
		p.setNhanVien(nhanVienDAOImpl.getStaffByID(factory, ((NhanVien) session.getAttribute("staff")).getMaNV()));
		phieuNhapDAO.insertImport(factory, p);

		for (int i = 0; i < soLuongs.length; i++) {
			SanPham sp = sanPhamDAO.getProduct(factory, maSPs[i]);
			CTPhieuNhapPK pk = new CTPhieuNhapPK();
			pk.setPhieuNhap(p);
			pk.setSanPham(sp);

			CTPhieuNhap ct = new CTPhieuNhap();
			ct.setPk(pk);
			ct.setGia(Long.valueOf(gias[i]));
			ct.setSl(Integer.valueOf(soLuongs[i]));

			ctPhieuNhapDAO.insertDetailImport(factory, ct);

			if (!soLuongs[i].equals("0"))
				sp.setGia(Long.valueOf(gias[i]) * 12 / 10);
			sp.setSlt(Integer.valueOf(soLuongs[i]) + sp.getSlt());
			sanPhamDAO.update(factory, sp);
		}
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("suppliers", nhaCungCapDAO.getSuppliers(factory));
		return "redirect:/Admin/orderMng.htm";
	}
}
