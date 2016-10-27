package com.shadow.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskDemo {

	public void executeA() {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行executeA任务");
	}
}
