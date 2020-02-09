package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.config.UploadConfigure;
import ink.zxu.learn_japanese.dao.ExcelImportDao;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.annotation.Resource;
import java.io.InputStream;


/**
 * @author zhangwei
 * @date 2020/2/6 15:24
 */
@Service
@Transactional
public class ExcelImportService {
    @Autowired
    private ExcelImportDao excelImportDao;

    @Resource
    private UploadConfigure uploadConfigure;


    //获取配置文件中对应属性值

    private String keys="japanese,chinese,kana,tone,attribute,voice_url,word_id,chapter_id";

    public String ExcelImport(MultipartFile file, String chapter_id) throws Exception {
        String filename = file.getOriginalFilename();
        if (!filename.matches("^.+\\.(?i)(xls)$") && !filename.matches("^.+\\.(?i)(xlsx)$")) {
            return "File format error";
        }

        //判断Excel文件的版本
        boolean isExcel2003 = true;
        if (filename.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }


        //获取Excel文件的第一页sheet，判断是否有信息
        Sheet sheet = wb.getSheetAt(0);
        if (sheet == null) {
            return "excel is null";
        }

        //创建两个List集合，一个保存属性文件中的字段值，一个保存Excel文件中每一行的单元格的值

        //遍历Excel文件
        int totalRows = sheet.getPhysicalNumberOfRows();    //获取行数，第一行是标题
        Row row = null;
        //遍历每一行的数据
        System.out.println("总行数"+totalRows);
        for (int i = 1; i < totalRows; i++) {
            String values ="";
            //遍历单元格
            row = sheet.getRow(i);
            //循环设置每个单元格的数据类型为String 并处理日期类型
            for (int j = 0; j < 6; j++) {
                if(row.getCell(j)!=null){
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                }
                //判断单元格类型是否为空
                if (row.getCell(j, row.RETURN_BLANK_AS_NULL) == null ) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue("");
                }
                String cellInfo = row.getCell(j).getStringCellValue();
                System.out.println(cellInfo);
                if(j==5){
                    cellInfo= uploadConfigure.getServerPrefix()+"/voice/"+cellInfo;
                }
                values=values+"'"+cellInfo+"',";
            }
            values=values+"'"+ UUIDUtil.getUid() +"',"+"'"+chapter_id+"'";
            //每循环一行，调用数据访问层的方法,导入一行数据到数据库
            try{
                System.out.println("插入");
                excelImportDao.addWords(keys, values);
            }catch (Exception e){
            }

        }
        return "Successful import";
    }
}




























































