package ink.zxu.learn_japanese.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.Page;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangwei
 * @date 2019/12/1 18:32
 */
@Service
public class WordService {
    @Resource(name="daoSupport")
    private DaoSupport dao;




    /**
     * 课程类别查询
     * @return
     * @throws Exception
     */
    public List<PageData> queryCourseType(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("wordMapper.queryCourseType", pageData);
    }

    /**
     * 日语单词列表查询
     * @return
     * @throws Exception
     */
    public List<PageData> queryWordListPage(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("wordMapper.queryWordListPage", pageData);
    }

    /**
     * 日语单词总数
     * @return
     * @throws Exception
     */
    public PageData queryWordCount(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("wordMapper.queryWordCount", pageData);
    }



    /**
     * 查询章节是否存在
     * @return
     * @throws Exception
     */
    public List<PageData>  queryChapterByName(PageData pageData) throws Exception {
        return (List<PageData> ) dao.findForList("wordMapper.queryChapterByName", pageData);
    }

    /**
     * 章节列表
     * @return
     * @throws Exception
     */
    public List<PageData>  queryChapterListPage(PageData pageData) throws Exception {
        return (List<PageData> ) dao.findForList("wordMapper.queryChapterListPage", pageData);
    }

    /**
     * 章节总数
     * @return
     * @throws Exception
     */
    public PageData queryChapterCount(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("wordMapper.queryChapterCount", pageData);
    }


    /**
     * 添加章节
     * @return
     * @throws Exception
     */
    public Integer addChapter(PageData pageData) throws Exception {
        return  (Integer) dao.save("wordMapper.addChapter",pageData);
    }



    /**
     * 课程总数
     * @return
     * @throws Exception
     */
    public PageData queryCourseCount(PageData pageData) throws Exception {
        return (PageData) dao.findForObject("wordMapper.queryCourseCount", pageData);
    }


    /**
     * 添加课程
     * @return
     * @throws Exception
     */
    public Integer addCourse(PageData pageData) throws Exception {
        return  (Integer) dao.save("wordMapper.addCourse",pageData);
    }


    /**
     * 删除课程
     * @return
     * @throws Exception
     */
    public Integer deleteCourse(PageData pageData) throws Exception {
        return  (Integer) dao.update("wordMapper.deleteCourse",pageData);
    }





    /**
     * 课程列表查询
     * @return
     * @throws Exception
     */
    public List<PageData> queryCourseListPage(PageData pageData) throws Exception {
        return (List<PageData>) dao.findForList("wordMapper.queryCourseListPage", pageData);
    }




    /**
     * 日语单词库
     * @param
     * @return
     * @throws Exception
     */
    public List<PageData> queryWordTitle(PageData pageData) throws Exception {
        PageData pd;
        List<PageData> pageDataList=(List<PageData>) dao.findForList("wordMapper.queryWordTitle", pageData);
        for(int i=0;i<pageDataList.size();i++){//遍历单词库名
            String chapter_id=pageDataList.get(i).getString("chapter_id");
            pageData.put("chapter_id",chapter_id);
            //    查询单词测试列表 当前章节是否达标
            pd=(PageData) dao.findForObject("wordMapper.queryWordTestIsEnd",pageData);
            pageDataList.get(i).put("count",pd.get("count"));//达标次数
        }
        return pageDataList;
    }



    /**
     * 增加答题记录
     * @param
     * @return
     * @throws Exception
     */

