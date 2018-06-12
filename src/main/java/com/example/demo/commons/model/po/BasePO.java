package com.example.demo.commons.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public abstract class BasePO<PK> implements PO<PK> {



    @ApiModelProperty(value = "创建时间",hidden = true)
    @Column(name = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间",hidden = true)
    @Column(name = "update_time")
    private Date updateTime;

}
