package com.blogger.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogger.entity.Article;
import com.blogger.entity.ArticleEntity.Article;
import com.blogger.server.ArticleService;
import com.blogger.server.ArticleService.ArticleService;
import com.blogger.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.blogger.util.ExportWord;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 文章台账
    @PostMapping("getArticleList")
    public Result getArticleList(@RequestBody String data) {
        JSONObject obj = JSONObject.parseObject(data);
        Page<Article> page = new Pagegit<>();
//        设置当前第几页
        page.setCurrent(1);
//        设置每页显示条数
        page.setSize(2);
//        把page传到方法内进行分页
//        List<User> users = userDao.selectAllUser(page);
        List<Article> articleList = articleService.getArticleList();
//        获取总共分了几页
        page.getPages();
//        获取总条数
        page.getTotal();
//        for (User user : users) {
//            System.out.println(user);
//        }

        Page pageBean = new Page<Tenant>(pageNum, pageSize);
        List<User> users = userMapper.selectPage(pageBean);
        pageBean.setRecords(users);

        Page<Article> articleList = articleService.getArticleList(pageNum, pageSize);

//        List<Article> articleList = articleService.getArticleList();
        return Result.success(articleList);
    }

    // 文章保存
    @PostMapping("saveArticle")
    public String saveArticle(@RequestBody String data) {
        JSONObject obj = JSONObject.parseObject(data);
        Article article = JSONObject.parseObject(data, Article.class);

        boolean succ = articleService.saveArticle(article);
        if (succ) {
            return "保存成功";
        } else {
            return "保存失败";
        }

    }

//    // 文章删除 批量删除
//    @PostMapping("deleteArticleAll")
//    public String  deleteArticleAll(@RequestBody String data) {
//        String oper = "deleteArticleAll";
//        JSONObject obj = JSONObject.parseObject(data);
//        JSONArray idList = (JSONArray) obj.get("idList");
//        boolean succ = schedulingMgmtService.deleteScheduling(idList);
//        if (succ) {
//            return  "删除成功";
//        } else {
//            return  "删除失败";
//        }
//    }

    // 文章删除 单独删除
    @GetMapping("deleteArticle/{articleId}")
    public String deleteArticle(@PathVariable("articleId") int articleId) {
        int succ = articleService.deleteArticle(articleId);
        if (succ == 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }


    // 文章详情
    @GetMapping("detailsArticle/{articleId}")
    public Article detailsArticle(@PathVariable("articleId") int articleId) {

        Article article = articleService.detailsArticle(articleId);
        return article;
    }

    // 导出文章Word文件 /// throws ParseException
    @GetMapping("exportWrod/{articleId}")
    public void exportWrod(HttpServletRequest request, HttpServletResponse response, @PathVariable("articleId") int articleId) {


        List<Article> articleList = articleService.getArticleList();
        Article article = articleService.detailsArticle(articleId);
        Map<String, Object> params = new HashMap<>();
        params.put("ArticleName", article.getArticleName());
        // 工具类生成word文档（本地）
        ExportWord.exportWord("C:/Users/DELL/Desktop/exportWord.docx", "C:/Users/DELL/Desktop",
                article.getArticleName() + ".docx", params, request, response, article,articleList);

        // 工具类生成word文档（服务器）   高明有轨电车XX月XX日运营日报
//        WorderToNewWordUtils.exportWord("/root/operateWord.docx", "/root", operationalProjectOne.getOperateName() + ".docx",
//                params, request, response, operationalProjectTimetables, operationalProjectDelayList, operationalProjectVehicleList, operationalProjectEventList, operationalProjectFaultList);
    }
}
