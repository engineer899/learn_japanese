package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.dao.DaoSupport;
import ink.zxu.learn_japanese.utils.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 张伟
 * @date 2019/9/22 12:48
 */
@Service
public class CommunicationService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public List<PageData> showDongtai() throws Exception {
        List<PageData> pageDataList=null;
        ArrayList list=null;
        pageDataList=(List<PageData>)dao.findForList("communicationMapper.showContent");
        for(PageData pageData:pageDataList){
            list=(ArrayList<String>) dao.findForList("communicationMapper.showImage",pageData);
            pageData.put("list",list);
        }
        return pageDataList;
    }

    public List<PageData> showComment(PageData pageData) throws Exception {
        List<PageData> pageDataList=null;
        List<PageData> pageDataList1=null;
        pageDataList=(List<PageData>)dao.findForList("communicationMapper.showComment",pageData);
        for(PageData pageData1:pageDataList){
            pageDataList1=(List<PageData>)dao.findForList("communicationMapper.showReply",pageData1);
//            for(PageData pageData2:pageDataList1){
//                if(pageData2.getString("hfid")!=null){
//                    List<String> strings=(List<String>)dao.findForList("communicationMapper.completeComment",pageData2);
//                }
//            }
            pageData1.put("list",pageDataList1);
        }
        return pageDataList;
    }

    public int addContent(PageData pageData) throws Exception {
        return (int)dao.save("communicationMapper.addContent",pageData);
    }

    public int addImage(PageData pageData) throws Exception {
        return (int)dao.save("communicationMapper.addImage",pageData);
    }

    public int addComment(PageData pageData) throws Exception {
        return (int)dao.save("communicationMapper.addComment",pageData);
    }



}
