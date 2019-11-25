package ink.zxu.learn_japanese.entity;

/**
 * @author 张伟
 * @date 2019/11/13 18:13
 */

public class SysPermission  {
    private String pid;
    private String rid;
    private String url;
    private String remarks;
    private String state;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}