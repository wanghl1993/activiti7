package com.itheima.day05;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 *  组任务的测试
 *  act_ru_identitylink 候选人时候type类型会是 participant
 */
public class GroupTest {

    // 8. 任务交接(与归还只是设置的值不一样),前提要保证当前用户是这个任务的负责人(act_hi_actinst)assignee = "zhangsan"时才可以交接,
    // 这时候他才可以有权限去将任务交接给其他候选人
    /*public static void main(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.设置一些参数,流程定义的key,候选用户
        String key = "myProcess_1";
        String assignee = "zhangsan";

        // 4.执行查询
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee(assignee) // 设置任务的负责人
                .singleResult();

        // 5.判断是否有任务
        if(task != null) {
            //交接任务为lisi, 交接任务就是一个候选人拾取用户的过程
            taskService.setAssignee(task.getId(), "lisi");
            System.out.println("交接任务完成~!");
        }

    }*/

    // 7.当前用户完成自己的任务
    /*
             zhangsan
             候选人查询(只是查询,对于拾取任务意义不大)(taskCandidateUser) -> 拾取(claim(task.getId(), candidate_users)) ->
             设置负责人(taskAssignee) -> 任务执行完毕(complete)
     */
    public static void main(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.设置一些参数,流程定义的key,候选用户
        String key = "myProcess_1";
        String assignee = "lisi";

        // 4.执行查询
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee(assignee) // 设置任务的负责人
                .singleResult();

        // 5.执行当前的任务
        if(task != null){
            taskService.complete(task.getId());
            System.out.println("任务执行完毕!");
        }

    }

    // 6.当前的用户查询自己的任务
    // 4.查询候选用户的组任务
    /*public static void main(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.设置一些参数,流程定义的key,候选用户
        String key = "myProcess_1";
        String assignee = "zhangsan";

        // 4.执行查询
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee(assignee) // 设置任务的负责人
                .list();

        // 5.输出
        for (Task task : list ) {
            System.out.println(task.getProcessInstanceId());
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getAssignee()); //为null,说明当前的zhangsan只是一个候选人,不是任务的执行人
        }

    }*/

    // 5.测试zhangsan用户,来拾取组任务
    // 拾取任务的过程就是将候选用户转化为真正的负责人 （让任务的assignee有值）
    /*public static void main(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.设置一些参数,流程定义的key,候选用户
        String key = "myProcess_1";
        String candidate_users = "zhangsan";

        // 4.执行查询
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskCandidateUser(candidate_users) // 设置候选用户查询
                .singleResult();
        if(task != null){
            // 如果设置为null,归还组任务,该任务没有负责人
//            taskService.setAssignee(task.getId(),null);
            taskService.claim(task.getId(), candidate_users); //第一个参数任务ID，第二个为具体候选人的名称
            System.out.println("任务拾取完毕!");
        }

        // 5.输出

    }*/

    // 4.查询候选用户的组任务
    public static void main1(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.设置一些参数,流程定义的key,候选用户
        String key = "myProcess_1";
        String candidate_users = "zhangsan";

        // 4.执行查询
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskCandidateUser(candidate_users) // 设置候选用户查询
                .list();

        // 5.输出
        for (Task task : list ) {
            System.out.println(task.getProcessInstanceId());
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getAssignee()); //为null,说明当前的zhangsan只是一个候选人,不是任务的执行人
        }

    }

    // 3.填写请假单的任务要执行完成
    public static void main5(String[] args) {

        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        // 3.查询当前用户的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee("xiaozhang")
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
    /*public static void main(String[] args) {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2.得到RunService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // 3.创建流程实例 流程定义的key需要知道 holiday
        ProcessInstance processInstance = runtimeService.
                startProcessInstanceByKey("myProcess_1");

        // 4.输出实例的相关信息
        System.out.println("流程定义ID" + processInstance.getProcessDefinitionId()); //holiday:1:4
        System.out.println("流程实例ID" + processInstance.getId()); //2501

    }*/

    // 1.部署流程定义
    public static void main2(String[] args) {
        // 1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday5.bpmn") //添加bpmn资源
//                .addClasspathResource("diagram/holiday2.png")
                .name("请假申请单流程")
                .deploy();

        // 4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }
}
