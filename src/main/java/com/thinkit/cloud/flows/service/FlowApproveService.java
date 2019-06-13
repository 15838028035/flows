package com.thinkit.cloud.flows.service;

import java.util.List;

import com.thinkit.cloud.flows.bean.FlowApprove;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface FlowApproveService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowApprove flowApprove);

    public java.lang.Long insertSelective(FlowApprove flowApprove);

    public FlowApprove selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowApprove flowApprove);

    public java.lang.Long updateByPrimaryKey(FlowApprove flowApprove);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<FlowApprove> selectByExample(Query query);
}
