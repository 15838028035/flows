package com.thinkit.cloud.flows.service;

import java.util.List;

import com.thinkit.cloud.flows.bean.FlowTaskActorHist;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface FlowTaskActorHistService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowTaskActorHist flowTaskActorHist);

    public java.lang.Long insertSelective(FlowTaskActorHist flowTaskActorHist);

    public FlowTaskActorHist selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowTaskActorHist flowTaskActorHist);

    public java.lang.Long updateByPrimaryKey(FlowTaskActorHist flowTaskActorHist);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<FlowTaskActorHist> selectByExample(Query query);
}
