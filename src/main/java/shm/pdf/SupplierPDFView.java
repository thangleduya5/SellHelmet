package shm.pdf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import shm.entity.NhaCungCap;
import shm.entity.NhanVien;
import shm.entity.SanPham;

public class SupplierPDFView extends AbstractPdfView {

	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseFont bf = BaseFont.createFont("C:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bf, 13);
		Font fontTitle = new Font(bf, 15);
		Font fontNameCompany = new Font(bf, 12);

		Paragraph nameCompany = new Paragraph("Công ty cổ phần ASIA", fontNameCompany);
		nameCompany.setAlignment(Element.ALIGN_LEFT);
		nameCompany.setSpacingAfter(28);

		Paragraph p1 = new Paragraph("Người lập báo cáo", fontNameCompany);
		p1.setAlignment(Element.ALIGN_RIGHT);
		p1.setSpacingAfter(15);

		Paragraph p2 = new Paragraph(((NhanVien) request.getSession().getAttribute("staff")).getHoTen(),
				fontNameCompany);
		p2.setAlignment(Element.ALIGN_RIGHT);
		p2.setSpacingAfter(15);
		
		Paragraph p3 = new Paragraph("Ngày lập: "+(new SimpleDateFormat("dd/MM/yyyy").format(new Date())),
				fontNameCompany);
		p3.setAlignment(Element.ALIGN_RIGHT);
		p3.setSpacingAfter(15);

		Paragraph p = new Paragraph("DANH SÁCH NHÀ CUNG CẤP", fontTitle);
		p.setAlignment(Element.ALIGN_CENTER);
		p.setSpacingAfter(20);
		PdfPTable table;
		table = new PdfPTable(new float[] { 3, 3, 3, 5, 3 });// Thiáº¿t láº­p tá»‰ lá»‡ giá»¯a cÃ¡c cá»™t trong báº£ng
		table.setWidthPercentage(110);// Thiáº¿t láº­p chiá»?u rá»™ng
		table.addCell(PDF.getCell("Mã NCC", fontTitle));
		table.addCell(PDF.getCell("Tên NCC", fontTitle));
		table.addCell(PDF.getCell("SDT", fontTitle));
		table.addCell(PDF.getCell("Địa chỉ", fontTitle));
		table.addCell(PDF.getCell("Email", fontTitle));

		ArrayList<NhaCungCap> suppliers = (ArrayList<NhaCungCap>) model.get("suppliers");

		for (NhaCungCap s : suppliers) {
			table.addCell(PDF.getCell(s.getMaNCC(), font));
			table.addCell(PDF.getCell(s.getTenNCC(), font));
			table.addCell(PDF.getCell(s.getSdt(), font));
			table.addCell(PDF.getCell(s.getDiaChi(), font));
			table.addCell(PDF.getCell(s.getEmail(), font));
		}
		document.add(nameCompany);
		document.add(p1);
		document.add(p2);
		document.add(p3);
		document.add(p);
		document.add(table);
	}

}