    public PageData addWordRecord(PageData pageData) throws Exception{
        String record_id=UUIDUtil.getUid();
        pageData.put("record_id",record_id);
//        Map<String,Object> resultMap=new HashMap<>();
        dao.save("wordMapper.addWordTest",pageData);//新建一条测试记录
        JsonParser parser = new JsonParser();
        JsonArray Jarray = parser.parse(pageData.getString("result")).getAsJsonArray();
        for(JsonElement jsonObject:Jarray){
            String word_id=jsonObject.getAsJsonObject().get("word_id").getAsString();
            pageData.put("word_id",word_id);
            if(jsonObject.getAsJsonObject().get("flag").getAsString().equals("1")){
                pageData.put("true_num",1);
                pageData.put("false_num",0);
            }else{
                pageData.put("true_num",0);
                pageData.put("false_num",1);
            }
            dao.save("wordMapper.addWordRecord", pageData);
        }
        if(pageData.getString("true_rate").equals("100")){
            pageData.put("is_end",1);
        }else{
            pageData.put("is_end",0);
        }
        dao.update("wordMapper.updateWordTest",pageData);//新建一条测试记录
        System.out.println(pageData);
        return pageData;
    }






    /**
     * 删除单词
     * @param
     * @return
     * @throws Exception
     */
    public  int deleteWordById(PageData pageData) throws Exception {
        Integer tb_word=0;
        Integer tb_voice=0;
        Integer tb_word_eg=0;
        Integer tb_analy=0;
        try{
            tb_word=(Integer)dao.delete("wordMapper.deleteWord",pageData);
            tb_voice=(Integer)dao.delete("wordMapper.deleteVoiceByWordId",pageData);
            tb_word_eg=(Integer)dao.delete("wordMapper.deleteSentenceByWordId",pageData);
            tb_analy=(Integer)dao.delete("wordMapper.deleteAnalyByWordId",pageData);
        }catch(Exception e){
            return 1;
        }
        if(tb_word+tb_voice+tb_word_eg+tb_analy>=4){
            return 0;
        }else{
            return 1;
        }
    }


    public Map<String, Object> clickWordTest(PageData pageData) throws Exception {
        Map<String,Object> resultMap=new HashMap<>();//结果集
        int randnum=10;//设置每次随机数量
        pageData.put("randnum",randnum);
        List<PageData>  testDataList=(List<PageData>)dao.findForList("queryWordRandom",pageData);//从单词库随机取出10个单词
        int hf_size=testDataList.size()/2;
        System.out.println("测试"+hf_size);
        pageData.put("record_id",UUIDUtil.getUid());
        for(int i=0;i<testDataList.size();i++){
            if(i<hf_size){ //根据汉字选读音
                String chinese=testDataList.get(i).getString("chinese");
                String kana=testDataList.get(i).getString("kana");
                String attribute=testDataList.get(i).getString("attribute");
                testDataList.get(i).put("kana",chinese);
                testDataList.get(i).put("chinese",kana);
                testDataList.get(i).put("attribute",attribute);
                List<PageData> select=(List<PageData>)dao.findForList("queryKanaRand",testDataList.get(i));//在单词表随机找出三个读音做干扰项
                testDataList.get(i).put("select",select);//将干扰项插入单词对象中
            }else{//根据读音选汉字
                List<PageData> select=(List<PageData>)dao.findForList("queryAnalyRand",testDataList.get(i));//在单词解析表随机找出三个中文做干扰项
                testDataList.get(i).put("select",select);//将干扰项插入单词对象中
            }
        }
        resultMap.put("testDataList",testDataList);
        return resultMap;
    }



