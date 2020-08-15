package com.itheima.day04;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

/**
 *  流程变量的测试
 */
public class VariableTest2 {

    // 完成任务 zhangsan --- lisi --- 判断流程变量的请假天数,1天 ---- 分支: 人事经理存档
    public static void main(String[] args) {
        // 1.得到ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到RuntimeService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.查询当前用户是否有任务
        String key = "myProcess_1";
        Task task = taskService.createTaskQuery().processDefinitionKey(key)
                .taskAssignee("zhangsan").singleResult();

        Map<String, Object> map = new HashMap<String, Object>();

        //初始化一些参数
        Holiday holiday = new Holiday();
        holiday.setNum(5F);
        map.put("holiday",holiday);

        // 4.判断task != null,说明当前用户有任务
        if(task != null) {
            taskService.complete(task.getId(),map); //完成任务时,设置流程变量的值
            System.out.println("任务执行完毕");
        }



    }

    //启动流程实例,同时还要设置流程变量的值
    // act_ge_bytearray
    // act_ru_variable
    public static void main2(String[] args) {
        // 1.得到ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // 3.流程定义的key问题 myProcess_1
        String key = "myProcess_1";
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        Holiday holiday = new Holiday();
//        holiday.setNum(5F);
//        map.put("holiday",holiday);

        // 4.启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);

        // 5.输出实例信息
        System.out.println(processEngine.getName());
        System.out.println(processInstance.getProcessDefinitionId());

    }

    //新的请假流程定义部署
    public static void main1(String[] args) {
        // 1.得到ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 3.部署
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday4.bpmn")
                .addClasspathResource("diagram/holiday4.png")
                .name("请假流程-流程变量")
                .deploy();

        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

}
