package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.Page;
import ink.zxu.learn_japanese.utils.PageData;
import ink.zxu.learn_japanese.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * 微课视频分页查询
     * @param page
     * @return
     * @throws Exception
     */
    public List<PageData> queryWordInfoListPage(Page page) throws Exception {
        return (List<PageData>) dao.findForList("wordMapper.queryWordInfoListPage", page);
    }


    /**
     * 微课视频分页查询
     * @param
     * @return
     * @throws Exception
     */
    public List<PageData> queryWordTitle(PageData pageData) throws Exception {
        PageData result;
        List<PageData> pageDataList=(List<PageData>) dao.findForList("wordMapper.queryWordTitle", pageData);
        for(int i=0;i<pageDataList.size();i++){
            String word_title=pageDataList.get(i).getString("word_title");
            pageData.put("word_title",word_title);
            result=(PageData) dao.findForObject("wordMapper.queryWordTestIsEnd",pageData);
            if(result==null){
                pageDataList.get(i).put("is_end","0");
                pageDataList.get(i).put("record_id","null");
            }else{
                String is_end=result.getString("is_end");
                String record_id=result.getString("record_id");
                if(is_end.equals("0")){
                    pageDataList.get(i).put("is_end",0);
                    pageDataList.get(i).put("record_id",record_id);
                }else {
                    pageDataList.get(i).put("is_end",1);
                    pageDataList.get(i).put("record_id",record_id);
                }
            }
        }
        return pageDataList;
    }


    /**
     * 微课视频分页查询
     * @param
     * @return
     * @throws Exception
     */
    public  int deleteWordById(PageData pageData) throws Exception {
        int tb_word=0;
        int tb_voice=0;
        int tb_word_eg=0;
        int tb_analy=0;
        try{
            tb_word=(int)dao.delete("wordMapper.deleteWord",pageData);
            tb_voice=(int)dao.delete("wordMapper.deleteVoiceByWordId",pageData);
            tb_word_eg=(int)dao.delete("wordMapper.deleteSentenceByWordId",pageData);
            tb_analy=(int)dao.delete("wordMapper.deleteAnalyByWordId",pageData);
        }catch(Exception e){
            return 1;
        }
        if(tb_word+tb_voice+tb_word_eg+tb_analy>=4){
            return 0;
        }else{
            return 1;
        }
    }



    public List<PageData> clickWordTest(PageData pageData) throws Exception {
        int flag=0;
        List<PageData> pageDataList=null;
        if(pageData.getString("record_id").equals("null")){
            pageData.put("record_id",UUIDUtil.getUid());
            flag=(int)dao.save("wordMapper.addWordTest",pageData);
        }else{
            dao.findForList("wordMapper.queryWordRecord");
        }
        Page page=new Page();
        page.setPd(pageData);
        pageDataList=(List<PageData>)dao.findForList("queryWordInfoListPage",page);
        for(int i=0;i<pageDataList.size();i++){
            List<PageData> select=(List<PageData>)dao.findForList("queryAnalyRand",pageDataList.get(i));
            pageDataList.get(i).put("select",select);
        }
        return pageDataList;
    }


    public int addWord(PageData pageData) {
        int tb_word=0;
        int tb_voice=0;
        int tb_word_eg=0;
        int tb_analy=0;
        try{
             tb_word=(int)dao.save("wordMapper.addWord",pageData);
             tb_voice=(int)dao.save("wordMapper.addVoice",pageData);
             tb_word_eg=(int)dao.save("wordMapper.addSentence",pageData);
             tb_analy=(int)dao.save("wordMapper.addAnaly",pageData);
        }catch(Exception e){
            try{
                if(tb_word<1){
                    dao.delete("wordMapper.deleteWord",pageData);
                }
                if(tb_voice<1){
                    dao.delete("wordMapper.deleteVoice",pageData);
                }
                if(tb_word_eg<1){
                    dao.delete("wordMapper.deleteSentence",pageData);
                }
                if(tb_analy<1){
                    dao.delete("wordMapper.deleteAnaly",pageData);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return 1;
        }
        if(tb_word+tb_voice+tb_word_eg+tb_analy>=4){
            return 0;
        }else{
            try{
                if(tb_word<1){
                    dao.delete("wordMapper.deleteWord",pageData);
                }
                if(tb_voice<1){
                    dao.delete("wordMapper.deleteVoice",pageData);
                }
                if(tb_word_eg<1){
                    dao.delete("wordMapper.deleteSentence",pageData);
                }
                if(tb_analy<1){
                    dao.delete("wordMapper.deleteAnaly",pageData);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return 1;
    }
}
