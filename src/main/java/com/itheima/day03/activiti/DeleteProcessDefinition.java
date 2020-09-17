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
 *  act_re_deplayment 部署信息
 *  act_re_procdef    流程定义
 *  act_re_bytearray  流程定义的bpmn文件及png文件
 */
public class DeleteProcessDefinition {


    /**
     * 注意事项:
     *      1.当我们正在执行一套流程没有完全审批结束的时候，此时如果要删除流程定义信息就会失败
     *      2.如果公司要强制删除,可以使用repostitoryService.deleteDeployment("1",true)
     *      //参数true代表级联删除,此时就会先删除没有完成的流程节点,最后就可以删除流程定义信息
     *      false的值代表不级联
     * @param args
     */
    public static void main(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.创建RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 3.执行删除流程定义   参数代表流程部署的ID
        repositoryService.deleteDeployment("15001",true);
    }
}
