package com.thinkit.cloud.flows.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.flows.dao.FlowApproveMapper;
import com.thinkit.cloud.flows.service.FlowApproveService;
import com.thinkit.cloud.flows.bean.FlowApprove;

import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class FlowApproveServiceImpl  implements FlowApproveService{
	
	@Autowired
	private FlowApproveMapper flowApproveMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return flowApproveMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(FlowApprove flowApprove){
		return flowApproveMapper.insert(flowApprove);
	}

	@Override
	public java.lang.Long insertSelective(FlowApprove flowApprove) {
		return flowApproveMapper.insertSelective(flowApprove);
	}

	@Override
	public FlowApprove selectByPrimaryKey(java.lang.Long id) {
		return flowApproveMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(FlowApprove flowApprove) {
		return flowApproveMapper.updateByPrimaryKeySelective(flowApprove);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(FlowApprove flowApprove) {
		return flowApproveMapper.updateByPrimaryKey(flowApprove);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = flowApproveMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<FlowApprove> selectByExample(Query query) {
		return flowApproveMapper.selectByExample(query);
	}

}
