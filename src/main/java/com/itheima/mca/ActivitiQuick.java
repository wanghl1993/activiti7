package com.itheima.mca;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

public class ActivitiQuick {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    private String bpmnNameAndKey = "first";

    @Test
    public void createTable(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("processes/first.bpmn")
                .key(bpmnNameAndKey)
                .name(bpmnNameAndKey)
                .category("HR")
                .deploy();
        System.out.println("流程部署ID\t" + deploy.getId());
        System.out.println("流程keyID\t" + deploy.getKey());
        System.out.println("流程名称ID\t" + deploy.getName());
        System.out.println("流程分类ID\t" + deploy.getCategory());
    }

    /**
     *  启动一个流程实例
     */
    @Test
    public void start(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.
                startProcessInstanceByKey(bpmnNameAndKey);

        System.out.println("流程实例ID\t"+processInstance.getId());
        System.out.println("流程定义ID\t"+processInstance.getProcessDefinitionId());
        System.out.println("流程定义key\t"+processInstance.getProcessDefinitionKey());
        System.out.println("流程部署对象ID\t"+processInstance.getDeploymentId());
    }

    /* 第三步 查找个人待办任务列表 */

    @Test
    public void findMyTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        taskService = processEngine.getTaskService();

        String assignee = "李四";
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();

        if(!CollectionUtils.isEmpty(list)){
            for (Task task : list) {
                System.out.println("任务ID\t" + task.getId());
                System.out.println("任务名称\t" + task.getName());
                System.out.println("任务执行人\t" + task.getAssignee());
            }
        }
    }

    /* 第四步: 执行任务 */
    @Test
    public void complte(){
        String taskId = "70002";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        taskService = processEngine.getTaskService();
        taskService.complete(taskId);

        System.out.println("任务执行完成");
    }

    /* 第五步: 查看历史流程实例 */
    @Test
    public void findhistProcessInstance(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        historyService = processEngine.getHistoryService();
        List<HistoricProcessInstance> list = this.historyService
                .createHistoricProcessInstanceQuery()
                .processDefinitionKey(bpmnNameAndKey)
                .list();
        if(!CollectionUtils.isEmpty(list)){
            for (HistoricProcessInstance historicProcessInstance : list) {
                System.out.println("业务系统key\t" + historicProcessInstance.getBusinessKey());
                System.out.println("部署对象ID\t" + historicProcessInstance.getDeploymentId());
                System.out.println("执行时长\t" + historicProcessInstance.getDurationInMillis());
                System.out.println("流程定义key\t" + historicProcessInstance.getProcessDefinitionId());
                System.out.println("流程定义名称\t" + historicProcessInstance.getProcessDefinitionName());
            }
        }
    }

    /* 查看历史任务 */
    @Test
    public void findHisTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        historyService = processEngine.getHistoryService();

        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .list();

        if(!CollectionUtils.isEmpty(list)){
            for (HistoricTaskInstance historicTaskInstance : list) {
                System.out.println("任务执行人\t" + historicTaskInstance.getAssignee());
                System.out.println("任务名称\t" + historicTaskInstance.getName());
                System.out.println("任务ID\t" + historicTaskInstance.getId());
                System.out.println("流程实例ID\t" + historicTaskInstance.getProcessInstanceId());
                System.out.println("********");
            }
        }

    }
}
