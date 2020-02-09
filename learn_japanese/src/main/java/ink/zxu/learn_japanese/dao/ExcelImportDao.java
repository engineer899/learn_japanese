package ink.zxu.learn_japanese.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author zw
 * @date 2019/9/30 9:36
 */

@Repository
public class ExcelImportDao {

    @Autowired
    private JdbcTemplate jdbctemplate;

    //添加数据
    @Transactional
    public void addWords(String keys,String values) {
        System.out.println("开始插入");
        StringBuilder sql = new StringBuilder();
        try{
            sql.append("insert into w_word ").append("(").append(keys).append(")").append(" values (").append(values).append(")");
            System.out.println(sql.toString());
            jdbctemplate.execute(sql.toString());
        }catch(Exception e ) {
            e.printStackTrace();
            System.out.println("插入失败,回滚事务!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        System.out.println("完成插入");
    }
}
