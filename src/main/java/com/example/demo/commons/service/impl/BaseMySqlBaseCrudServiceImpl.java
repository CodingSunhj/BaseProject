package com.example.demo.commons.service.impl;

import com.example.demo.commons.mapper.MyMapper;
import com.example.demo.commons.model.po.PO;
import com.example.demo.commons.model.qo.PageQO;
import com.example.demo.commons.model.vo.PageVO;
import com.example.demo.commons.service.BaseCrudService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @desc MYSQL通用CRUD服务类
 * 备注：使用该类时，注入泛型 E，一定要有对应的 EMapper，例如：使用User的基础服务实现类需要继承MySqlCrudServiceImpl<User, String>，
 *       前提是要有UserMapper extends MyMapper 类
 */
public abstract class BaseMySqlBaseCrudServiceImpl<E extends PO<PK>, PK> implements BaseCrudService<E, PK> {

	@Autowired
	protected MyMapper<E> myMapper;

	protected Class<E> poType;

	public BaseMySqlBaseCrudServiceImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		poType = (Class<E>) pt.getActualTypeArguments()[0];
	}

	@Override
	public PK insert(E record) {
		Assert.notNull(record, "record is not null");

		if (record.getCreateTime() == null) {
			Date currentDate = new Date();
			record.setCreateTime(currentDate);
			record.setUpdateTime(currentDate);
		}
		myMapper.insert(record);
		return record.getId();
	}

	@Override
	public int deleteByPk(PK pk) {
		Assert.notNull(pk, "pk is not null");

		return myMapper.deleteByPrimaryKey(pk);
	}

	@Override
	public int deleteByPks(Iterable<PK> pks) {
		Assert.notNull(pks, "pks is not null");

		String pksStr = this.iterableToSpitStr(pks, ",");
		if (pksStr == null) {
			return 0;
		}

		return myMapper.deleteByIds(pksStr);
	}

	@Override
	public int updateByPk(PK pk, E record) {
		Assert.notNull(pk, "pk is not null");
		Assert.notNull(record, "record is not null");

  		record.setId(pk);
 		if (record.getUpdateTime() == null) {
			record.setUpdateTime(new Date());
		}
		return myMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPkSelective(PK pk, E record) {
		Assert.notNull(pk, "pk is not null");
		Assert.notNull(record, "record is not null");

		record.setId(pk);
		if (record.getUpdateTime() == null) {
			record.setUpdateTime(new Date());
		}
		return myMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PK saveOrUpdate(E record) {
		Assert.notNull(record, "record is not null");

		if (null != record.getId() && null != selectByPk(record.getId())) {
			updateByPk(record.getId(), record);
		} else {
			insert(record);
		}
		return record.getId();
	}

	@Override
	public E selectByPk(PK pk) {
		Assert.notNull(pk, "pk is not null");

		return myMapper.selectByPrimaryKey(pk);
	}

	@Override
	public List<E> selectByPks(Iterable<PK> pks) {
		Assert.notNull(pks, "pks is not null");

		String pksStr = this.iterableToSpitStr(pks, ",");
		if (pksStr == null) {
			return new ArrayList<>();
		}

		return myMapper.selectByIds(pksStr);
	}

	private String iterableToSpitStr(Iterable<PK> pks, String separator) {
		StringBuilder s = new StringBuilder();
		pks.forEach(pk -> s.append(pk).append(separator));

		if (StringUtil.isEmpty(s.toString())) {
			return null;
		} else {
			s.deleteCharAt(s.length() - 1);
		}

		return s.toString();
	}

	@Override
	public List<E> selectAll() {
		return myMapper.selectAll();
	}

	@Override
	public PageVO<E> selectPage(PageQO pageQO) {
		Assert.notNull(pageQO, "pageQO is not null");

		Page<E> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
		myMapper.selectAll();
		return PageVO.build(page);
	}

	@Override
	public PageVO<E> selectPage(PageQO pageQO,Object condition){
		Assert.notNull(pageQO, "pageQO is not null");

		Page<E> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
		try {
			if (condition == null) {
				myMapper.selectAll();
			} else if (condition instanceof Condition) {
				myMapper.selectByCondition(condition);
			} else if (condition instanceof Example) {
				myMapper.selectByExample(condition);
			} else if (poType.isInstance(condition)){
				myMapper.select((E)condition);
			} else {
				try {
					E e = poType.newInstance();
					BeanUtils.copyProperties(condition, e);
					myMapper.select(e);
				} catch (InstantiationException | IllegalAccessException e) {
					throw new RuntimeException("poType.newInstance occurs InstantiationException or IllegalAccessException", e);
				}
			}
		} finally {
			page.close();
		}

		return PageVO.build(page);
	}

}
