package com.thinkit.cloud.flows.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowOrderHist;


public interface FlowOrderHistMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowOrderHist flowOrderHist);

    public java.lang.Long insertSelective(FlowOrderHist flowOrderHist);

    public FlowOrderHist selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowOrderHist flowOrderHist);

    public java.lang.Long updateByPrimaryKey(FlowOrderHist flowOrderHist);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<FlowOrderHist> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
