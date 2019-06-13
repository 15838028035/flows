package com.thinkit.cloud.flows.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.thinkit.cloud.flows.bean.FlowCcOrder;
import com.thinkit.cloud.flows.dao.FlowCcOrderMapper;
import com.thinkit.cloud.flows.service.FlowCcOrderService;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;

@Service
public class FlowCcOrderServiceImpl  implements FlowCcOrderService{
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	
	@Autowired
	private FlowCcOrderMapper flowCcOrderMapper;
	
	@Override
	public java.lang.Long deleteByPrimaryKey(java.lang.Long id) {
		return flowCcOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public java.lang.Long insert(FlowCcOrder flowCcOrder){
		return flowCcOrderMapper.insert(flowCcOrder);
	}

	@Override
	public java.lang.Long insertSelective(FlowCcOrder flowCcOrder) {
		return flowCcOrderMapper.insertSelective(flowCcOrder);
	}

	@Override
	public FlowCcOrder selectByPrimaryKey(java.lang.Long id) {
		return flowCcOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public java.lang.Long updateByPrimaryKeySelective(FlowCcOrder flowCcOrder) {
		return flowCcOrderMapper.updateByPrimaryKeySelective(flowCcOrder);
	}

	@Override
	public java.lang.Long updateByPrimaryKey(FlowCcOrder flowCcOrder) {
		return flowCcOrderMapper.updateByPrimaryKey(flowCcOrder);
	}

	@Override
	 public LayUiTableResultResponse selectByQuery(Query query) {
	        com.github.pagehelper.Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
	        List<Map<String,Object>> list  = flowCcOrderMapper.selectByPageExample(query);
	        return new LayUiTableResultResponse(result.getTotal(), list);
	}

	@Override
	public List<FlowCcOrder> selectByExample(Query query) {
		return flowCcOrderMapper.selectByExample(query);
	}

	/**
   * 创建实例的抄送
   * 
   * @param orderId
   *          orderId
   * @param creator
   *          创建人
   * @param actorIds
   *          参与人
   */
  public void createCcOrder(String orderId, String creator, String... actorIds) {
    for (String actorId : actorIds) {
        FlowCcOrder ccorder = new FlowCcOrder();
      ccorder.setOrderId(Long.parseLong(orderId));
      ccorder.setActorId(Long.parseLong(actorId));
      ccorder.setCreator(creator);
    //  ccorder.setStatus(FlowConstains.STATE_ACTIVE);
      ccorder.setCreateTime(new Date());
      try {
        this.insert(ccorder);
      } catch (Exception e) {
          logger.error("error",e);
      }
    }
  }
}
