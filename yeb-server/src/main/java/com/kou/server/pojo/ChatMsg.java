package com.kou.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author JIAJUN KOU
 *
 * 聊天消息类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {


    private String from;
    private String to;
    private String content;
    private LocalDateTime date;
    private String formNickName;

}
