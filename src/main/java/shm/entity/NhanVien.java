package shm.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name = "NHANVIEN")
public class NhanVien implements Serializable{

	@Id @Column(name = "MANV")
	private String maNV;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "HOTEN")
	private String hoTen;
	
	@Column(name = "GIOITINH")
	private String gioiTinh;
	
	@Column(name = "DIACHI")
	private String diaChi;
	
	@Column(name = "SDT")
	private String sdt;
		
	@OneToOne @JoinColumn(name = "TENDN")
	private TaiKhoan taiKhoan;
	
	@OneToMany(mappedBy = "nhanVienD", fetch = FetchType.LAZY)
	private Collection<DonHang> donHangDs;
	
	@OneToMany(mappedBy = "nhanVienG", fetch = FetchType.LAZY)
	private Collection<DonHang> donHangGs;
	
	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
	private Collection<HoaDon> hoaDons;
	
	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
	private Collection<PhieuTra> phieuTras;
	
	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
	private Collection<PhieuDat> phieuDats;

	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
	private Collection<PhieuNhap> phieuNhaps;
//	
	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY)
	private Collection<KhuyenMai> khuyenMais;
	
	public NhanVien() {
		
	}

	public NhanVien(String maNV, String email, String hoTen, String gioiTinh, String diaChi, String sdt,
			TaiKhoan taiKhoan, Collection<DonHang> donHangDs, Collection<DonHang> donHangGs, Collection<HoaDon> hoaDons,
			Collection<PhieuTra> phieuTras, Collection<PhieuDat> phieuDats, Collection<PhieuNhap> phieuNhaps,
			Collection<KhuyenMai> khuyenMais) {
		super();
		this.maNV = maNV;
		this.email = email;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.taiKhoan = taiKhoan;
		this.donHangDs = donHangDs;
		this.donHangGs = donHangGs;
		this.hoaDons = hoaDons;
		this.phieuTras = phieuTras;
		this.phieuDats = phieuDats;
		this.phieuNhaps = phieuNhaps;
		this.khuyenMais = khuyenMais;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public Collection<DonHang> getDonHangDs() {
		return donHangDs;
	}

	public void setDonHangDs(Collection<DonHang> donHangDs) {
		this.donHangDs = donHangDs;
	}

	public Collection<DonHang> getDonHangGs() {
		return donHangGs;
	}

	public void setDonHangGs(Collection<DonHang> donHangGs) {
		this.donHangGs = donHangGs;
	}

	public Collection<HoaDon> getHoaDons() {
		return hoaDons;
	}

	public void setHoaDons(Collection<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}

	public Collection<PhieuTra> getPhieuTras() {
		return phieuTras;
	}

	public void setPhieuTras(Collection<PhieuTra> phieuTras) {
		this.phieuTras = phieuTras;
	}

	public Collection<PhieuDat> getPhieuDats() {
		return phieuDats;
	}

	public void setPhieuDats(Collection<PhieuDat> phieuDats) {
		this.phieuDats = phieuDats;
	}

	public Collection<PhieuNhap> getPhieuNhaps() {
		return phieuNhaps;
	}

	public void setPhieuNhaps(Collection<PhieuNhap> phieuNhaps) {
		this.phieuNhaps = phieuNhaps;
	}

	public Collection<KhuyenMai> getKhuyenMais() {
		return khuyenMais;
	}

	public void setKhuyenMais(Collection<KhuyenMai> khuyenMais) {
		this.khuyenMais = khuyenMais;
	}
	
}
