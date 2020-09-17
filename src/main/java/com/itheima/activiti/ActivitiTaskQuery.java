package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 *  查询当前用户的任务列表
 */
public class ActivitiTaskQuery {


    //wangwu完成任务
    public static void main(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.根据流程定义的key，负责人assignee来实现当前用户的任务列表查询
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("messagex")
//                .taskAssignee("2")
                .taskId("12506")
                .active()
                .singleResult();

        // 4.任务列表的展示
        System.out.println("流程实例ID:" + task.getProcessInstanceId());
        System.out.println("任务ID:" + task.getId()); //5002
        System.out.println("任务负责人:" + task.getAssignee());
        System.out.println("任务名称:" + task.getName());

    }

//    public static void main(String[] args) {
//        // 1.得到ProcessEngine对象
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        // 2.得到TaskService对象
//        TaskService taskService = processEngine.getTaskService();
//
//        // 3.根据流程定义的key，负责人assignee来实现当前用户的任务列表查询
//        List<Task> taskList = taskService.createTaskQuery()
//                .processDefinitionKey("holiday")
//                .taskAssignee("zhangsan")
//                .list();
//
//        // 4.任务列表的展示
//        for (Task task : taskList) {
//            System.out.println("流程实例ID:" + task.getProcessInstanceId());
//            System.out.println("任务ID:" + task.getId());
//            System.out.println("任务负责人:" + task.getAssignee());
//            System.out.println("任务名称:" + task.getName());
//
//        }
//
//    }
}
