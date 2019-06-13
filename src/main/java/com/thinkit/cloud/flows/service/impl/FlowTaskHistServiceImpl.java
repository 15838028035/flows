package com.thinkit.cloud.flows.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.flows.dao.FlowTaskHistMapper;
import com.thinkit.cloud.flows.service.FlowTaskHistService;
import com.thinkit.cloud.flows.bean.FlowTaskHist;

import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class FlowTaskHistServiceImpl  implements FlowTaskHistService{
	
	@Autowired
	private FlowTaskHistMapper flowTaskHistMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return flowTaskHistMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(FlowTaskHist flowTaskHist){
		return flowTaskHistMapper.insert(flowTaskHist);
	}

	@Override
	public java.lang.Long insertSelective(FlowTaskHist flowTaskHist) {
		return flowTaskHistMapper.insertSelective(flowTaskHist);
	}

	@Override
	public FlowTaskHist selectByPrimaryKey(java.lang.Long id) {
		return flowTaskHistMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(FlowTaskHist flowTaskHist) {
		return flowTaskHistMapper.updateByPrimaryKeySelective(flowTaskHist);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(FlowTaskHist flowTaskHist) {
		return flowTaskHistMapper.updateByPrimaryKey(flowTaskHist);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = flowTaskHistMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<FlowTaskHist> selectByExample(Query query) {
		return flowTaskHistMapper.selectByExample(query);
	}

}
