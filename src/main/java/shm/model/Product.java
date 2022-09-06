package shm.model;

import shm.entity.SanPham;

public class Product {
	SanPham sanPham;
	Integer discount;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(SanPham sanPham, Integer discount) {
		super();
		this.sanPham = sanPham;
		this.discount = discount;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
}
