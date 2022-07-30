package shm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.sql.Update;
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

import shm.DAO.CTDonHangDAOImpl;
import shm.DAO.ChiTietKMDAO;
import shm.DAO.ChiTietKMDAOImpl;
import shm.DAO.DonHangDAOImpl;
import shm.DAO.KhachHangDAOImpl;
import shm.DAO.LoaiSPDAOImpl;
import shm.DAO.NhaCungCapDAOImpl;
import shm.DAO.SanPhamDAOImpl;
import shm.DAO.TaiKhoanDAOImpl;
import shm.entity.CTDonHang;
import shm.entity.CTDonHangPK;
import shm.entity.DonHang;
import shm.entity.KhachHang;
import shm.entity.SanPham;




@Controller
@Transactional
@RequestMapping("User/")
public class UserController {

	@Autowired
	SessionFactory factory;
	
	SanPhamDAOImpl sanPhamDAOImpl = new SanPhamDAOImpl();
	NhaCungCapDAOImpl nhaCungCapDAOImpl = new NhaCungCapDAOImpl();
	TaiKhoanDAOImpl taiKhoanImpl = new TaiKhoanDAOImpl();
	KhachHangDAOImpl khachHangDAOImpl = new KhachHangDAOImpl();
	CTDonHangDAOImpl ctDonHangDAOImpl = new CTDonHangDAOImpl();
	DonHangDAOImpl donHangDAOImpl = new DonHangDAOImpl();
	ChiTietKMDAOImpl chiTietKMDAOImpl = new ChiTietKMDAOImpl();
	LoaiSPDAOImpl loaiSPDAOImpl = new LoaiSPDAOImpl();
	
	
	@RequestMapping("home")
	public String index(HttpServletRequest request, HttpSession session, ModelMap model) {
		System.out.println("VO DAY");
		
		session.setAttribute("brands", nhaCungCapDAOImpl.getSuppliers(factory));
		session.setAttribute("categorys", loaiSPDAOImpl.getListCategory(factory));
		
		Map<SanPham, Integer> newProducts = new HashMap<SanPham, Integer>();
		for(SanPham s: sanPhamDAOImpl.getListNewProduct(factory)) {
			newProducts.put(s, chiTietKMDAOImpl.getDiscount(factory, s.getMaSP()));
		}
		
		Map<SanPham, Integer> hotProducts = new HashMap<SanPham, Integer>();
		for(SanPham s: sanPhamDAOImpl.getListHotProdduct(factory, 5)) {
			hotProducts.put(s, chiTietKMDAOImpl.getDiscount(factory, s.getMaSP()));
		}
		
		Map<SanPham, Integer> hotSaleProducts = new HashMap<SanPham, Integer>();
		for(SanPham s: sanPhamDAOImpl.getListHotSaleProduct(factory, 10)) {
			hotSaleProducts.put(s, chiTietKMDAOImpl.getDiscount(factory, s.getMaSP()));
		}
		
		session.setAttribute("newProducts", newProducts);
		session.setAttribute("hotProducts", hotProducts);
		session.setAttribute("hotSaleProducts", hotSaleProducts);	
		
		showProducts(request, model, sanPhamDAOImpl.getListProduct(factory));
		
		return "User/home";
	}
	
	@RequestMapping(value = "home", params = "btnSearchByName", method = RequestMethod.POST)
	public String fillProduct(HttpServletRequest request, ModelMap model) {
		model.addAttribute("products", sanPhamDAOImpl.getListProductByName(factory, request.getParameter("nameProduct")));
		model.addAttribute("searchAll", "1");
		return "User/home";
	}
	
	@RequestMapping("home/{nameBrand}")
	public String fillBrand(HttpServletRequest request, ModelMap model, @PathVariable("nameBrand") String nameBrand) {
		showProducts(request, model, sanPhamDAOImpl.getListProductByNameBrand(factory, nameBrand));
		model.addAttribute("brandsss", nameBrand);
		return "User/home";
	}
	
