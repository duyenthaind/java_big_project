package com.haui.thaind.processor;

import com.haui.common.Config;
import com.haui.thaind.common.Ranking;
import com.haui.thaind.dao.ReportDAO;
import com.haui.entities.KetQua;
import com.haui.entities.Khoa;
import com.haui.entities.MonHoc;
import com.haui.entities.SinhVien;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author duyenthai
 */
public class ExportExcelProcessor {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        pubjob();
    }

    public static void pubjob() {
        Thread t = createJob();
        try {
            EXECUTOR_SERVICE.submit(t);
        } catch (Exception ex) {
            System.err.println("Publish job error, trace: " + ex);
            ex.printStackTrace();
        }
    }

    private static Thread createJob() {
        Thread job = new Thread(() -> {
            try {
                createWorkbook();
            } catch (Exception ex) {
                System.err.println("Create job error, trace: " + ex);
                ex.printStackTrace();
            }
        });
        return job;
    }

    private static void createWorkbook() {
        try {
            Config.loadConfig();
            ReportDAO dao = new ReportDAO();
            dao.fetchResult();
            UUID uuid = UUID.randomUUID();
            String exportFileName = "/Export_file_" + uuid.toString() + ".xlsx";
            String fullExportFileName = Config.BASE_DIR_EXPORT + exportFileName;
            System.out.println("Exporting data to file: " + fullExportFileName);

            int rowIndex = -1;
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Student report");
            Row headerRow = sheet.createRow(++rowIndex);
            Cell cellHeader = headerRow.createCell(0);
            cellHeader.setCellStyle(createStyleForHeader(sheet));
            cellHeader.setCellValue("THÔNG TIN IN BẰNG");

            String[] infoArr = {"major", "name", "ranking", "dob", "graduatedYear"};
            SinhVien info = dao.getStudent();
            Khoa department = dao.getDepartment();
            for (int index = -1; ++index < infoArr.length; ) {
                Row row = sheet.createRow(++rowIndex);
                Cell cell = row.createCell(0);
                Cell cell2 = row.createCell(1);
                switch (infoArr[index]) {
                    case "major":
                        cell.setCellValue("Ngành học");
                        cell2.setCellValue(department.getTenKhoa());
                        break;
                    case "name":
                        cell.setCellValue("Họ và tên");
                        cell2.setCellValue(info.getTenSV());
                        break;
                    case "ranking":
                        cell.setCellValue("Xếp loại");
                        cell2.setCellValue(getRanking(dao.getAvgPoints()));
                        break;
                    case "graduatedYear":
                        cell.setCellValue("Ngày sinh");
                        Date date = new Date(info.getNgaySinh() * 1000L);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
                        cell2.setCellValue(sdf.format(date));
                        break;
                    case "dob":
                        cell.setCellValue("Năm tốt nghiệp");
                        cell2.setCellValue("");
                        break;
                    default:
                        break;
                }
            }

            Row headerRow2 = sheet.createRow(++rowIndex);
            Cell cellHeaderRow2 = headerRow2.createCell(0);
            cellHeaderRow2.setCellStyle(createStyleForHeader(sheet));
            cellHeaderRow2.setCellValue("THÔNG TIN KẾT QUẢ HỌC TẬP");

            // 1 empty row
            ++rowIndex;
            Row resultHeader1 = sheet.createRow(++rowIndex);
            Cell cellResultHeader1 = resultHeader1.createCell(0);
            cellResultHeader1.setCellStyle(createStyleForCell(sheet));
            cellResultHeader1.setCellValue("- Phần kết quả học tập");

            String resultHeadersArr[] = {"STT", "Tên HP", "Số TC", "Điểm"};
            // result header 1
            Row resultRowHeader1 = sheet.createRow(++rowIndex);
            for (int index = -1; ++index < resultHeadersArr.length; ) {
                Cell cell = resultRowHeader1.createCell(index);
                cell.setCellStyle(createStyleForResultCell(sheet));
                cell.setCellValue(resultHeadersArr[index]);
            }

            if (!dao.fetchResult()) {
                return;
            }
            List<KetQua> listResults = dao.getListResults();
            Map<String, MonHoc> mapSubjects = dao.getMapSubject();
            for (int index = -1; ++index < listResults.size(); ) {
                Row row = sheet.createRow(++rowIndex);
                KetQua result = listResults.get(index);
                for (int indexResult = -1; ++indexResult < resultHeadersArr.length; ) {
                    Cell cell = row.createCell(indexResult);
                    cell.setCellStyle(createStyleForResultCellRow(sheet));
                    String value = "";
                    switch (indexResult) {
                        case 0:
                            value += index + "";
                            break;
                        case 1:
                            if (mapSubjects.get(result.getMaHP()) != null) {
                                value += mapSubjects.get(result.getMaHP()).getTenMH();
//                                cell.setCellValue(mapSubjects.get(result.getMaHP()).getTenMH());
                            }
                            break;
                        case 2:
                            if (mapSubjects.get(result.getMaHP()) != null) {
                                value += mapSubjects.get(result.getMaHP()).getSoTinChi() + "";
//                                cell.setCellValue(mapSubjects.get(result.getMaHP()).getSoTinChi());
                            }
                            break;
                        case 3:
                            value += getPointA(result.getDiem());
                            break;
                        default:
                            break;

                    }
                    cell.setCellValue(value);
                }
            }

            // up border row to close table
            Row emptyRowWithUpBorder = sheet.createRow(++rowIndex);
            for (int index = -1; ++index < resultHeadersArr.length; ) {
                Cell cell = emptyRowWithUpBorder.createCell(index);
                cell.setCellStyle(createStyleUpBorder(sheet));
                cell.setCellValue("");
            }

            // result header 2
            Row resultHeader2 = sheet.createRow(++rowIndex);
            Cell cellResultHeader2 = resultHeader2.createCell(0);
            cellResultHeader2.setCellStyle(createStyleForCell(sheet));
            cellResultHeader2.setCellValue("- Học phần thay thế đồ án khóa luân tốt nghiệp");

            Row resultRowHeader2 = sheet.createRow(++rowIndex);
            for (int index = -1; ++index < resultHeadersArr.length; ) {
                Cell cell = resultRowHeader2.createCell(index);
                cell.setCellStyle(createStyleForResultCell(sheet));
                cell.setCellValue(resultHeadersArr[index]);
            }

            // up border row to close table
            Row emptyRowWithUpBorder1 = sheet.createRow(++rowIndex);
            for (int index = -1; ++index < resultHeadersArr.length; ) {
                Cell cell = emptyRowWithUpBorder1.createCell(index);
                cell.setCellStyle(createStyleUpBorder(sheet));
                cell.setCellValue("");
            }

            // result header 3
            Row resultHeader3 = sheet.createRow(++rowIndex);
            Cell cellResultHeader3 = resultHeader3.createCell(0);
            cellResultHeader3.setCellStyle(createStyleForCell(sheet));
            cellResultHeader3.setCellValue("- Kết quả đánh giá quá trình học tập");

            String[] finalRsArr = {"totalCredits", "realTotalCredits", "avgPoints", "ranking"};
            for (int index = -1; ++index < finalRsArr.length; ) {
                Row row = sheet.createRow(++rowIndex);
                Cell cell = row.createCell(0);
                Cell cell2 = row.createCell(1);
                cell.setCellStyle(createStyleForResultCell(sheet));
                cell2.setCellStyle(createStyleForResultCell(sheet));
                switch (finalRsArr[index]) {
                    case "totalCredits":
                        cell.setCellValue("Tổng số tín chỉ tích lũy");
                        cell2.setCellValue("149");
                        break;
                    case "realTotalCredits":
                        cell.setCellValue("Tổng số TC tích lũy để tính điểm TBCTL");
                        cell2.setCellValue(dao.getTotalCredits());
                        break;
                    case "avgPoints":
                        cell.setCellValue("Điểm TBCTL");
                        cell2.setCellValue(String.format("%.2f", dao.getAvgPoints()));
                        break;
                    case "ranking":
                        cell.setCellValue("Xếp hạng tốt nghiệp");
                        cell2.setCellValue(getRanking(dao.getAvgPoints()));
                        break;
                    default:
                        break;
                }
            }

            FileOutputStream outputStream = new FileOutputStream(fullExportFileName);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
            System.out.println("Saved exported file as " + fullExportFileName);
            JOptionPane.showMessageDialog(new JFrame(), "Exported file to: " + fullExportFileName);

        } catch (Exception ex) {
            System.err.println("Create workbook error, trace: " + ex);
            ex.printStackTrace();
        }
    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static CellStyle createStyleForCell(Sheet sheet) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;
    }

    private static CellStyle createStyleForResultCell(Sheet sheet) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.THICK);
        cellStyle.setBorderRight(BorderStyle.THICK);
        cellStyle.setBorderTop(BorderStyle.THICK);
        cellStyle.setBorderBottom(BorderStyle.THICK);
        return cellStyle;
    }

    private static CellStyle createStyleForResultCellRow(Sheet sheet) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.THICK);
        cellStyle.setBorderRight(BorderStyle.THICK);
        return cellStyle;
    }

    private static CellStyle createStyleUpBorder(Sheet sheet) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THICK);
        return cellStyle;
    }

    private static String getRanking(float point) {
        String result;
        if (point == 0.0) {
            return "";
        }
        if (point < 2.5) {
            result = Ranking.BELOW_AVERAGE.get();
        } else if (point < 3.2) {
            result = Ranking.AVERAGE.get();
        } else if (point < 3.5) {
            result = Ranking.GOOD.get();
        } else {
            result = Ranking.EXCELLENT.get();
        }
        return result;
    }

    private static String getPointA(float point) {
        String result;
        if (point == 0.0) {
            return "";
        }
        if (point < 1.5) {
            result = "D";
        } else if (point < 2.0) {
            result = "D+";
        } else if (point < 2.5) {
            result = "C";
        } else if (point < 3) {
            result = "C+";
        } else if (point < 3.5) {
            result = "B";
        } else if (point < 4) {
            result = "B+";
        } else {
            result = "A";
        }
        return result;
    }
}
