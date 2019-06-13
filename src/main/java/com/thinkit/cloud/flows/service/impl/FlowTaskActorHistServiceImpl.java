package com.thinkit.cloud.flows.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.flows.dao.FlowTaskActorHistMapper;
import com.thinkit.cloud.flows.service.FlowTaskActorHistService;
import com.thinkit.cloud.flows.bean.FlowTaskActorHist;

import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class FlowTaskActorHistServiceImpl  implements FlowTaskActorHistService{
	
	@Autowired
	private FlowTaskActorHistMapper flowTaskActorHistMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return flowTaskActorHistMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(FlowTaskActorHist flowTaskActorHist){
		return flowTaskActorHistMapper.insert(flowTaskActorHist);
	}

	@Override
	public java.lang.Long insertSelective(FlowTaskActorHist flowTaskActorHist) {
		return flowTaskActorHistMapper.insertSelective(flowTaskActorHist);
	}

	@Override
	public FlowTaskActorHist selectByPrimaryKey(java.lang.Long id) {
		return flowTaskActorHistMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(FlowTaskActorHist flowTaskActorHist) {
		return flowTaskActorHistMapper.updateByPrimaryKeySelective(flowTaskActorHist);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(FlowTaskActorHist flowTaskActorHist) {
		return flowTaskActorHistMapper.updateByPrimaryKey(flowTaskActorHist);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = flowTaskActorHistMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<FlowTaskActorHist> selectByExample(Query query) {
		return flowTaskActorHistMapper.selectByExample(query);
	}

}
