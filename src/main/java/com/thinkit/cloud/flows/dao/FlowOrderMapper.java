package com.thinkit.cloud.flows.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowOrder;


public interface FlowOrderMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowOrder flowOrder);

    public java.lang.Long insertSelective(FlowOrder flowOrder);

    public FlowOrder selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowOrder flowOrder);

    public java.lang.Long updateByPrimaryKey(FlowOrder flowOrder);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<FlowOrder> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
