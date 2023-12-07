/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.awt.Desktop;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import model.HoaDon;
import response.HDCTResponse;
import response.XemCTHDResponse;

/**
 *
 * @author Dell
 */
public class ExportPDFHoaDon {

    public static final String pathUnicode = "font\\unicode.ttf";

    public void exportBill(XemCTHDResponse hoaDon, List<HDCTResponse> listHoaDonChiTiet, String pathFile) {
        try {
            String path = pathFile + "\\" + "hoa_don" + Calendar.getInstance().getTimeInMillis() + ".pdf";
            File file = new File(path);
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            float col = 280f;
            float columWidth[] = {col, col};

            PdfFont font = PdfFontFactory.createFont(pathUnicode, BaseFont.IDENTITY_H);

            Paragraph header = new Paragraph()
                    .add(new Text("Bill Alone Wolves Store")
                            .setFont(font)
                            .setFontSize(40f) // Increase font size
                            .setFontColor(Color.WHITE))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setBackgroundColor(new DeviceRgb(0, 102, 255))
                    .setMarginTop(50f) // Increase top margin for more space
                    .setMarginBottom(30f);

            document.add(header);

            Paragraph billInfo = new Paragraph()
                    .add(new Text("Mã hóa đơn: " + hoaDon.getMaHD() + "\nAlone Wolves Store")
                            .setFont(font)
                            .setFontSize(18f) // Decrease font size
                            .setFontColor(Color.BLACK))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20f) // Increase top margin for more space
                    .setMarginBottom(30f);  // Add spacing after the paragraph

            document.add(billInfo);

            float colWidth[] = {80, 230, 200, 200};
            Table customerInforTableKH = new Table(colWidth);
            customerInforTableKH.setFont(font);

            customerInforTableKH.addCell(new Cell(0, 4)
                    .add("Thông tin khách hàng:")
                    .setBold()
                    .setBorder(Border.NO_BORDER));

// Add customer information line by line
            customerInforTableKH.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER));
            customerInforTableKH.addCell(new Cell().add(hoaDon.getTenKH()).setBorder(Border.NO_BORDER));

            customerInforTableKH.addCell(new Cell().add("SĐT:").setBorder(Border.NO_BORDER));
            customerInforTableKH.addCell(new Cell().add(hoaDon.getSdt()).setBorder(Border.NO_BORDER));

            customerInforTableKH.addCell(new Cell().add("Địa chỉ:").setBorder(Border.NO_BORDER));
            customerInforTableKH.addCell(new Cell().add(hoaDon.getDiaChi()).setBorder(Border.NO_BORDER));

            customerInforTableKH.addCell(new Cell().add("Ngày thanh toán:").setBorder(Border.NO_BORDER));
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa dd-MM-yyyy");
            String date = sdf.format(hoaDon.getNgayThanhToan());
            customerInforTableKH.addCell(new Cell().add(date).setBorder(Border.NO_BORDER));

            float itemColWidth[] = {30, 110, 170, 50, 110, 110};

            int index = 1;
            Table itemTable = new Table(itemColWidth);
            itemTable.setFont(font);
            itemTable.addCell(new Cell().add("STT").setBackgroundColor(new DeviceRgb(0, 102, 255)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tên sản phẩm").setBackgroundColor(new DeviceRgb(0, 102, 255)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thông tin SP").setBackgroundColor(new DeviceRgb(0, 102, 255)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("SL").setBackgroundColor(new DeviceRgb(0, 102, 255)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Giá bán").setBackgroundColor(new DeviceRgb(0, 102, 255)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Thành tiền").setBackgroundColor(new DeviceRgb(0, 102, 255)).setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
            DecimalFormat df = new DecimalFormat("#,###");
            for (HDCTResponse xx : listHoaDonChiTiet) {
                itemTable.addCell(new Cell().add(index++ + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getTenSP()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getHang() + " " + xx.getMau() + " " + "Size: " + xx.getSize()).setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getSoLuong() + "").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getDonGia() + " Vnđ").setBorder(Border.NO_BORDER));
                itemTable.addCell(new Cell().add(xx.getSoLuong() * xx.getDonGia() + " Vnđ").setBorder(Border.NO_BORDER));
            }

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tổng tiền").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTongTien()) + " Vnđ").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tiền giảm giá").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            Float tienGiamGia = hoaDon.getTienGiam()== null ? new Float(0) : hoaDon.getTienGiam();
            itemTable.addCell(new Cell().add(df.format(tienGiamGia) + " Vnđ").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tiền khách trả").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            Float tienKhachTra = hoaDon.getTienKhachTra() == null ? new Float(0) : hoaDon.getTienKhachTra();
            itemTable.addCell(new Cell().add(df.format(tienKhachTra) + " Vnđ").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
           
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Ngân hàng").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            Float tienKhachCK = hoaDon.getTienkhachCK() == null ? new Float(0) : hoaDon.getTienkhachCK();
            itemTable.addCell(new Cell().add(df.format(tienKhachCK) + " Vnđ").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
          
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBorder(Border.NO_BORDER));
            itemTable.addCell(new Cell().add("Tiền thừa").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
            itemTable.addCell(new Cell().add(df.format(hoaDon.getTienThua()) + " Vnđ").setBackgroundColor(new DeviceRgb(0, 102, 255)).setBold().setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

            //DecimalFormat df = new DecimalFormat("#,###");
            float colWidthLoiChao12[] = {80, 220, 230, 200};
            Table customerLuuY = new Table(colWidthLoiChao12);
            customerLuuY.setFont(font);
            customerLuuY.addCell(new Cell(0, 4)
                    .add("Lưu ý: Quý khách hãy giữ lại hóa đơn,\nNếu sản phẩm gặp vấn đề gì có thể trả hàng trong vòng 3 ngày,\n chỉ thực hiện trả hàng cho những sản phẩm không áp dụng khuyến mại.\nNhững sản phẩm được đánh dấu (*) ở giá bán là những sản phẩm đã có giảm giá khuyến mại").setItalic().setFontColor(Color.RED).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidth1[] = {80, 220, 230, 200};
            Table customer1 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("----------------------------------------------------------").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            float colWidthLoiChao[] = {80, 220, 230, 200};
            Table customerLoiChao = new Table(colWidthLoiChao);
            customerLoiChao.setFont(font);
            customerLoiChao.addCell(new Cell(0, 4)
                    .add("Trường cao đẳng FPT Polytechnich, P.Kiều Mai,\nP.Phúc Diễn, Q.Bắc Từ Liêm, TP.Hà Nội").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            Table customer3 = new Table(colWidth1);
            customer1.setFont(font);
            customer1.addCell(new Cell(0, 4)
                    .add("Cảm ơn quý khách và hẹn gặp lại\nHotline: 0686868686").setItalic().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

            //document.add(table);
            document.add(new Paragraph("\n"));
            document.add(customerInforTableKH);
            document.add(new Paragraph("\n"));
            document.add(itemTable);
            //document.add(customerInforTable);
            document.add(new Paragraph("\n"));
            //document.add(itemTable);
            document.add(customer1);
            document.add(customerLoiChao);
            document.add(customer3);
            if (!Desktop.isDesktopSupported()) {
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
