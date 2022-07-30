package shm.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity @Table(name = "CHITIETKM")
@AssociationOverrides({
    @AssociationOverride(name = "pk.khuyenMai", 
        joinColumns = @JoinColumn(name = "MAKM")),
    @AssociationOverride(name = "pk.sanPham", 
        joinColumns = @JoinColumn(name = "MASP")) })
public class ChiTietKM {
	
	@Embedded @Id
	ChiTietKMPK pk;
	
	@Column(name = "GIAMGIA")
	private int giamGia;
	
	public ChiTietKM() {
		
	}

	public ChiTietKM(ChiTietKMPK pk, int giamGia) {
		super();
		this.pk = pk;
		this.giamGia = giamGia;
	}

	public ChiTietKMPK getChiTietKMPK() {
		return pk;
	}

	public void setChiTietKMPK(ChiTietKMPK pk) {
		this.pk = pk;
	}

	public int getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(int giamGia) {
		this.giamGia = giamGia;
	}
	
}
