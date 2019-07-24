package life.majiang.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreated;
    private Long gmtModified;
    private String avatarUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", accountId='" + accountId + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                ", avatar='" + avatarUrl + '\'' +
                '}';
    }
}
