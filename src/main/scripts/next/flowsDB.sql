CREATE DATABASE /*!32312 IF NOT EXISTS*/`Flows` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `Flows`;


SET FOREIGN_KEY_CHECKS=0;


-- Table structure for FLOW_BORROW_TEST
-- ----------------------------
DROP TABLE IF EXISTS `FLOW_APPROVE`;
CREATE TABLE `FLOW_APPROVE` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `operator` varchar(255) DEFAULT NULL COMMENT '操作人',
  `operate_Time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_result` varchar(255) DEFAULT NULL COMMENT '操作结果 ',
  `opt_content` varchar(255) DEFAULT NULL COMMENT '操作内容',
  `task_Name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  
  `flow_order_id` bigint(20) DEFAULT NULL COMMENT '流程实例ID',
  `flow_task_id` bigint(20) DEFAULT NULL COMMENT '任务ID',
  `process_Id` bigint(20) DEFAULT NULL COMMENT '流程定义ID',
  
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='流程审批表';


-- Table structure for Flow_Process
-- ----------------------------
DROP TABLE IF EXISTS `Flow_Process`;
CREATE TABLE `Flow_Process` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `FLOW_NO` varchar(255) DEFAULT NULL COMMENT '流程编号',
  `FLOW_VERSION` bigint(20) DEFAULT NULL COMMENT '流程版本',
  `FLOW_NAME` varchar(255) DEFAULT NULL COMMENT '流程名称 ',
  `DISPLAY_NAME` varchar(255) DEFAULT NULL COMMENT '显示名称',
  `FLOW_TYPE` varchar(255) DEFAULT NULL COMMENT '流程类型 ',
  `FLOW_CONTENT` blob DEFAULT NULL COMMENT '流程内容',
  
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='流程定义表';

-- Table structure for Flow_Order
-- ----------------------------
DROP TABLE IF EXISTS `Flow_Order`;
CREATE TABLE `Flow_Order` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ORDER_NO` varchar(255) DEFAULT NULL COMMENT '流程编号',
  `ORDER_VERSION` bigint(20) DEFAULT NULL COMMENT '流程版本',
  `FLOW_PROCESS_ID` bigint(20) DEFAULT NULL COMMENT '流程定义ID',
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '父流程实例ID',
   `parent_Node_Name` varchar(255) DEFAULT NULL COMMENT '父流程名称',
 `expire_time` datetime DEFAULT NULL  COMMENT '流程实例期望完成时间',
 `priority` bigint(20) DEFAULT NULL COMMENT '流程实例优先级',
  `variable` varchar(255) DEFAULT NULL COMMENT '流程实例附属变量',
  
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='流程实例表';


-- Table structure for Flow_Order
-- ----------------------------
DROP TABLE IF EXISTS `Flow_Order_hist`;
CREATE TABLE `Flow_Order_hist` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ORDER_NO` varchar(255) DEFAULT NULL COMMENT '流程编号',
  `ORDER_VERSION` bigint(20) DEFAULT NULL COMMENT '流程版本',
  `FLOW_PROCESS_ID` bigint(20) DEFAULT NULL COMMENT '流程定义ID',
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '父流程实例ID',
   `parent_Node_Name` varchar(255) DEFAULT NULL COMMENT '父流程名称',
 `expire_time` datetime DEFAULT NULL  COMMENT '流程实例期望完成时间',
 `priority` bigint(20) DEFAULT NULL COMMENT '流程实例优先级',
  `variable` varchar(255) DEFAULT NULL COMMENT '流程实例附属变量',
  `order_state` bigint(20) DEFAULT NULL COMMENT '流程实例状态（0：结束；1：活动）',
  `end_time` datetime DEFAULT NULL  COMMENT '流程结束时间',
  
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='流程实例历史表';


-- Table structure for Flow_Cc_Order
-- ----------------------------
DROP TABLE IF EXISTS `Flow_Cc_Order`;
CREATE TABLE `Flow_Cc_Order` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` bigint(20) DEFAULT NULL COMMENT '流程实例编号',
  `actor_id` bigint(20) DEFAULT NULL COMMENT '执行人',
  
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
 `finish_Time` datetime DEFAULT NULL  COMMENT '完成时间',
 `FLOW_PROCESS_ID` bigint(20) DEFAULT NULL COMMENT '流程定义ID',
  
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='流程抄送实例表';

