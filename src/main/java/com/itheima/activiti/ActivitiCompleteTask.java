package com.itheima.activiti;

import com.itheima.entity.Message;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

/**
 *  处理当前用户的任务列表
 *  背后操作的表:
 *      act_hi_actinst
 *      act_hi_identitylink
 *      act_hi_taskinst
 *      act_ru_identitylink
 *      act_ru_task
 */
public class ActivitiCompleteTask {


    //查询当前用户wangwu的任务并处理掉
    public static void main1(String[] args) {

        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.查询当前用户的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("messagex")
                .taskAssignee("1")
                .singleResult();//唯一的一条，如果俩条用list

        // 4.处理任务，结合当前用户任务列表的查询操作的话，任务ID:task.getId() 不用查库在写7502
        taskService.complete(task.getId());

        // 5.输出任务的id
        System.out.println(task.getId());
    }

    //lisi完成自己的任务
//    public static void main(String[] args) {
//
//        // 1.得到ProcessEngine对象
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//
//        // 2.得到TaskService对象
//        TaskService taskService = processEngine.getTaskService();
//
//        // 3.处理任务，结合当前用户任务列表的查询操作的话，任务ID:5002
//        taskService.complete("5002");
//    }

     //zhangsan完成了自己的任务
    public static void main(String[] args) {

        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        Map<String, Object> map = new HashMap<String, Object>();
        Message message = new Message();
        message.setAudit("1");
        map.put("message",message);
        // 3.处理任务，结合当前用户任务列表的查询操作的话，任务ID:2505
        taskService.complete("52502",map);
    }

}
