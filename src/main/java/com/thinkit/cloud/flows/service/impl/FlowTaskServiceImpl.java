package com.thinkit.cloud.flows.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.flows.dao.FlowTaskMapper;
import com.thinkit.cloud.flows.service.FlowTaskService;
import com.thinkit.cloud.flows.bean.FlowTask;

import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class FlowTaskServiceImpl  implements FlowTaskService{
	
	@Autowired
	private FlowTaskMapper flowTaskMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return flowTaskMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(FlowTask flowTask){
		return flowTaskMapper.insert(flowTask);
	}

	@Override
	public java.lang.Long insertSelective(FlowTask flowTask) {
		return flowTaskMapper.insertSelective(flowTask);
	}

	@Override
	public FlowTask selectByPrimaryKey(java.lang.Long id) {
		return flowTaskMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(FlowTask flowTask) {
		return flowTaskMapper.updateByPrimaryKeySelective(flowTask);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(FlowTask flowTask) {
		return flowTaskMapper.updateByPrimaryKey(flowTask);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = flowTaskMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<FlowTask> selectByExample(Query query) {
		return flowTaskMapper.selectByExample(query);
	}

}