	public void showProducts(HttpServletRequest request, ModelMap model, List<SanPham> sanPhams) {
		PagedListHolder pagedListHolder = new PagedListHolder(sanPhams);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(9);
		model.addAttribute("pagedListHolder", pagedListHolder);
	}
	
	@RequestMapping("login")
	public String login() {
		return "User/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("customer");
		return "redirect:/User/home.htm";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, ModelMap model, HttpSession session) {
		String idCustomer = request.getParameter("name");
		String passCustomer = request.getParameter("pass");
		if (taiKhoanImpl.getRole(factory, passCustomer, idCustomer).equals("Customer")) {
			KhachHang k = khachHangDAOImpl.getCustomer(factory, idCustomer);
			if (k.getTrangThai() == 0) {
				model.addAttribute("message", "Tài khoản của bạn đã bị khóa, vui lòng liên hệ để được mở khóa!!");
			} else {
				session.setAttribute("customer", k);
				session.setAttribute("detailBills",ctDonHangDAOImpl.getDetailBills(factory, 
						donHangDAOImpl.getBillUnBuy(factory, 
								((KhachHang)session.getAttribute("customer")).getMaKH()).getMaDH()));
				return "redirect:/User/home.htm";
			}
		} else {
			model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng");
		}
		return "User/login";
	}
	
	@RequestMapping("register")
	public String register(ModelMap model) {
		model.addAttribute("customer", new KhachHang());
		return "User/register";
	}
	
	@RequestMapping("info")
	public String info(HttpSession session, ModelMap model) {
		model.addAttribute("customer", (KhachHang)session.getAttribute("customer"));
		return "User/info";
	}
	
	@RequestMapping(value = "changePass")
	public String editCustomerPass() {
		return "User/changePass";
	}

	@RequestMapping(value = "changePass", params = "btnUpdatePass", method = RequestMethod.POST)
	public String editCustomerPass(HttpServletRequest request, ModelMap model, HttpSession session) {
		String newPass = request.getParameter("newPass");
		String oldPass = request.getParameter("oldPass");
		String newPassReset = request.getParameter("newPassReset");		
		String idCustomer = ((KhachHang)session.getAttribute("customer")).getTaiKhoan().getTenDN();
		Boolean flag=true;
		if(taiKhoanImpl.getRole(factory, oldPass, idCustomer).equals("")) {
			model.addAttribute("oldPassEr", "Mật khẩu cũ không chính xác");
			flag=false;
		}
		if(newPass.equals("")) {
			model.addAttribute("newPassEr", "Vui lòng nhập mật khẩu mới");
			flag=false;
		}
		if(!newPass.equals(newPassReset)) {
			model.addAttribute("newPassResetEr", "Mật khẩu nhập lại không khớp");
			flag=false;
		}
		if(!flag) {
			model.addAttribute("oldPass", oldPass);
			model.addAttribute("newPass", newPass);
			model.addAttribute("newPassReset", newPassReset);
			return "User/changePass";
		}
		Integer temp = taiKhoanImpl.updatePass(factory, newPassReset, idCustomer);
		if (temp == 0) {
			model.addAttribute("message", "Thay đổi mật khẩu thất bại");
		} 
		model.addAttribute("customer", (KhachHang)session.getAttribute("customer"));
		return "User/info";
	}
	
	@RequestMapping("history")
	public String history(HttpServletRequest request, ModelMap model, HttpSession session) {
		showBills(request, model, session);
		return "User/history";
	}
		
