package com.example.demo.business.resultcode.entity;

import com.example.demo.commons.mapper.SimpleGenId;
import com.example.demo.commons.model.po.BasePO;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "common_result_code")
public class CommonResultCode extends BasePO<Long> {

    @Id
    @Column(name = "id")
    @KeySql(genId = SimpleGenId.class)
    private Long id;

    /**
     * 错误码
     */
    @Column(name = "code")
    private Integer code;

    /**
     * 错误提示
     */
    @Column(name = "msg")
    private String msg;

    /**
     * 错误名称
     */
    @Column(name = "name")
    private String name;


}