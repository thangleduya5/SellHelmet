package shm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ChiTietKMPK implements Serializable{

	@ManyToOne @JoinColumn(name = "MAKM")
	private KhuyenMai khuyenMai;
	
	@ManyToOne @JoinColumn(name = "MASP")
	private SanPham sanPham;
	
	public ChiTietKMPK() {
		
	}

	public ChiTietKMPK(KhuyenMai khuyenMai, SanPham sanPham) {
		super();
		this.khuyenMai = khuyenMai;
		this.sanPham = sanPham;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	
}