-- Table structure for Flow_Task
-- ----------------------------
DROP TABLE IF EXISTS `Flow_Task`;
CREATE TABLE `Flow_Task` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
 `TASK_VEFRSION` bigint(20) DEFAULT NULL COMMENT '版本',
  `FLOW_ORDER_ID` bigint(20) DEFAULT NULL COMMENT ' 流程实例ID',
 `TASK_NAME` datetime DEFAULT NULL  COMMENT '任务名称 ',
  `display_Name` varchar(255) DEFAULT NULL COMMENT '显示名称',
  `task_Type` bigint(20) DEFAULT NULL COMMENT '任务类型（0：主办任务；1：协办任务）',
  `operator` varchar(255) DEFAULT NULL  COMMENT '任务处理者ID',
   `finish_Time` datetime DEFAULT NULL COMMENT '任务完成时间',
   `expire_Date` datetime DEFAULT NULL COMMENT '期望的完成时间',
    `remind_Date` datetime DEFAULT NULL COMMENT '提醒时间',
    `action_Url` varchar(255) DEFAULT NULL  COMMENT '任务关联的表单url',
    `actorIds` varchar(255) DEFAULT NULL  COMMENT '任务参与者列表',
    `parent_Task_Id` bigint(20) DEFAULT NULL COMMENT '父任务Id',
    `variable` varchar(255) DEFAULT NULL  COMMENT '任务参与者列表',
  
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='流程任务表';

-- Table structure for Flow_Task
-- ----------------------------
DROP TABLE IF EXISTS `Flow_Task_hist`;
CREATE TABLE `Flow_Task_hist` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
 `TASK_VEFRSION` bigint(20) DEFAULT NULL COMMENT '版本',
  `FLOW_ORDER_ID` bigint(20) DEFAULT NULL COMMENT ' 流程实例ID',
 `TASK_NAME` datetime DEFAULT NULL  COMMENT '任务名称 ',
  `display_Name` varchar(255) DEFAULT NULL COMMENT '显示名称',
  `task_Type` bigint(20) DEFAULT NULL COMMENT '任务类型（0：主办任务；1：协办任务）',
  `operator` varchar(255) DEFAULT NULL  COMMENT '任务处理者ID',
   `finish_Time` datetime DEFAULT NULL COMMENT '任务完成时间',
   `expire_Date` datetime DEFAULT NULL COMMENT '期望的完成时间',
    `remind_Date` datetime DEFAULT NULL COMMENT '提醒时间',
    `action_Url` varchar(255) DEFAULT NULL  COMMENT '任务关联的表单url',
    `actorIds` varchar(255) DEFAULT NULL  COMMENT '任务参与者列表',
    `parent_Task_Id` bigint(20) DEFAULT NULL COMMENT '父任务Id',
    `variable` varchar(255) DEFAULT NULL  COMMENT '任务参与者列表',
  
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='流程任务历史表';


-- Table structure for Flow_Task_Actor
-- ----------------------------
DROP TABLE IF EXISTS `Flow_Task_Actor`;
CREATE TABLE `Flow_Task_Actor` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TASK_ID` bigint(20) DEFAULT NULL COMMENT '任务ID',
  `ACTOR_ID` varchar(255) DEFAULT NULL COMMENT '关联的参与者ID（参与者可以为用户、部门、角色)',
  
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='流程任务参与者';

-- Table structure for Flow_Task_Actor_hist
-- ----------------------------
DROP TABLE IF EXISTS `Flow_Task_Actor_hist`;
CREATE TABLE `Flow_Task_Actor_hist` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TASK_ID` bigint(20) DEFAULT NULL COMMENT '任务ID',
  `ACTOR_ID` varchar(255) DEFAULT NULL COMMENT '关联的参与者ID（参与者可以为用户、部门、角色)',
  
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='流程任务参与者历史';


