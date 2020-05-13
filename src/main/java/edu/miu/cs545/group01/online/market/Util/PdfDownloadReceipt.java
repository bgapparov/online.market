package edu.miu.cs545.group01.online.market.Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.miu.cs545.group01.online.market.domain.Order;
import edu.miu.cs545.group01.online.market.domain.OrderProduct;
import edu.miu.cs545.group01.online.market.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfDownloadReceipt {
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Logger logger = LoggerFactory.getLogger(PdfDownloadReceipt.class);

    public static ByteArrayInputStream Report(Order order) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 1, 1, 1});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Product", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Quantity", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Total", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            int totalQuantity = 0;
            float totalSum = 0;
            for (OrderProduct orderProduct : order.getOrderedProducts()) {
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(orderProduct.getProduct().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(String.valueOf(orderProduct.getProduct().getPrice())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                int quantity = orderProduct.getQuantity();
                totalQuantity += quantity;
                cell = new PdfPCell(new Phrase(String.valueOf(quantity)));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                float total = orderProduct.getTotalPayment();
                totalSum += total;
                cell = new PdfPCell(new Phrase(String.valueOf(total)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            hcell = new PdfPCell(new Phrase("Total sum", headFont));
            hcell.setColspan(2);
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(totalQuantity + "", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(totalSum+"", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(hcell);

            PdfWriter.getInstance(document, out);
            document.open();
            Anchor anchor = new Anchor("Recipt #"+order.getId(), catFont);
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Paragraph("Seller: "+ order.getSeller()));
            paragraph.add(new Paragraph("Buyer: "+ order.getBuyer()));
            paragraph.add(new Paragraph("Shipping address: "+ order.getShippingAddress()));

            Paragraph title = new Paragraph("MIU Online shopping", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title );
            document.add(anchor);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}