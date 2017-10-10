package com.jumper.angel.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解(系统操作日志)
 * 两个参数 desc = "描述信息"
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-1-18
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
@Target({ElementType.METHOD}) 
@Retention(RetentionPolicy.RUNTIME)   
@Documented
public @interface DescribeAnnotation {
	
	/**
	 * 描述信息
	 * 自定义注解的属性，default是设置默认值
	 * @version 1.0
	 * @createTime 2017-1-18,下午3:38:55
	 * @updateTime 2017-1-18,下午3:38:55
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @return
	 */
	String desc() default "无操作信息";
}
