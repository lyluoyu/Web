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

@Entity
@Table(name = "TB_MESSAGE")
public class Message {
    public static final int TYPE_STR = 1; // 字符串类型
    public static final int TYPE_PIC = 1; // 图片类型
    public static final int TYPE_FULE = 3; // 文件类型
    public static final int TYPE_AUDIO = 4; // 语音类型

    // 这是一个主键
    @Id  // 注解
    @PrimaryKeyJoinColumn // 主键标识
    // 主键(String id)生成存储的类型为UUID
    // 这里不自动生成UUID，Id由代码写入，由客户端负责生成
    // 避免复杂的服务器和客服端的映射关系
    //@GeneratedValue(generator ="uuid")
    // 把uuid的生成器定义为uuid2,uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid",strategy = "uuid2")// 重定向生成器
    // 不允许更改不,不允许为null
    @Column(updatable =false,nullable = false)
    private String id;

    // 内容不允许为null,类型为text
    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;

    // 附件
    @Column
    private String attach;

    // 类型类型
    @Column(nullable = false)
    private int type;

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

    // 发送者 不为null
    // 多个消息对应一个发送者
    @JoinColumn(name="senderId")
    @ManyToOne(optional = false)
    private User sender;
    // 这个字段仅仅只是为了对应sender的数据库字段senderId
    // 不允许手动的更新或插入
    @Column(nullable = false,updatable = false,insertable = false)
    private String senderId;

    // 接收者 可为null
    // 多个消息对应一个接收者
    @JoinColumn(name="receiverId")
    @ManyToOne
    private User receiver;
    // 这个字段仅仅只是为了对应receiver的数据库字段receiverId
    // 不允许手动的更新或插入
    @Column(updatable = false,insertable = false)
    private String receiverId;

    // 一个群可以接收多个消息
    @ManyToOne
    @JoinColumn(name="groupId")
    private Group group;
    // 这个字段仅仅只是为了对应receiver的数据库字段receiverId
    // 不允许手动的更新或插入
    @Column(updatable = false,insertable = false)
    private String groupId;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