    public int addWord(PageData pageData) throws Exception {
        return (int)dao.save("wordMapper.addWord",pageData);
    }


//    /**
//     * 日语单词列表查询
//     * @return
//     * @throws Exception
//     */
//    public List<PageData> queryWordInfoListPage(PageData pageData) throws Exception {
//        return (List<PageData>) dao.findForList("wordMapper.queryWordInfoListPage", pageData);
//    }

//    public List<PageData> queryWordTitle(PageData pageData) throws Exception {
//        PageData result;
//        List<PageData> pageDataList=(List<PageData>) dao.findForList("wordMapper.queryWordTitle", pageData);
//        for(int i=0;i<pageDataList.size();i++){//遍历单词库名
//            String word_title=pageDataList.get(i).getString("word_title");
//            pageData.put("word_title",word_title);
//            //    <!--查询单词测试列表 最近一次记录是否结束 记录表id-->
//            result=(PageData) dao.findForObject("wordMapper.queryWordTestIsEnd",pageData);
//            if(result==null){//从未答过题
//                pageDataList.get(i).put("is_end","0");//标记为未结束
//                pageDataList.get(i).put("record_id","null");//最近一次单词练习记录为空
//            }else{
//                //有答题记录
//                String is_end=result.getString("is_end");
//                String record_id=result.getString("record_id");
//                if(is_end.equals("0")){//判断是否为未结束
//                    pageDataList.get(i).put("is_end","0");//标记为未结束
//                    pageDataList.get(i).put("record_id",record_id);//获取最近一次答题记录id
//                }else {
//                    pageDataList.get(i).put("is_end","1");//标记为已经结束
//                    pageDataList.get(i).put("record_id",record_id);//获取最近一次答题记录id
//                }
//            }
//        }
//        return pageDataList;
//    }


//    public Map<String, Object> clickWordTest(PageData pageData) throws Exception {
//        Integer flag=0;
//        Integer index=0;
//        Double true_num=0.0;
//        Double false_num=0.0;
//        DecimalFormat df = new DecimalFormat("0.0");//设置小数点位数
//        Double true_rate=0.0;
//        Map<String,Object> resultMap=new HashMap<>();
//        Page page=new Page();
//        page.setPd(pageData);
//        List<PageData>  testDataList=(List<PageData>)dao.findForList("queryWordInfoListPage",page);//单词库单词列表
//        if(pageData.getString("record_id").equals("null")){ //如果测试表记录为空 即为第一次答题 则创建一条答题记录
//            pageData.put("record_id",UUIDUtil.getUid());
//            flag=(Integer)dao.save("wordMapper.addWordTest",pageData);//新建一条测试记录
//            for(int i=0;i<testDataList.size();i++){
//                List<PageData> select=(List<PageData>)dao.findForList("queryAnalyRand",testDataList.get(i));//在单词解析表随机找出三个中文做干扰项
//                testDataList.get(i).put("select",select);//将干扰项插入单词对象中
//            }
//        }else{
//            List<PageData>  recordDataListId=(List<PageData>)dao.findForList("queryWordRecord",pageData);//已答题库单词列表
//            for(int i=0;i<testDataList.size();i++){
//                for(int j=0;j<recordDataListId.size();j++){
//                    if(testDataList.get(i).getString("word_id").equals(recordDataListId.get(j).getString("word_id"))){//找到单词库中已经被答的单词
//                        true_num=true_num+(Integer)recordDataListId.get(j).get("true_num");//已答单词的正确次数
//                        false_num=false_num+(Integer)recordDataListId.get(j).get("false_num");//已答单词的错误次数
//                        index++;//上次退出时 单词表单词位置
//                    }
//                }
//                List<PageData> select=(List<PageData>)dao.findForList("queryAnalyRand",testDataList.get(i));//在单词解析表随机找出三个中文做干扰项
//                testDataList.get(i).put("select",select);//将干扰项插入单词对象中
//            }
//            if((true_num+false_num)==0 || true_num==0){
//                true_rate=0.0;
//            }else{
//                true_rate=true_num/(true_num+false_num)*100;
//            }
//        }
//
//        resultMap.put("had_learn",index);
//        resultMap.put("not_learn",testDataList.size()-index);
//        resultMap.put("true_rate",df.format(true_rate));
//        resultMap.put("index",index);
//        resultMap.put("testDataList",testDataList);
//        return resultMap;
//    }



//    public int addWord(PageData pageData) {
//        int tb_word=0;
//        int tb_voice=0;
//        int tb_word_eg=0;
//        int tb_analy=0;
//        try{
//             tb_word=(int)dao.save("wordMapper.addWord",pageData);
//             tb_voice=(int)dao.save("wordMapper.addVoice",pageData);
//             tb_word_eg=(int)dao.save("wordMapper.addSentence",pageData);
//             tb_analy=(int)dao.save("wordMapper.addAnaly",pageData);
//        }catch(Exception e){
//            try{
//                if(tb_word<1){
//                    dao.delete("wordMapper.deleteWord",pageData);
//                }
//                if(tb_voice<1){
//                    dao.delete("wordMapper.deleteVoice",pageData);
//                }
//                if(tb_word_eg<1){
//                    dao.delete("wordMapper.deleteSentence",pageData);
//                }
//                if(tb_analy<1){
//                    dao.delete("wordMapper.deleteAnaly",pageData);
//                }
//            }catch(Exception ex){
//                ex.printStackTrace();
//            }
//            return 1;
//        }
//        if(tb_word+tb_voice+tb_word_eg+tb_analy>=4){
//            return 0;
//        }else{
//            try{
//                if(tb_word<1){
//                    dao.delete("wordMapper.deleteWord",pageData);
//                }
//                if(tb_voice<1){
//                    dao.delete("wordMapper.deleteVoice",pageData);
//                }
//                if(tb_word_eg<1){
//                    dao.delete("wordMapper.deleteSentence",pageData);
//                }
//                if(tb_analy<1){
//                    dao.delete("wordMapper.deleteAnaly",pageData);
//                }
//            }catch(Exception ex){
//                ex.printStackTrace();
//            }
//        }
//        return 1;
//    }


