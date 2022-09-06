package shm.entity;

import java.io.Serializable;
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

@Entity @Table(name = "TAIKHOAN")
public class TaiKhoan implements Serializable{

	@Id @Column(name = "TENDN")
	private String tenDN;
	
	@Column(name = "MATKHAU")
	private String matKhau;
	
	@ManyToOne @JoinColumn(name = "MAQUYEN")
	private Quyen quyen;
		
	public TaiKhoan() {
		
	}

	public TaiKhoan(String tenDN, String matKhau, Quyen quyen) {
		super();
		this.tenDN = tenDN;
		this.matKhau = matKhau;
		this.quyen = quyen;
	}

	public String getTenDN() {
		return tenDN;
	}

	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Quyen getQuyen() {
		return quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

}
