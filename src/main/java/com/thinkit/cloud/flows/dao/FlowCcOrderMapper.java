package com.thinkit.cloud.flows.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowCcOrder;


public interface FlowCcOrderMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowCcOrder flowCcOrder);

    public java.lang.Long insertSelective(FlowCcOrder flowCcOrder);

    public FlowCcOrder selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowCcOrder flowCcOrder);

    public java.lang.Long updateByPrimaryKey(FlowCcOrder flowCcOrder);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<FlowCcOrder> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
