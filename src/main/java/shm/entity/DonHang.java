package shm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity @Table(name = "DONHANG")
public class DonHang {
	
	@Id @Column(name = "MADH")
	private String maDH;
	
	@Column(name = "TRANGTHAI")
	private int trangThai;
	
	@Column(name = "HOTENNN")
	private String hoTenNN;
	
	@Column(name = "DIACHINN")
	private String diaChiNN;
	
	@Column(name = "SDTNN")
	private String sdtNN;
	
	@Column(name = "EMAILNN")
	private String emailNN;
	
	@Column(name = "NGAYTAO") @Temporal(TemporalType.DATE) @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date ngayTao;
	
	@Column(name = "NGAYNHAN") @Temporal(TemporalType.DATE) @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date ngayNhan;
	
	@Column(name = "TONGTIEN")
	private long tongTien;
	
	@ManyToOne @JoinColumn(name = "MANVD")
	private NhanVien nhanVienD;
	
	@ManyToOne @JoinColumn(name = "MANVG")
	private NhanVien nhanVienG;
	
	@ManyToOne @JoinColumn(name = "MAKH")
	private KhachHang khachHang;
	
	@OneToMany(mappedBy = "pk.donHang", fetch = FetchType.EAGER)
	private Collection<CTDonHang> ctDonHangs;

	@OneToMany(mappedBy = "donHang", fetch = FetchType.EAGER)
	private Collection<HoaDon> hoaDons;
	
	public DonHang() {
		
	}

	public DonHang(String maDH, int trangThai, String hoTenNN, String diaChiNN, String sdtNN, String emailNN,
			Date ngayTao, Date ngayNhan, long tongTien, NhanVien nhanVienD, NhanVien nhanVienG, KhachHang khachHang,
			Collection<CTDonHang> ctDonHangs, Collection<HoaDon> hoaDons) {
		super();
		this.maDH = maDH;
		this.trangThai = trangThai;
		this.hoTenNN = hoTenNN;
		this.diaChiNN = diaChiNN;
		this.sdtNN = sdtNN;
		this.emailNN = emailNN;
		this.ngayTao = ngayTao;
		this.ngayNhan = ngayNhan;
		this.tongTien = tongTien;
		this.nhanVienD = nhanVienD;
		this.nhanVienG = nhanVienG;
		this.khachHang = khachHang;
		this.ctDonHangs = ctDonHangs;
		this.hoaDons = hoaDons;
	}

	public String getMaDH() {
		return maDH;
	}

	public void setMaDH(String maDH) {
		this.maDH = maDH;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getHoTenNN() {
		return hoTenNN;
	}

	public void setHoTenNN(String hoTenNN) {
		this.hoTenNN = hoTenNN;
	}

	public String getDiaChiNN() {
		return diaChiNN;
	}

	public void setDiaChiNN(String diaChiNN) {
		this.diaChiNN = diaChiNN;
	}

	public String getSdtNN() {
		return sdtNN;
	}

	public void setSdtNN(String sdtNN) {
		this.sdtNN = sdtNN;
	}

	public String getEmailNN() {
		return emailNN;
	}

	public void setEmailNN(String emailNN) {
		this.emailNN = emailNN;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Date getNgayNhan() {
		return ngayNhan;
	}

	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}

	public long getTongTien() {
		return tongTien;
	}

	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
	}

	public NhanVien getNhanVienD() {
		return nhanVienD;
	}

	public void setNhanVienD(NhanVien nhanVienD) {
		this.nhanVienD = nhanVienD;
	}

	public NhanVien getNhanVienG() {
		return nhanVienG;
	}

	public void setNhanVienG(NhanVien nhanVienG) {
		this.nhanVienG = nhanVienG;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Collection<CTDonHang> getCtDonHangs() {
		return ctDonHangs;
	}

	public void setCtDonHangs(Collection<CTDonHang> ctDonHangs) {
		this.ctDonHangs = ctDonHangs;
	}

	public Collection<HoaDon> getHoaDons() {
		return hoaDons;
	}

	public void setHoaDons(Collection<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}

}
