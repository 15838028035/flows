package com.thinkit.cloud.flows.dao;

import java.util.List;
import java.util.Map;

import com.thinkit.cloud.flows.bean.FlowTaskActor;


public interface FlowTaskActorMapper  {

    public java.lang.Long deleteByPrimaryKey(java.lang.Long id);

    public java.lang.Long insert(FlowTaskActor flowTaskActor);

    public java.lang.Long insertSelective(FlowTaskActor flowTaskActor);

    public FlowTaskActor selectByPrimaryKey(java.lang.Long id);

    public java.lang.Long updateByPrimaryKeySelective(FlowTaskActor flowTaskActor);

    public java.lang.Long updateByPrimaryKey(FlowTaskActor flowTaskActor);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
   public  List<FlowTaskActor> selectByExample(Object mapAndObject);

   /**
     * 根据条件查询列表
     *
     * @param example
     */
    public List<Map<String,Object>> selectByPageExample(Object mapAndObject);
}
