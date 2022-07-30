package shm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CTPhieuDatPK implements Serializable{

	@ManyToOne @JoinColumn(name = "MASP")
	private SanPham sanPham;
	
	@ManyToOne @JoinColumn(name = "MAPD")
	private PhieuDat phieuDat;
	
	public CTPhieuDatPK() {
		
	}

	public CTPhieuDatPK(SanPham sanPham, PhieuDat phieuDat) {
		super();
		this.sanPham = sanPham;
		this.phieuDat = phieuDat;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public PhieuDat getPhieuDat() {
		return phieuDat;
	}

	public void setPhieuDat(PhieuDat phieuDat) {
		this.phieuDat = phieuDat;
	}
	
}
