package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

/**
 *  流程定义的部署
 *  activiti表有哪些?
 */
public class ActivitiDeployment {

    //流程定义部署
    public static void main(String[] args) {
        // 1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday.bpmn") //添加bpmn资源
                .addClasspathResource("diagram/holiday.png")
                .name("请假申请单流程")
                .deploy();

        // 4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());

    }

}
