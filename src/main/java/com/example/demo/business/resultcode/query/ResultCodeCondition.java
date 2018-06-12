package com.example.demo.business.resultcode.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc:
 * @author: create by SunHJ
 * @date:2018/6/12 14:10
 */
@Data
@ApiModel(value = "返回码查询参数")
public class ResultCodeCondition{

    @ApiModelProperty(value = "返回码名称")
    private String name;
    @ApiModelProperty(value = "返回码")
    private Integer code;
}
