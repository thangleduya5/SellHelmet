package shm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity @Table(name = "KHUYENMAI")
public class KhuyenMai {

	@Id @GeneratedValue @Column(name = "MAKM")
	private int maKM;
	
	@Column(name = "NGAYBD") @Temporal(TemporalType.DATE) @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date ngayBD;
	
	@Column(name = "NGAYKT") @Temporal(TemporalType.DATE) @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date ngayKT;
	
	@Column(name = "MOTA")
	private String moTa;
	
	@ManyToOne @JoinColumn(name = "MANV")
	private NhanVien nhanVien;
	
	@OneToMany(mappedBy = "pk.khuyenMai", fetch = FetchType.EAGER)
	private Collection<ChiTietKM> chiTietKMs;
	
	public KhuyenMai() {
		
	}

	public KhuyenMai(int maKM, Date ngayBD, Date ngayKT, String moTa, NhanVien nhanVien,
			Collection<ChiTietKM> chiTietKMs) {
		super();
		this.maKM = maKM;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
		this.moTa = moTa;
		this.nhanVien = nhanVien;
		this.chiTietKMs = chiTietKMs;
	}

	public int getMaKM() {
		return maKM;
	}

	public void setMaKM(int maKM) {
		this.maKM = maKM;
	}

	public Date getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}

	public Date getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Collection<ChiTietKM> getChiTietKMs() {
		return chiTietKMs;
	}

	public void setChiTietKMs(Collection<ChiTietKM> chiTietKMs) {
		this.chiTietKMs = chiTietKMs;
	}
	
}
