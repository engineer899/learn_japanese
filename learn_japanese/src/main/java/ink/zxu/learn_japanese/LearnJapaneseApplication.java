package ink.zxu.learn_japanese;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = "ink.zxu.learn_japanese.dao")
public class LearnJapaneseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnJapaneseApplication.class, args);
    }


}
