package com.itheima.entity;

import com.itheima.day04.Holiday;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

public class MessageTest {

    public static void main(String[] args) {
        // 1.得到ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到RuntimeService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.查询当前用户是否有任务
//        String key = "messagex";
//        Task task = taskService.createTaskQuery().processDefinitionKey(key)
//                .taskAssignee("1").singleResult();

        Map<String, Object> map = new HashMap<String, Object>();

        //初始化一些参数
        Message message = new Message();
        message.setAudit("1");
        map.put("message",message);

        // 4.判断task != null,说明当前用户有任务
        if(true) {
            taskService.complete("42505",map); //完成任务时,设置流程变量的值
            System.out.println("任务执行完毕");
        }



    }

}
