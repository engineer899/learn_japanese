package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.Page;
import ink.zxu.learn_japanese.utils.PageData;
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
        return (List<PageData>) dao.findForList("wordMapper.queryWordTitle", pageData);
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
