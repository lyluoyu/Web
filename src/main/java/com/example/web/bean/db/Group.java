package com.example.web.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_GROUP")
public class Group {
    // 这是一个主键
    @Id  // 注解
    @PrimaryKeyJoinColumn // 主键标识
    // 主键(String id)生成存储的类型为UUID,自动生成UUID
    @GeneratedValue(generator ="uuid")
    // 把uuid的生成器定义为uuid2,uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid",strategy = "uuid2")// 重定向生成器
    // 不允许更改不,不允许为null
    @Column(updatable =false,nullable = false)
    private String id;

    // 群名称
    @Column(nullable = false)
    private String name;

    // 群描述
    @Column(nullable = false)
    private String description;

    // 群头像
    @Column(nullable = false)
    private String picture;

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

    // 群的创建者
    // optional：可选为false,必须有一个创建者
    // fetch：加载方式FetchType.EAGER，急加载，
    // 意味着加载群的信息的时候必须加载owner的信息
    // cascade：联级级别为ALL，所有的更改（更新，删除等）都将进行关系更新
    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "ownerId")
    private User owner;
    @Column(nullable = false,updatable = false,insertable = false)
    private String ownerID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
}
