package com.thinkit.cloud.flows.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.flows.dao.FlowOrderHistMapper;
import com.thinkit.cloud.flows.service.FlowOrderHistService;
import com.thinkit.cloud.flows.bean.FlowOrderHist;

import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class FlowOrderHistServiceImpl  implements FlowOrderHistService{
	
	@Autowired
	private FlowOrderHistMapper flowOrderHistMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return flowOrderHistMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(FlowOrderHist flowOrderHist){
		return flowOrderHistMapper.insert(flowOrderHist);
	}

	@Override
	public java.lang.Long insertSelective(FlowOrderHist flowOrderHist) {
		return flowOrderHistMapper.insertSelective(flowOrderHist);
	}

	@Override
	public FlowOrderHist selectByPrimaryKey(java.lang.Long id) {
		return flowOrderHistMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(FlowOrderHist flowOrderHist) {
		return flowOrderHistMapper.updateByPrimaryKeySelective(flowOrderHist);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(FlowOrderHist flowOrderHist) {
		return flowOrderHistMapper.updateByPrimaryKey(flowOrderHist);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = flowOrderHistMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<FlowOrderHist> selectByExample(Query query) {
		return flowOrderHistMapper.selectByExample(query);
	}

	@Override
  public FlowOrderHist getHistOrder(String orderId) {
	    return flowOrderHistMapper.selectByPrimaryKey(Long.parseLong(orderId));
  }
}
