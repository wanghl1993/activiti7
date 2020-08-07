package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;

/**
 *  查询当前用户的任务列表
 */
public class ActivitiTaskQuery {

    public static void main(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.得到RunService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // 3.创建流程实例
//        Proces

        // 4.输出实例的相关信息
    }
}
