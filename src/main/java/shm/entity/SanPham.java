package shm.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity @Table(name = "SANPHAM")
public class SanPham implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @Column(name = "MASP")
	private String maSP;
	
	@Column(name = "TENSP")
	private String tenSP;
	
	@Column(name = "MOTA")
	private String moTa;
	
	@Column(name = "HINHANH")
	private String hinhAnh;
	
	@Column(name = "GIA")
	private long gia;
	
	@Column(name = "SLT")
	private int slt;
	
	@Column(name = "SPMOI")
	private int spMoi;
	
	@ManyToOne @JoinColumn(name = "MALOAI")
	private LoaiSP loaiSP;
	
	@ManyToOne @JoinColumn(name = "MANCC")
	private NhaCungCap nhaCungCap;
	
	@OneToMany(mappedBy = "pk.sanPham", fetch = FetchType.LAZY)
	@JsonBackReference
	private Collection<CTPhieuDat> ctPhieuDats;
	
	@OneToMany(mappedBy = "pk.sanPham", fetch = FetchType.LAZY)
	@JsonBackReference
	private Collection<CTDonHang> ctDonHangs;
	
	@OneToMany(mappedBy = "pk.sanPham", fetch = FetchType.LAZY)
	@JsonBackReference
	private Collection<CTPhieuNhap> ctPhieuNhaps;

	@OneToMany(mappedBy = "pk.sanPham", fetch = FetchType.LAZY)
	@JsonBackReference
	private Collection<ChiTietKM> chiTietKMs;
	
	public SanPham() {
		
	}

	public SanPham(String maSP, String tenSP, String moTa, String hinhAnh, long gia, int slt, int spMoi, LoaiSP loaiSP,
			NhaCungCap nhaCungCap, Collection<CTPhieuDat> ctPhieuDats, Collection<CTDonHang> ctDonHangs,
			Collection<CTPhieuNhap> ctPhieuNhaps, Collection<ChiTietKM> chiTietKMs) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
		this.gia = gia;
		this.slt = slt;
		this.spMoi = spMoi;
		this.loaiSP = loaiSP;
		this.nhaCungCap = nhaCungCap;
		this.ctPhieuDats = ctPhieuDats;
		this.ctDonHangs = ctDonHangs;
		this.ctPhieuNhaps = ctPhieuNhaps;
		this.chiTietKMs = chiTietKMs;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public long getGia() {
		return gia;
	}

	public void setGia(long gia) {
		this.gia = gia;
	}

	public int getSlt() {
		return slt;
	}

	public void setSlt(int slt) {
		this.slt = slt;
	}

	public int getSpMoi() {
		return spMoi;
	}

	public void setSpMoi(int spMoi) {
		this.spMoi = spMoi;
	}

	public LoaiSP getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(LoaiSP loaiSP) {
		this.loaiSP = loaiSP;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public Collection<CTPhieuDat> getCtPhieuDats() {
		return ctPhieuDats;
	}

	public void setCtPhieuDats(Collection<CTPhieuDat> ctPhieuDats) {
		this.ctPhieuDats = ctPhieuDats;
	}

	public Collection<CTDonHang> getCtDonHangs() {
		return ctDonHangs;
	}

	public void setCtDonHangs(Collection<CTDonHang> ctDonHangs) {
		this.ctDonHangs = ctDonHangs;
	}

	public Collection<CTPhieuNhap> getCtPhieuNhaps() {
		return ctPhieuNhaps;
	}

	public void setCtPhieuNhaps(Collection<CTPhieuNhap> ctPhieuNhaps) {
		this.ctPhieuNhaps = ctPhieuNhaps;
	}

	public Collection<ChiTietKM> getChiTietKMs() {
		return chiTietKMs;
	}

	public void setChiTietKMs(Collection<ChiTietKM> chiTietKMs) {
		this.chiTietKMs = chiTietKMs;
	}

	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", moTa=" + moTa + ", hinhAnh=" + hinhAnh + ", gia=" + gia
				+ ", slt=" + slt + ", spMoi=" + spMoi +  "] " + loaiSP.getTenLoai() + nhaCungCap.getMaNCC();
	}
	
}
