package com.zpq.scheduling;

import com.zpq.utils.DateUtils;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.*;

// 将excel中的数据记录到list集合中
public class ExcelToList {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\zpq\\Desktop\\day1.xls");
        List excelList = readExcel();
        System.out.println("===========list中数据打印结果=============");
        for (int i = 0; i < excelList.size(); i++) {
            List list = (List) excelList.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + "  ");
            }
            System.out.println();
        }
    }


    // 优化读取excel文件信息
    // 0 1 2 存储 门店id 日期 时间 后面存储客流量
    public static List readExcel() {
        File file = new File("C:\\Users\\zpq\\Desktop\\day1.xls");
        try {
            // 创建输入流 读取excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxt提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel 的页签数量
            int sheet_size = wb.getNumberOfSheets();
            List<List> outerList = new ArrayList<>();
            for (int index = 0; index < sheet_size; index++) {
                // 每一页创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                List innerList = new ArrayList();   // 存储一天的信息
                // 第1行第0列即为门店id
                innerList.add(Integer.parseInt(sheet.getCell(0, 1).getContents()));
                // 第1行第1列即为日期
                innerList.add(DateUtils.strToDate(sheet.getCell(1, 1).getContents()));
                // 第1行第2列即为营业时间(开店和关店时间相同)
                innerList.add(DateUtils.strToTime(sheet.getCell(2, 1).getContents()));
                // sheet.getRows() 获取本页的总行数         从第2行开始读取
                for (int i = 1; i < sheet.getRows(); i++) {     // 读取每半个小时的客流量
                    // 每行的第4列即为人流量
                    String cellInfo = sheet.getCell(4, i).getContents();       // getCell(x,y) 第y行的第x列
                    innerList.add(cellInfo);
                }
                outerList.add(innerList);
                System.out.println("第" + (index + 1) + "张表已完成");
            }
            return outerList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (BiffException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 读取第day天的数据
    public static List getDayPredication(int day, int storeId) {
        String fileName = "src/main/resources/Store"+storeId+".xls";
        File file = new File(fileName);
        try {
            // 创建输入流 读取excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxt提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel 的页签数量
            // 每一页创建一个Sheet对象
            Sheet sheet = wb.getSheet(day);
            List innerList = new ArrayList();   // 存储一天的信息
            // 第1行第0列即为门店id
            innerList.add(Integer.parseInt(sheet.getCell(0, 1).getContents()));
            // 第1行第1列即为日期
            innerList.add(DateUtils.strToDate(sheet.getCell(1, 1).getContents()));
            // 第1行第2列即为营业时间(开店和关店时间相同)
            innerList.add(DateUtils.strToTime(sheet.getCell(2, 1).getContents()));
            // sheet.getRows() 获取本页的总行数         从第2行开始读取
            for (int i = 1; i < sheet.getRows(); i++) {     // 读取每半个小时的客流量
                // 每行的第4列即为人流量
                String cellInfo = sheet.getCell(4, i).getContents();       // getCell(x,y) 第y行的第x列
                innerList.add(cellInfo);
            }
            return innerList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (BiffException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
