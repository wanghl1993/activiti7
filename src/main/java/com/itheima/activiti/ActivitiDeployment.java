package com.itheima.activiti;

import com.itheima.entity.Message;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 *  流程定义的部署
 *  activiti表有哪些?
 *
 *  act_re_deplayment 部署信息
 *  act_re_procdef    流程定义
 *  act_re_bytearray  流程定义的bpmn文件及png文件
 */
public class ActivitiDeployment {

    //流程定义部署
//    public static void main(String[] args) {
//        // 1.创建ProcessEngine对象
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        // 2.得到RepositoryService实例
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//
//
//        // 3.转化出ZipInputStream流对象
//        InputStream is = ActivitiDeployment.class.getClassLoader()
//                .getResourceAsStream("diagram/holidayBPMN.zip");
//
//        // 将 InputStream流 转化为 ZipInputStream
//        ZipInputStream zipInputStream = new ZipInputStream(is);
//
//        // 3.进行部署
//        Deployment deployment = repositoryService.createDeployment()
//                .addZipInputStream(zipInputStream)
//                .name("请假申请单流程")
//                .deploy();
//
//        // 4.输出部署的一些信息
//        System.out.println(deployment.getName());
//        System.out.println(deployment.getId());
//
//    }

    //流程定义部署
    public static void main(String[] args) {
        // 1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/message01.bpmn") //添加bpmn资源
//                .addClasspathResource("diagram/holiday2.png")
                .name("报文审批流程")
                .deploy();

        // 4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());

    }

}
