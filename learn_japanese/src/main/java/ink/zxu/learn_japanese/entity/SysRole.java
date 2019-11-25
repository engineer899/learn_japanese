package ink.zxu.learn_japanese.entity;

/**
 * @author 张伟
 * @date 2019/11/13 18:12
 */


public class SysRole {

    private String rid; // 角色id
    private String uid; // 用户id
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String remarks; // 角色描述,UI界面显示使用
    private String state; // 是否可用,如果不可用将不会添加给用户

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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