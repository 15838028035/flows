package com.thinkit.cloud.flows.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowProcess;


public interface FlowProcessMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowProcess flowProcess);

    public java.lang.Long insertSelective(FlowProcess flowProcess);

    public FlowProcess selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowProcess flowProcess);

    public java.lang.Long updateByPrimaryKey(FlowProcess flowProcess);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<FlowProcess> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
