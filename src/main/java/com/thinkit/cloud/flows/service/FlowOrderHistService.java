package com.thinkit.cloud.flows.service;

import java.util.List;

import com.thinkit.cloud.flows.bean.FlowOrderHist;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface FlowOrderHistService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowOrderHist flowOrderHist);

    public java.lang.Long insertSelective(FlowOrderHist flowOrderHist);

    public FlowOrderHist selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowOrderHist flowOrderHist);

    public java.lang.Long updateByPrimaryKey(FlowOrderHist flowOrderHist);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<FlowOrderHist> selectByExample(Query query);
    
    public FlowOrderHist getHistOrder(String orderId);
}
