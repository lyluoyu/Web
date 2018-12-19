package com.example.web.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 用户关系的Model,
 * 用于用户之间进行好友关系的实现
 */
@Entity // 实体类标识
@Table(name = "TB_USER_FOLLOW") // 表名
public class UserFollow {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;

    // 定义一个发起人，你关注某人，这里就是你
    // 多对一，你可以关注很多人，你的每一次关注都是一条记录
    // 你可以创建很多个关注的信息，所以是多对一；
    // 这里的多对一是：User 对应 多个UserFollow
    // optional 不可选，必须存储，一条关注记录一定要有一个关注人
    @ManyToOne(optional = false)
    // 定义关联的表字段名为originId，对应的是User.id
    // 定义的是数据库中的储存字段
    @JoinColumn(name="originId")
    private User origin;
    // 把这个列提取到我们的Model中，不允许为null,不允许更新、插入
    @Column(nullable = false,updatable = false,insertable = false)
    private String originId;

    // 定义关注的目标，你关注的人
    // 也是多对一，你可以被很多人关注，每一次关注都是一条记录
    // 所以是 多个UserFollow 对应 一个User 的情况
    @ManyToOne(optional = false)
    // 定义关联的表字段名为targetId，对应的是User.id
    // 定义的是数据库中的储存字段
    @JoinColumn(name = "targetId")
    private User target;
    // 把这个列提取到我们的Model中，不允许为null,不允许更新、插入
    @Column(nullable = false,updatable = false,insertable = false)
    private String targetId;

    // 别名，也就是对target的备注,可以为null
    @Column
    private String alias;

    // 定义为创建时间戳，在创建时就已经写入
    // 时间戳（timestamp），一个能表示一份数据在某个特定时间之前已经存在的、 完整的、 可验证的数据,通常是一个字符序列，唯一地标识某一刻的时间。
    @CreationTimestamp
    @Column(nullable = false)
    // createAt（本地时间）
    private LocalDateTime createAt =LocalDateTime.now();

    // 定义为更新时间戳
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt =LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
