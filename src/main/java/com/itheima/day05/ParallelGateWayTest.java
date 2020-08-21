package com.itheima.day05;

import com.itheima.day04.Holiday;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

/**
 *  act_ru_task
 *  测试并行网关
 *      主要是丰富了我们的请假流程
 */
public class ParallelGateWayTest {

    // 3.填写请假单的任务要执行完成
    public static void main(String[] args) {

        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.查询当前用户的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holidayParallel")
                .taskAssignee("xiaowu")
                .singleResult();//唯一的一条，如果俩条用list

        // 4.处理任务，结合当前用户任务列表的查询操作的话，任务ID:task.getId() 不用查库在写7502
        if(task != null){
            taskService.complete(task.getId());
            System.out.println("用户任务执行完毕...");
        }

        // 5.输出任务的id
        System.out.println(task.getId());
    }

    // 2.启动流程实例
    public static void main2(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到RunService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Holiday holiday = new Holiday();
        holiday.setNum(5F);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("holiday",holiday); //流程变量赋值

        // 3.创建流程实例 流程定义的key需要知道 holiday
        ProcessInstance processInstance = runtimeService.
                startProcessInstanceByKey("holidayParallel",map);

        // 4.输出实例的相关信息
        System.out.println("流程定义ID" + processInstance.getProcessDefinitionId()); //holiday:1:4
        System.out.println("流程实例ID" + processInstance.getId()); //2501

    }

    // 1.部署流程定义 带排他网关,同时还带并行网关
    public static void main1(String[] args) {
        // 1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/day05/holiday06.bpmn") //添加bpmn资源
                .addClasspathResource("diagram/day05/holiday06.png")
                .name("请假申请单流程")
                .deploy();

        // 4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }
}
