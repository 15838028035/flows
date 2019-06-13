package com.thinkit.cloud.flows.service;

import java.util.List;

import com.thinkit.cloud.flows.bean.FlowTask;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface FlowTaskService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowTask flowTask);

    public java.lang.Long insertSelective(FlowTask flowTask);

    public FlowTask selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowTask flowTask);

    public java.lang.Long updateByPrimaryKey(FlowTask flowTask);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<FlowTask> selectByExample(Query query);
}
