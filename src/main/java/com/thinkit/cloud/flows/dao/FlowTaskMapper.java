package com.thinkit.cloud.flows.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowTask;


public interface FlowTaskMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowTask flowTask);

    public java.lang.Long insertSelective(FlowTask flowTask);

    public FlowTask selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowTask flowTask);

    public java.lang.Long updateByPrimaryKey(FlowTask flowTask);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<FlowTask> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
