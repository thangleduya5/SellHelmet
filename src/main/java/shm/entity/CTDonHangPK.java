package shm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CTDonHangPK implements Serializable{

	@ManyToOne @JoinColumn(name = "MASP")
	private SanPham sanPham;
	
	@ManyToOne @JoinColumn(name = "MADH")
	private DonHang donHang;
	
	public CTDonHangPK() {
		
	}

	public CTDonHangPK(SanPham sanPham, DonHang donHang) {
		super();
		this.sanPham = sanPham;
		this.donHang = donHang;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}
	
}
