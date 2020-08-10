package com.itheima.day03.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;

/**
 *  删除已经部署的流程定义
 *
 */
public class DeleteProcessDefinition {

    public static void main(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.创建RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 3.执行删除流程定义   参数代表流程部署的ID
        repositoryService.deleteDeployment("1");
    }
}
