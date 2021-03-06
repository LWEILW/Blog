package com.blogger.util;//package com.blogger.util;
//
//
//import com.blogger.entity.ArticleEntity.Article;
//import org.springframework.util.Assert;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.util.List;
//import java.util.Map;
//
//public class ExportWord {
//    /**
//     * 导出word
//     * <p>第一步生成替换后的word文件，只支持docx</p>
//     * <p>第二步下载生成的文件</p>
//     * <p>第三步删除生成的临时文件</p>
//     * 模版变量中变量格式：{{foo}}
//     *
//     * @param templatePath word模板地址
//     * @param temDir       生成临时文件存放地址
//     * @param fileName     文件名
//     * @param params       替换的参数
//     * @param request      HttpServletRequest
//     * @param response     HttpServletResponse
//     */
//    public static void exportWord(String templatePath, String temDir, String fileName, Map<String, Object> params,
//                                  HttpServletRequest request, HttpServletResponse response, Article article,List<Article> articleList) {
//
//        Assert.notNull(templatePath, "模板路径不能为空");
//        Assert.notNull(temDir, "临时文件路径不能为空");
//        Assert.notNull(fileName, "导出文件名不能为空");
//        Assert.isTrue(fileName.endsWith(".docx"), "word导出请使用docx格式");
//        if (!temDir.endsWith("/")) {
//            temDir = temDir + File.separator;
//        }
//        File dir = new File(temDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        try {
//            String userAgent = request.getHeader("user-agent").toLowerCase();
//            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
//                fileName = URLEncoder.encode(fileName, "UTF-8");
//            } else {
//                fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
//            }
//            XWPFDocument doc = WordExportUtil.exportWord07(templatePath, params);
//
//            // 获取word文档信息
//            List<XWPFTable> tableList = doc.getTables();
//            // 获取第一张表格
//            XWPFTable table = tableList.get(0);
//
//            // 时刻表情况
//            int rowTimetableBefore = 13;
//            int rowTimetableLater = 13 + articleList.size() - 1;
//            // 表格的插入行, 第一个参数是新增行的样式
//            XWPFTableRow rowTimetableStyle = table.getRow(rowTimetableBefore);
//            // 插入模板行
//            int numTimetable = 0;
//            for (int h = rowTimetableBefore; h <= rowTimetableLater; h++) {
//                copy(table, rowTimetableStyle, h);
//                List<XWPFTableCell> tmpCellsDateTimetable = table.getRow(h).getTableCells();
//
//                tmpCellsDateTimetable.get(0).setText(articleList.get(numTimetable).getArticleContent());
//                tmpCellsDateTimetable.get(1).setText(articleList.get(numTimetable).getArticleContent() + "");
//                tmpCellsDateTimetable.get(2).setText(articleList.get(numTimetable).getArticleContent() + "");
//                tmpCellsDateTimetable.get(3).setText(articleList.get(numTimetable).getArticleContent() + "");
//                tmpCellsDateTimetable.get(4).setText(articleList.get(numTimetable).getArticleContent() + "");
//
//                numTimetable++;
//            }
//
//
//            // 列车晚点统计
//            int rowDelayBefore = rowTimetableLater + 15;
//            int rowDelayLater = rowDelayBefore + articleList.size() - 1;
//            // 表格的插入行, 第一个参数是新增行的样式
//            XWPFTableRow rowDelayStyle = table.getRow(rowDelayBefore);
//            // 插入模板行
//            int numDelay = 0;
//            for (int i = rowDelayBefore; i <= rowDelayLater; i++) {
//                copy(table, rowDelayStyle, i);
//                List<XWPFTableCell> tmpCellsDateDelay = table.getRow(i).getTableCells();
//                tmpCellsDateDelay.get(0).setText(numDelay + 1 + "");
//                tmpCellsDateDelay.get(1).setText(articleList.get(numDelay).getArticleContent());
//                tmpCellsDateDelay.get(2).setText(articleList.get(numDelay).getArticleContent());
//                tmpCellsDateDelay.get(3).setText(articleList.get(numDelay).getArticleContent());
//                tmpCellsDateDelay.get(4).setText(articleList.get(numDelay).getArticleContent());
//                tmpCellsDateDelay.get(5).setText(articleList.get(numDelay).getArticleContent());
//                tmpCellsDateDelay.get(6).setText(articleList.get(numDelay).getArticleContent());
//                tmpCellsDateDelay.get(7).setText(articleList.get(numDelay).getArticleContent());
//                tmpCellsDateDelay.get(8).setText(articleList.get(numDelay).getArticleContent());
//
//                numDelay++;
//            }
//
//
//            // 开行调试列车/工程车情况
//            int rowVehicleBefore = rowDelayLater + 4;
//            int rowVehicleLater = rowVehicleBefore + articleList.size() - 1;
//            //表格的插入行, 新增行的样式
//            XWPFTableRow rowVehicleStyle = table.getRow(rowVehicleBefore);
//            // 插入模板行
//            int numVehicle = 0;
//            for (int j = rowVehicleBefore; j <= rowVehicleLater; j++) {
//                copy(table, rowVehicleStyle, j);
//                List<XWPFTableCell> tmpCellsDateVehicle = table.getRow(j).getTableCells();
//                // 插入数据
//                tmpCellsDateVehicle.get(0).setText(numVehicle + 1 + "");
//                tmpCellsDateVehicle.get(1).setText(articleList.get(numVehicle).getArticleContent());
//                tmpCellsDateVehicle.get(2).setText(articleList.get(numVehicle).getArticleContent());
//                tmpCellsDateVehicle.get(3).setText(articleList.get(numVehicle).getArticleContent());
//                tmpCellsDateVehicle.get(4).setText(articleList.get(numVehicle).getArticleContent());
//                tmpCellsDateVehicle.get(5).setText(articleList.get(numVehicle).getArticleContent());
//                numVehicle++;
//            }
//
//
//
//
//            // 删除模板行样式
//            if (articleList.size() > 0) {
//                table.removeRow(rowVehicleLater + 1);
//            }
//            if (articleList.size() > 0) {
//                table.removeRow(rowDelayLater + 1);
//            }
//            if (articleList.size() > 0) {
//                table.removeRow(rowTimetableLater + 1);
//            }
//
//            String tmpPath = temDir + fileName;
//            FileOutputStream fos = new FileOutputStream(tmpPath);
//            doc.write(fos);
//            // 设置强制下载不打开
//            response.setContentType("application/force-download");
//            // 设置文件名
//            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
//            OutputStream out = response.getOutputStream();
//            doc.write(out);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            deleteServerFile(temDir, fileName);//这一步看具体需求，要不要删
//        }
//
//    }
//
//    /**
//     * 删除服务上的文件
//     *
//     * @param filePath 路径
//     * @param fileName 文件名
//     * @return
//     * @author Master.Pan
//     * @date 2016年5月9日 上午1:06:48
//     */
//    public static boolean deleteServerFile(String filePath, String fileName) {
//        boolean delete_flag = false;
//        File file = new File(filePath + fileName);
//        if (file.exists() && file.isFile() && file.delete())
//            delete_flag = true;
//        else
//            delete_flag = false;
//        return delete_flag;
//    }
//
//
//    // 添加行,复制样式
//    private static void copy(XWPFTable table, XWPFTableRow sourceRow, int rowIndex) {
//        //在表格指定位置新增一行
//        XWPFTableRow targetRow = table.insertNewTableRow(rowIndex);
//        //复制行属性
//        targetRow.getCtRow().setTrPr(sourceRow.getCtRow().getTrPr());
//        List<XWPFTableCell> cellList = sourceRow.getTableCells();
//        if (null == cellList) {
//            return;
//        }
//        //复制列及其属性和内容
//        XWPFTableCell targetCell = null;
//        for (XWPFTableCell sourceCell : cellList) {
//            targetCell = targetRow.addNewTableCell();
//            //列属性
//            targetCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());
//            //段落属性
//            if (sourceCell.getParagraphs() != null && sourceCell.getParagraphs().size() > 0) {
//                targetCell.getParagraphs().get(0).getCTP().setPPr(sourceCell.getParagraphs().get(0).getCTP().getPPr());
//                if (sourceCell.getParagraphs().get(0).getRuns() != null && sourceCell.getParagraphs().get(0).getRuns().size() > 0) {
//                    XWPFRun cellR = targetCell.getParagraphs().get(0).createRun();
//                    cellR.setText(sourceCell.getText());
//                    cellR.setBold(sourceCell.getParagraphs().get(0).getRuns().get(0).isBold());
//                } else {
//                    targetCell.setText(sourceCell.getText());
//                }
//            } else {
//                targetCell.setText(sourceCell.getText());
//            }
//        }
//    }
//
//}
