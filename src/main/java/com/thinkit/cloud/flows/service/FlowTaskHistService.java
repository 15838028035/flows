package com.thinkit.cloud.flows.service;

import java.util.List;

import com.thinkit.cloud.flows.bean.FlowTaskHist;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface FlowTaskHistService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowTaskHist flowTaskHist);

    public java.lang.Long insertSelective(FlowTaskHist flowTaskHist);

    public FlowTaskHist selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowTaskHist flowTaskHist);

    public java.lang.Long updateByPrimaryKey(FlowTaskHist flowTaskHist);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<FlowTaskHist> selectByExample(Query query);
}
