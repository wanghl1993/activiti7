package com.itheima.day03.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 *  需求:
 *  1.从Activiti的act_ge_bytearray表中读取两个资源文件
 *  2.将俩个资源文件保存到路径: G:\\\目录下
 *
 *  技术方案:
 *      1.第一种方式使用activiti api来实现
 *      2.第二种方式:其实就是原理层面,可以使用jdbc的对blob类型、clob类型数据的读取,并保存
 *      3.IO流转换,最好commons-io.jar
 *
 *  真实应用场景:用户想查看这个请假流程具体有哪些步骤要走?
 */
public class QueryBpmnFile {

    public static void main(String[] args) throws IOException {
        // 1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.得到RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3.得到查询器: ProcessDefinitionQuery对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // 4.设置查询条件
        processDefinitionQuery.processDefinitionKey("holiday");//参数是流程定义的key
        // 5.执行查询操作,查询出想要的流程定义
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        // 6.通过流程定义信息,得到部署ID
        String deploymentId = processDefinition.getDeploymentId();
        // 7.通过repositoryService的方法,实现读取图片信息及bpmn文件信息(输入流)
        // getResourceAsStream()参数说明: 第一个参数部署id,第二个参数资源名称
        // processDefinition.getDiagramResourceName() 代表获取png图片资源的名称
        // processDefinition.getResourceName() 代表获取bpmn文件的名称
        InputStream pngIos = repositoryService.getResourceAsStream(deploymentId
                ,processDefinition.getDiagramResourceName());
        InputStream bpmsIos = repositoryService.getResourceAsStream(deploymentId
                ,processDefinition.getResourceName());
        // 8.构建出OutputStream
        OutputStream pngOs =
                new FileOutputStream("E:\\photo\\" + processDefinition.getDiagramResourceName());

        OutputStream bpmsOs =
                new FileOutputStream("E:\\photo\\" + processDefinition.getResourceName());

        // 9.输入流,输出流的转换 commons-io-xx.jar中的方法
        IOUtils.copy(pngIos,pngOs);
        IOUtils.copy(bpmsIos,bpmsOs);
        // 10.关闭流
        pngIos.close();
        bpmsIos.close();

        pngOs.close();
        bpmsOs.close();
    }

}
