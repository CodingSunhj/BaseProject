package com.example.demo.business.resultcode.service.impl;

import com.example.demo.business.resultcode.entity.CommonResultCode;
import com.example.demo.business.resultcode.mapper.CommonResultCodeMapper;
import com.example.demo.business.resultcode.query.ResultCodeCondition;
import com.example.demo.business.resultcode.service.CommonResultCodeService;
import com.example.demo.commons.model.qo.PageQO;
import com.example.demo.commons.model.vo.PageVO;
import com.example.demo.commons.service.impl.BaseMySqlBaseCrudServiceImpl;
import com.example.demo.enums.ResultCode;
import com.example.demo.manager.ThreadPoolManager;
import com.example.demo.redis.key.ResultCodeKey;
import com.example.demo.utils.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @desc:
 * @author: create by SunHJ
 * @date:2018/6/12 9:38
 */

@Service
public class CommonResultCodeServiceImpl extends BaseMySqlBaseCrudServiceImpl<CommonResultCode, Long> implements CommonResultCodeService {

    @Autowired
    CommonResultCodeMapper commonResultCodeMapper;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public PageVO<CommonResultCode> pageCommonResultCode(PageQO pageQO, ResultCodeCondition resultCodeCondition) {
        Example example = new Example(CommonResultCode.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(resultCodeCondition.getCode())){
            criteria.andEqualTo("code",resultCodeCondition.getCode());
        }
        if(!StringUtils.isEmpty(resultCodeCondition.getName())){
            criteria.andEqualTo("name",resultCodeCondition.getName());
        }
        return selectPage(pageQO,example);
    }

    @Override
    public String resultCodeToPersist() {
        Example example = Example.builder(CommonResultCode.class).select("id", "code", "name", "msg").build();
        List<CommonResultCode> alreadyList = commonResultCodeMapper.selectByExample(example);
        for (ResultCode resultCode : ResultCode.values()) {
            CommonResultCode commonResultCode = new CommonResultCode();
            commonResultCode.setCode(resultCode.code());
            commonResultCode.setMsg(resultCode.message());
            commonResultCode.setName(resultCode.name());
            boolean isAlready = false;
            if (alreadyList.size() > 0) {
                for (CommonResultCode already : alreadyList) {
                    if (already.getName().equalsIgnoreCase(commonResultCode.getName())) {
                        isAlready = true;
                        if (!already.getMsg().equals(commonResultCode.getMsg())) {
                            already.setMsg(commonResultCode.getMsg());
                            updateByPkSelective(already.getId(), already);
                        }
                    }
                }
                if (!isAlready) {
                    insert(commonResultCode);
                }
            } else {
                insert(commonResultCode);
            }
            redisTemplate.opsForValue().set(ResultCodeKey.resultCode.getPrefix() + commonResultCode.getName(), commonResultCode);
        }
        return "SUCCESS";
    }

    @Override
    public int updateMsg(Integer code, String msg) {
        Example example = new Example(CommonResultCode.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code",code);

        CommonResultCode commonResultCode = commonResultCodeMapper.selectOneByExample(example);
        commonResultCode.setMsg(msg);
        redisTemplate.opsForValue().set(ResultCodeKey.resultCode.getPrefix()+ commonResultCode.getName(), commonResultCode);
        try {
            Field field = ResultCode.class.getField(commonResultCode.getName());
            ResultCode resultCode = (ResultCode) field.get(ResultCode.class);
            resultCode.setMessage(msg);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return updateByPkSelective(commonResultCode.getId(),commonResultCode);
    }
}
