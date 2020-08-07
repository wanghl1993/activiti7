package com.itheima.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 *  测试类
 *   作用: 测试activiti所需要的25张表的生成
 */
public class ActivitiTest {

    @Test
    public void testGenTable(){

        //条件: 1.activiti配置文件名称 : activiti.cfg.xml 2.bean的id="processEngineConfiguration"

        // 1.创建ProcessEngineConfiguration
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml","processEngineConfiguration");

        // 2.创建ProcessEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();

        // 3.输出processEngine对象
        System.out.println(processEngine);

        processEngine.getRuntimeService();

        // domain -- dao -- service
        HistoryService historyService = processEngine.getHistoryService();

    }
}
