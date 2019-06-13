package com.thinkit.cloud.flows.service;

import java.util.List;

import com.thinkit.cloud.flows.bean.FlowCcOrder;
import com.zhongkexinli.micro.serv.common.msg.LayUiTableResultResponse;
import com.zhongkexinli.micro.serv.common.pagination.Query;


public interface FlowCcOrderService  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowCcOrder flowCcOrder);

    public java.lang.Long insertSelective(FlowCcOrder flowCcOrder);

    public FlowCcOrder selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowCcOrder flowCcOrder);

    public java.lang.Long updateByPrimaryKey(FlowCcOrder flowCcOrder);

    public LayUiTableResultResponse	 selectByQuery(Query query) ;

    public  List<FlowCcOrder> selectByExample(Query query);
    
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
    public void createCcOrder(String orderId, String creator, String... actorIds);
}
