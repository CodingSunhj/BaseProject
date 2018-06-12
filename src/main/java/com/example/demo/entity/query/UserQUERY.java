package com.example.demo.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户查询")
@Data
public class UserQUERY {
    @ApiModelProperty(value = "姓名")
    String name;
    @ApiModelProperty(value = "密码")
    String password;
}
