package shm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CTPhieuNhapPK implements Serializable{

	@ManyToOne @JoinColumn(name = "MASP")
	private SanPham sanPham;
	
	@ManyToOne @JoinColumn(name = "MAPN")
	private PhieuNhap phieuNhap;
	
	public CTPhieuNhapPK() {
		
	}

	public CTPhieuNhapPK(SanPham sanPham, PhieuNhap phieuNhap) {
		super();
		this.sanPham = sanPham;
		this.phieuNhap = phieuNhap;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public PhieuNhap getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(PhieuNhap phieuNhap) {
		this.phieuNhap = phieuNhap;
	}
}

