package com.example.demo.business.resultcode.web;

import com.example.demo.business.resultcode.entity.CommonResultCode;
import com.example.demo.business.resultcode.query.ResultCodeCondition;
import com.example.demo.business.resultcode.service.CommonResultCodeService;
import com.example.demo.commons.model.qo.PageQO;
import com.example.demo.commons.model.vo.PageVO;
import com.example.demo.enums.ResultCode;
import com.example.demo.redis.key.ResultCodeKey;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: create by SunHJ
 */

@Api(tags = "维护返回码")
@RestController
@RequestMapping("result_code")
public class ResultCodeController {

    @Autowired
    CommonResultCodeService commonResultCodeService;

    @ApiOperation(value = "获取所有的返回码信息")
    @GetMapping
    public PageVO<CommonResultCode> getAllResultCode(PageQO pageQO,ResultCodeCondition resultCodeCondition){
        return commonResultCodeService.pageCommonResultCode(pageQO,resultCodeCondition);
    }

    @ApiOperation(value = "开发人员将枚举类中的返回码生成到数据库和Redis",notes = "仅开发阶段使用")
    @GetMapping("persist")
    public void resultCodeToPersist() throws ExecutionException, InterruptedException {
        commonResultCodeService.resultCodeToPersist();
    }

    @ApiOperation(value = "修改对应code的msg提示",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "要修改的code值",required = true,paramType = "path"),
            @ApiImplicitParam(name = "msg",value = "要修改code值的msg",required = true,paramType = "query")
    })
    @PostMapping("{code}")
    public void updateMsg(@PathVariable("code") Integer code,String msg){
        commonResultCodeService.updateMsg(code,msg);
    }


}
