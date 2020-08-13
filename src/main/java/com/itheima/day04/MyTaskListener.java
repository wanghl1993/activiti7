package com.itheima.day04;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyTaskListener implements TaskListener {
    //注意选择时,必须是class
    public void notify(DelegateTask delegateTask) {
        //这里指定负责人
        delegateTask.setAssignee("张三");
    }

}
