package com.example.demo.business.resultcode.service;

import com.example.demo.business.resultcode.entity.CommonResultCode;
import com.example.demo.business.resultcode.query.ResultCodeCondition;
import com.example.demo.commons.model.qo.PageQO;
import com.example.demo.commons.model.vo.PageVO;
import com.example.demo.commons.service.BaseCrudService;
import com.example.demo.enums.ResultCode;
import org.springframework.stereotype.Service;

/**
 * @author: create by SunHJ
 * @date:2018/6/12 9:34
 */
public interface CommonResultCodeService extends BaseCrudService<CommonResultCode,Long>{

    /**
     * 分页获取返回码
     * @param pageQO
     * @return
     */
    PageVO<CommonResultCode> pageCommonResultCode(PageQO pageQO, ResultCodeCondition codeCondition);

    /**
     * 将枚举类中的返回码持久化到数据库和Redis
     * @return
     */
    String resultCodeToPersist();

    /**
     * 运行时修改指定返回码的提示信息
     * @param code
     * @param msg
     * @return
     */
    int updateMsg(Integer code,String msg);
}
