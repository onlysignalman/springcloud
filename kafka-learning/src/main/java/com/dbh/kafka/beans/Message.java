package com.dbh.kafka.beans;

import lombok.Data;

import java.util.Date;

/**
 * 消息类
 *
 * @author dbh
 * @date 2018/02/04
 */
@Data
public class Message {

    /**
     * id
     */
    private Long id;

    /**
     * 消息
     */
    private String msg;

    /**
     * 时间戳
     */
    private Date sendTime;
}
