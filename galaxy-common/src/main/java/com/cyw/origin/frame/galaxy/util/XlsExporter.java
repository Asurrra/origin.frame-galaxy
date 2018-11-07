package com.cyw.origin.frame.galaxy.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * 导出xls文件
 * <p/>
 * 
 * <pre>
 * XlsExporter exporter = XlsExporter.create(outputStream).buildColumn("渠道名称", "channelName").buildColumn("券码", "code");
 * exporter.append(channelCouponCodeList);
 * exporter.flushWork();
 * </pre>
 *
 * @author god
 */
public class XlsExporter<T> {

    private static final Logger log = LoggerFactory.getLogger(XlsExporter.class);

    private static final int MAX_SHEET_RECORD_NUM = 65535;
    // private static final int MAX_SHEET_NUM = 255;
    private OutputStream outputStream;
    private LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();// 列信息
    // (key:中文标题
    // =
    // value:dataList记录集属性名)
    private List<String> uniqFieldNameList = new ArrayList<>();// 去重的属性名
    private HSSFWorkbook workBook = new HSSFWorkbook(); // 创建Excel文档
    private HSSFSheet lastSheet = workBook.createSheet();

    private XlsExporter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public static XlsExporter create(OutputStream outputStream) {
        return new XlsExporter(outputStream);
    }

    public static XlsExporter create(HttpServletResponse response, String fileName) {
        ServletOutputStream outputStream;
        try {
            fileName += ".xls";
            String disposition = "attachment;filename=\"" + fileName + "\"; filename*=UTF-8''"
                    + URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", disposition);
            response.setContentType("application/octet-stream");
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            log.error("XlsExporter.create(HttpServletResponse, String) exception", e);
            throw new RuntimeException(e);
        }
        return new XlsExporter(outputStream);
    }

    /**
     * <p>
     * 设置列字段和第一行中文标题名
     * </p>
     * <p/>
     * 
     * <pre>
     * buildColumn("入库编号", "stockInId");
     * </pre>
     *
     * @param title 中文标题名
     * @param fieldName 数据对象属性名
     */
    public XlsExporter buildColumn(String title, String fieldName) {
        columnMap.put(title, fieldName);
        return this;
    }

    public XlsExporter append(List<T> dataList) {
        write(dataList);
        return this;
    }

    public XlsExporter append(T... dataArr) {
        write(Arrays.asList(dataArr));
        return this;
    }

    private void write(List<T> dataList) {
        uniqDataList(dataList);
        Map colMap = getColumnMap();
        int dataSize = dataList.size();
        log.debug("XlsExporter.write data size : " + dataSize);
        // 累计记录数量
        for (int k = 0; k <= dataSize / MAX_SHEET_RECORD_NUM; k++) {
            int fromIndex = k * MAX_SHEET_RECORD_NUM;
            int toIndex = fromIndex + MAX_SHEET_RECORD_NUM;
            if (toIndex > dataSize) {
                toIndex = dataSize;
            }
            List subList = dataList.subList(fromIndex, toIndex);
            if (lastSheet.getLastRowNum() >= MAX_SHEET_RECORD_NUM - 1) {
                // 如果没有最后一个工作表,或者已经写满则新建一个工作页
                lastSheet = workBook.createSheet();
            }
            // 创建标题行
            if (lastSheet.getLastRowNum() < 1) {
                if (MapUtils.isNotEmpty(getColumnMap())) {
                    HSSFRow titleRow = lastSheet.createRow(0); // 下标为0的行开始
                    HSSFCell[] firstColCell = new HSSFCell[getColumnMap().size()];
                    int index = 0;
                    for (Object titleName : colMap.keySet()) {
                        firstColCell[index] = titleRow.createCell(index);
                        firstColCell[index].setCellValue(new HSSFRichTextString(titleName.toString()));
                        index++;
                    }
                }
            }

            for (int i = 0; i < subList.size(); i++) {
                HSSFRow row = lastSheet.createRow(lastSheet.getLastRowNum() + 1); // 创建一行
                Object record = subList.get(i);
                int index = 0;
                for (Object propertyName : colMap.values()) {
                    Object value = getPropertyValue(record, propertyName.toString());
                    HSSFCell cell = row.createCell(index);
                    cell.setCellValue(isEmpty(value) ? "" : value.toString());
                    log.debug("cell.setCellValue(" + (isEmpty(value) ? "" : value.toString()) + ")");
                    index++;
                }
            }
        }
    }

