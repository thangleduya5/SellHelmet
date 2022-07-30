package shm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name = "LOAISP")
public class LoaiSP {
	
	@Id @Column(name = "MALOAI")
	private String maLoai;
	
	@Column(name = "TENLOAI")
	private String tenLoai;
	
	@OneToMany(mappedBy = "loaiSP", fetch = FetchType.EAGER)
	private Collection<SanPham> sanPhams;
	
	public LoaiSP() {
		
	}

	public LoaiSP(String maLoai, String tenLoai, Collection<SanPham> sanPhams) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.sanPhams = sanPhams;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public Collection<SanPham> getSanPhams() {
		return sanPhams;
	}

	public void setSanPhams(Collection<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}
	
}
