package shm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name = "QUYEN")
public class Quyen {

	@Id @GeneratedValue @Column(name = "MAQUYEN")
	private int maQuyen;
	
	@Column(name = "TENQUYEN")
	private String tenQuyen;
	
	@OneToMany(mappedBy = "quyen", fetch = FetchType.EAGER)
	private Collection<TaiKhoan> taiKhoans;
	
	public Quyen() {
		
	}

	public Quyen(int maQuyen, String tenQuyen, Collection<TaiKhoan> taiKhoans) {
		super();
		this.maQuyen = maQuyen;
		this.tenQuyen = tenQuyen;
		this.taiKhoans = taiKhoans;
	}

	public int getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(int maQuyen) {
		this.maQuyen = maQuyen;
	}

	public String getTenQuyen() {
		return tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

	public Collection<TaiKhoan> getTaiKhoans() {
		return taiKhoans;
	}

	public void setTaiKhoans(Collection<TaiKhoan> taiKhoans) {
		this.taiKhoans = taiKhoans;
	}
	
}