    public void flushWork() {
        try {
            outputStream.flush();
            workBook.write(outputStream);
            workBook.close();
        } catch (IOException e) {
            log.error("XlsExporter.flushWork() exception", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 做记录去重操作
     */
    private void uniqDataList(List<T> dataList) {
        List<String> uniqFieldNameList = getUniqFieldNameList();
        if (CollectionUtils.isEmpty(uniqFieldNameList)) {
            return;
        }
        // 保存要去重的行索引号
        List<Integer> UniqRecordIndex = new ArrayList<>();

        for (int i = 0; i < dataList.size() - 1; i++) {
            List<Boolean> boolList = new ArrayList<>();
            int nextIndex = i + 1;
            Object currentRecord = dataList.get(i);
            Object nextRecord = dataList.get(nextIndex);
            for (String uniqKey : uniqFieldNameList) {
                Object currentVal = XlsExporter.getPropertyValue(currentRecord, uniqKey);
                Object nextVal = XlsExporter.getPropertyValue(nextRecord, uniqKey);
                boolean b = (currentVal != null && currentVal.equals(nextVal));
                boolList.add(b);
            }

            // 如果没有false的话,做一个标识
            Boolean[] array = boolList.toArray(new Boolean[0]);
            if (org.apache.commons.lang3.BooleanUtils.and(array)) {
                UniqRecordIndex.add(nextIndex);
            }
        }

        for (Integer index : UniqRecordIndex) {
            Object record = dataList.get(index);
            for (String uniqKey : uniqFieldNameList) {
                try {
                    XlsExporter.setPropertyValue(record, uniqKey, "");
                } catch (Exception e) {
                    log.warn(e.getMessage());
                }
            }
        }

    }

    private static Object getPropertyValue(Object bean, String propertyName) {
        Object value;
        try {
            if (bean instanceof Map) {
                Map map = (Map) bean;
                value = MapUtils.getObject(map, propertyName, "");
            } else {
                value = PropertyUtils.getSimpleProperty(bean, propertyName);
            }
        } catch (Exception e) {
            log.warn(null, e);
            return "";
        }
        return value;
    }

    private static void setPropertyValue(Object bean, String propertyName, Object propertyValue) {
        try {
            if (bean instanceof Map) {
                Map map = (Map) bean;
                map.put(propertyName, propertyValue);
            } else {
                PropertyUtils.setSimpleProperty(bean, propertyName, propertyValue);
            }
        } catch (Exception e) {
            log.warn(null, e);
        }
    }

    private static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            if (StringUtils.isBlank(obj.toString())) {
                return true;
            }
        }

        return false;
    }

    public List<String> getUniqFieldNameList() {
        return uniqFieldNameList;
    }

    public void setUniqFieldNameList(List<String> uniqFieldNameList) {
        this.uniqFieldNameList = uniqFieldNameList;
    }

    public void setUniqFieldNameList(String... uniqFieldNameList) {
        this.uniqFieldNameList = Arrays.asList(uniqFieldNameList);
    }

    public Map<String, String> getColumnMap() {
        return columnMap;
    }

    /**
     * 合并某一列中值相同并且相邻的几个单元格
     *
     * @param line
     */
    public void mergeCell(Integer line, Integer lineTwo) {
        HSSFSheet sheet = null;
        Integer startRow = null;
        String firstValue = null;
        String firstValue2 = null;
        int rowNum = 0;
        for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
            sheet = workBook.getSheetAt(i);
            for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                HSSFRow row = sheet.getRow(j);
                rowNum = row.getLastCellNum();
                if (line > rowNum || lineTwo > rowNum) {
                    break;
                }
                String cellValue;
                String cellValue2;
                /* 每一个sheet页的第一行 */
                if (j == 0) {
                    firstValue = row.getCell(line).toString();
                    firstValue2 = row.getCell(lineTwo).toString();
                    startRow = j;
                    continue;
                }
                cellValue = row.getCell(line).toString();
                cellValue2 = row.getCell(lineTwo).toString();
                if (cellValue.equals(firstValue)) {
                    if (j == sheet.getPhysicalNumberOfRows() - 1) {
                        row = sheet.getRow(startRow);
                        CellRangeAddress cel = new CellRangeAddress(startRow, j, line, line);
                        sheet.addMergedRegion(cel);// 指定合并区域
                        createCenterCell(row, line, firstValue);

                        CellRangeAddress cel2 = new CellRangeAddress(startRow, j, lineTwo, lineTwo);
                        sheet.addMergedRegion(cel2);// 指定合并区域
                        createCenterCell(row, lineTwo, firstValue2);
                    }
                    continue;
                } else {
                    if (startRow != j - 1) {
                        row = sheet.getRow(startRow);
                        CellRangeAddress cel = new CellRangeAddress(startRow, j - 1, line, line);
                        sheet.addMergedRegion(cel);// 指定合并区域
                        createCenterCell(row, line, firstValue);

                        CellRangeAddress cel2 = new CellRangeAddress(startRow, j - 1, lineTwo, lineTwo);
                        sheet.addMergedRegion(cel2);// 指定合并区域
                        createCenterCell(row, lineTwo, firstValue2);
                    }
                    startRow = j;
                    firstValue = cellValue;
                    firstValue2 = cellValue2;
                }
            }
        }
    }

    /**
     * 指定生成一个value居中的单元格
     *
     * @param row
     * @param line
     * @param firstValue
     */
    private void createCenterCell(HSSFRow row, Integer line, String firstValue) {
        Cell cell = row.createCell(line);
        cell.setCellValue(firstValue);
        HSSFCellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 平行居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        cell.setCellStyle(cellStyle);
    }

}
