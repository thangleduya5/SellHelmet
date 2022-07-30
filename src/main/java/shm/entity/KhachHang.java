package shm.entity;

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

@Entity @Table(name = "KHACHHANG")
public class KhachHang {

	@Id @Column(name = "MAKH")
	private String maKH;
	
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
	
	@Column(name = "TRANGTHAI")
	private int trangThai;
	
	@ManyToOne @JoinColumn(name = "TENDN")
	private TaiKhoan taiKhoan;
	
	@OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER)
	private Collection<DonHang> donHangs;
	
	public KhachHang() {
	
	}

	public KhachHang(String maKH, String email, String hoTen, String gioiTinh, String diaChi, String sdt, int trangThai,
			TaiKhoan taiKhoan, Collection<DonHang> donHangs) {
		super();
		this.maKH = maKH;
		this.email = email;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.trangThai = trangThai;
		this.taiKhoan = taiKhoan;
		this.donHangs = donHangs;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
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

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public Collection<DonHang> getDonHangs() {
		return donHangs;
	}

	public void setDonHangs(Collection<DonHang> donHangs) {
		this.donHangs = donHangs;
	}
	
}
