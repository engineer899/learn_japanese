package ink.zxu.learn_japanese.config;

/**
 * @author 张伟
 * @date 2019/9/25 22:25
 */

//import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

//@Data
@Component
@ConfigurationProperties(prefix ="upload")
public class UploadConfigure {

    // 获取图片存放位置
    private Map<String, String> image_path;


    // 获取课程图片存放位置
    private Map<String, String> c_image_path;




    // 获取课程存放位置
    private Map<String, String> course_path;



    // 获取音频存放位置
    private Map<String, String> voice_path;




    // 获取视频存放位置
    private Map<String, String> video_path;

    // 单个文件大小
    private String maxFileSize;


    // 单次上传总文件大小
    private String maxRequestSize;

    private String serverPrefix;



    public String getServerPrefix() {
        return serverPrefix;
    }

    public void setServerPrefix(String serverPrefix) {
        this.serverPrefix = serverPrefix;
    }

    public Map<String, String> getC_image_path() {
        return c_image_path;
    }

    public void setC_image_path(Map<String, String> c_image_path) {
        this.c_image_path = c_image_path;
    }

    public Map<String, String> getVideo_path() {
        return video_path;
    }

    public void setVideo_path(Map<String, String> video_path) {
        this.video_path = video_path;
    }

    public Map<String, String> getImage_path() {
        return image_path;
    }

    public void setImage_path(Map<String, String> image_path) {
        this.image_path = image_path;
    }

    public Map<String, String> getCourse_path() {
        return course_path;
    }

    public void setVoice_path(Map<String, String> voice_path) {
        this.voice_path = voice_path;
    }


    public Map<String, String> getVoice_path() {
        return voice_path;
    }


    public void setCourse_path(Map<String, String> course_path) {
        this.course_path = course_path;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getMaxRequestSize() {
        return maxRequestSize;
    }

    public void setMaxRequestSize(String maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
    }



    public String getCourseImageBasePath() {
        String path;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")) {
            path = this.getC_image_path().get("windows");
        } else {
            path = this.getC_image_path().get("linux");
        }
        return path;
    }


    public String getImageBasePath() {
        String path;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")) {
            path = this.getImage_path().get("windows");
        } else {
            path = this.getImage_path().get("linux");
        }
        return path;
    }


    public String getCourseBasePath() {
        String path;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")) {
            path = this.getCourse_path().get("windows");
        } else {
            path = this.getCourse_path().get("linux");
        }
        return path;
    }

    public String getVideoBasePath() {
        String path;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")) {
            path = this.getVideo_path().get("windows");
        } else {
            path = this.getVideo_path().get("linux");
        }
        return path;
    }


    public String getVoiceBasePath() {
        String path;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")) {
            path = this.getVoice_path().get("windows");
        } else {
            path = this.getVoice_path().get("linux");
        }
        return path;
    }


}