    //    public PageData addAnswerRecord(PageData pageData) throws Exception {
//        PageData ResultMap=new PageData();
//        Integer had_learn=0;
//        Integer not_learn=0;
//        Double  true_num=0.0;
//        Double  false_num=0.0;
//        DecimalFormat df = new DecimalFormat("0.00");//设置小数点位数
//        String flag=pageData.getString("flag");
//        if(Integer.parseInt(String.valueOf(((PageData)dao.findForObject("wordMapper.queryWordRecordById", pageData)).get("count")))>0){
//            pageData.put("flag",flag);
//            Integer updateAnswer=(Integer) dao.update("wordMapper.updateAnswerWord", pageData);
//        }else{
//            if(flag.equals("1")){//回答正确
//                pageData.put("true_num",1);
//                pageData.put("false_num",0);
//            }else if(flag.equals("0")){//回答错误
//                pageData.put("true_num",0);
//                pageData.put("false_num",1);
//            }
//            Integer addAnswer=(Integer) dao.save("wordMapper.addAnswerWord", pageData);
//        }
//        PageData WordCount=(PageData) dao.findForObject("wordMapper.queryWordCount", pageData);
//        PageData RecordCount=(PageData) dao.findForObject("wordMapper.queryWordRecordCount", pageData);
//        if(String.valueOf(WordCount.get("count")).equals(String.valueOf(RecordCount.get("count")))){
//            had_learn=Integer.parseInt(String.valueOf(RecordCount.get("count")));
//            not_learn=Integer.parseInt(String.valueOf(WordCount.get("count")))-had_learn;
//            Long nowtime=System.currentTimeMillis();
//            Date d=new Date(nowtime);
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String  end_time=sdf.format(d);
//            pageData.put("end_time",end_time);
//            Integer is_end=(Integer) dao.update("wordMapper.updateAnswerWord", pageData);
//        }else{
//            had_learn=Integer.parseInt(String.valueOf(RecordCount.get("count")));
//            not_learn=Integer.parseInt(String.valueOf(WordCount.get("count")))-had_learn;
//        }
//        List<PageData>  recordDataListId=(List<PageData>)dao.findForList("queryWordRecord",pageData);//已答题库单词列表
//        for(int i=0;i<recordDataListId.size();i++){
//            true_num=true_num+(Integer)recordDataListId.get(i).get("true_num");//已答单词的正确次数
//            false_num=false_num+(Integer)recordDataListId.get(i).get("false_num");//已答单词的错误次数
//        }
//        ResultMap.put("had_learn",had_learn);
//        ResultMap.put("not_learn",not_learn);
//        ResultMap.put("true_rate",df.format(true_num/(true_num+false_num)*100));
//        return ResultMap;
//    }


}