	public void showBills(HttpServletRequest request, ModelMap model, HttpSession session) {
		System.out.println("toi day di cu");
		List<DonHang> list = donHangDAOImpl.getBills(factory, ((KhachHang)session.getAttribute("customer")).getMaKH().toString());
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);

	}
	
	@RequestMapping(value = "history/idBill={idBill}.htm", params = "linkDetail")
	public String historyBill(HttpServletRequest request, ModelMap model, @PathVariable("idBill") String idBill) {
		System.out.println("xin chao");
		showDetailBills(request, model, idBill);
		model.addAttribute("idBill", idBill);
		return "User/historyDetail";
	}
	
	public void showDetailBills(HttpServletRequest request, ModelMap model, String idBill) {
		List<CTDonHang> list = ctDonHangDAOImpl.getDetailBills(factory, idBill);
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);

	}
	
	@RequestMapping("cart")
	public String cart(HttpServletRequest request, ModelMap model, HttpSession session) {
		showDetailBills(request, model, session);
		return "User/cart";
	}
	
	@RequestMapping(value = "cart/idBill={idBill}+idProduct={idProduct}.htm", params = "linkDelete")
	public String deleteCart(HttpServletRequest request, ModelMap model, HttpSession session,
			@PathVariable("idProduct") String idProduct, @PathVariable("idBill") String idBill) {
		ctDonHangDAOImpl.deleteDetailBill(factory, ctDonHangDAOImpl.getDetailBill(factory, idBill, idProduct));
		session.setAttribute("detailBills",ctDonHangDAOImpl.getDetailBills(factory, 
				donHangDAOImpl.getBillUnBuy(factory, 
						((KhachHang)session.getAttribute("customer")).getMaKH()).getMaDH()));
		return "redirect:/User/cart.htm";
	}
	
	public void showDetailBills(HttpServletRequest request, ModelMap model, HttpSession session) {
		List<CTDonHang> list = (List<CTDonHang>) session.getAttribute("detailBills");
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		Long sum = 0L;
		for (CTDonHang k : list) {
			sum = sum + k.getPk().getSanPham().getGia() * k.getSl() *
				(100 - chiTietKMDAOImpl.getDiscount(factory, k.getPk().getSanPham().getMaSP()))/100;
			System.out.println(chiTietKMDAOImpl.getDiscount(factory, k.getPk().getSanPham().getMaSP()));
		}
		model.addAttribute("sum", sum);
		model.addAttribute("pagedListHolder", pagedListHolder);

	}
	
	@RequestMapping("helmet/{id}")
	public String product(ModelMap model, @PathVariable("id") String id) {
		model.addAttribute("p", sanPhamDAOImpl.getProduct(factory, id));
		model.addAttribute("discount", chiTietKMDAOImpl.getDiscount(factory, id));
		model.addAttribute("detailBill", new CTDonHang());
		return "User/product";
	}
	
	@RequestMapping(value = "info", params = "btnUpdate", method = RequestMethod.POST)
	public String editCustomer(HttpServletRequest request, ModelMap model,
			@ModelAttribute("customer") KhachHang customer, BindingResult errors, HttpSession session) {
		if (validateCustomer(customer, errors)) {
			Integer temp = khachHangDAOImpl.updateCustomer(factory, customer);
			if (temp != 0) {
				model.addAttribute("message", "Sửa thành công");
				session.setAttribute("customer", khachHangDAOImpl.getCustomer(factory, ((KhachHang)session.getAttribute("customer")).getMaKH()));
				
			} else {
				model.addAttribute("message", "Sửa thất bại" + customer);
			}
		}
		return "User/info";
	}
	
	public Boolean validateCustomer(@ModelAttribute("customer") KhachHang customer, BindingResult errors) {
		String checkname = "([\\p{L}\\s]+){1,50}";
		String checkphone = "[0-9]{10}";
		String checkemail = "^[A-Za-z0-9+_.-]+@(.+)$";
		String checkaddress = "([\\p{L}\\s\\d\\,]+){1,100}";
		String checkid = "[A-Za-z0-9]{1,10}";
		String checkpass = "[A-Za-z0-9]{1,16}";
		if (customer.getHoTen().trim().matches(checkname) == false) {
			errors.rejectValue("nameCustomer", "customer",
					"Họ tên không được để trống, chứa ký tự đặc biệt hoặc quá 50 ký tự!");
		}
		if (customer.getSdt().trim().matches(checkphone) == false) {
			errors.rejectValue("phoneCustomer", "customer", "số điện thoại không đúng!");
		}
		if (customer.getEmail().trim().matches(checkemail) == false) {
			errors.rejectValue("emailCustomer", "customer", "email không đúng định dạng!");
		}
		if (customer.getDiaChi().trim().matches(checkaddress) == false) {
			errors.rejectValue("addressCustomer", "customer",
					"Địa chỉ không được để trống, chứa ký tự đặc biệt hoặc quá 100 ký tự!");
		}
		if (errors.hasErrors()) {
			return false;
		}
		return true;
	}

	
	@RequestMapping(value = "helmet/{id}", params = "linkAdd")
	public String addProduct(ModelMap model, @PathVariable("id") String id, HttpSession session) {
		if (session.getAttribute("customer") == null) {
			return "redirect:/User/login.htm";
		}
		DonHang b = donHangDAOImpl.getBillUnBuy(factory, ((KhachHang)session.getAttribute("customer")).getMaKH());
		CTDonHang db = ctDonHangDAOImpl.getDetailBill(factory, b.getMaDH(), id);
		if (db == null) {
			CTDonHang detailBill = new CTDonHang();
			SanPham p = sanPhamDAOImpl.getProduct(factory, id);
			detailBill.setSl(1);
			detailBill.setGia(p.getGia());

			detailBill.setPk(new CTDonHangPK(p, b));
			Integer temp = ctDonHangDAOImpl.insertDetailBill(factory, detailBill);
		} else {
			db.setSl(db.getSl() + 1);
			ctDonHangDAOImpl.updateDetailBill(factory, db);
		}

		session.setAttribute("detailBills", ctDonHangDAOImpl.getDetailBills(factory, b.getMaDH()));
		return "redirect:/User/home.htm";
	}
	
	@RequestMapping(value = "product", params = "btnBuy")
	public String addDetailBill(HttpServletRequest request, ModelMap model, HttpSession session,
			@ModelAttribute("detailBill") CTDonHang detailBill, BindingResult errors) {
		if (session.getAttribute("customer") == null) {
			return "redirect:/User/login.htm";
		}

		if (detailBill.getSl() < 1) {
			errors.rejectValue("sl", "detailBill", "số lượng phải lớn hơn 1");
		}
		if(detailBill.getSl()> sanPhamDAOImpl.getProduct(factory, detailBill.getPk().getSanPham().getMaSP()).getSlt()) {
			errors.rejectValue("sl", "detailBill", "số lượng không vượt quá SLT (" + sanPhamDAOImpl.getProduct(factory, detailBill.getPk().getSanPham().getMaSP()).getSlt() + ")");
		}
		if (errors.hasErrors()) {
			model.addAttribute("message", "vui lòng sửa các lỗi");
			model.addAttribute("p", sanPhamDAOImpl.getProduct(factory, detailBill.getPk().getSanPham().getMaSP()));
			return "User/product";
		}
		DonHang b = donHangDAOImpl.getBillUnBuy(factory, ((KhachHang)session.getAttribute("customer")).getMaKH());
		detailBill.getPk().setDonHang(b);
		detailBill.setGia(0L);
		Integer temp = ctDonHangDAOImpl.insertDetailBill(factory, detailBill);
		if (temp == 0) {
			model.addAttribute("message", "Thêm vào giỏ hàng lỗi, sản phẩm đã được thêm trước đó rồi");
			model.addAttribute("p", sanPhamDAOImpl.getProduct(factory, detailBill.getPk().getSanPham().getMaSP()));

			return "User/phone";
		}
		session.setAttribute("detailBills", ctDonHangDAOImpl.getDetailBills(factory, b.getMaDH()));
		return "redirect:/User/cart.htm";
	}
}
