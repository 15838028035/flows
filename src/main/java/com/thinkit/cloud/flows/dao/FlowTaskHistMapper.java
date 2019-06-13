package com.thinkit.cloud.flows.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowTaskHist;


public interface FlowTaskHistMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowTaskHist flowTaskHist);

    public java.lang.Long insertSelective(FlowTaskHist flowTaskHist);

    public FlowTaskHist selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowTaskHist flowTaskHist);

    public java.lang.Long updateByPrimaryKey(FlowTaskHist flowTaskHist);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<FlowTaskHist> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
