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


@Entity @Table(name = "PHIEUDAT")
public class PhieuDat {
	
	@Id @Column(name = "MAPD")
	private String maPD;
	
	@Column(name = "NGAYTAO") @Temporal(TemporalType.DATE) @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date ngayTao;
	
	@ManyToOne @JoinColumn(name = "MANCC")
	private NhaCungCap nhaCungCap;
	
	@ManyToOne @JoinColumn(name = "MANV")
	private NhanVien nhanVien;
	
	@OneToMany(mappedBy = "pk.phieuDat", fetch = FetchType.EAGER)
	private Collection<CTPhieuDat> ctPhieuDats;
	
	@OneToMany(mappedBy = "phieuDat", fetch = FetchType.EAGER)
	private Collection<PhieuNhap> phieuNhaps;
	
	public PhieuDat() {
		
	}

	public PhieuDat(String maPD, Date ngayTao, NhaCungCap nhaCungCap, NhanVien nhanVien,
			Collection<CTPhieuDat> ctPhieuDats, Collection<PhieuNhap> phieuNhaps) {
		super();
		this.maPD = maPD;
		this.ngayTao = ngayTao;
		this.nhaCungCap = nhaCungCap;
		this.nhanVien = nhanVien;
		this.ctPhieuDats = ctPhieuDats;
		this.phieuNhaps = phieuNhaps;
	}

	public String getMaPD() {
		return maPD;
	}

	public void setMaPD(String maPD) {
		this.maPD = maPD;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Collection<CTPhieuDat> getCtPhieuDats() {
		return ctPhieuDats;
	}

	public void setCtPhieuDats(Collection<CTPhieuDat> ctPhieuDats) {
		this.ctPhieuDats = ctPhieuDats;
	}

	public Collection<PhieuNhap> getPhieuNhaps() {
		return phieuNhaps;
	}

	public void setPhieuNhaps(Collection<PhieuNhap> phieuNhaps) {
		this.phieuNhaps = phieuNhaps;
	}

}
