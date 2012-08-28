/*
 * Copyright 2011-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.splunk.samples;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.splunk.model.SplunkServer;

/**
 * @author Jarred Li
 *
 */
public class Test {

	private Logger logger = Logger.getLogger(Test.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/context.xml");
		SplunkServer server = ctx.getBean(SplunkServer.class);
		System.out.println("host:" + server.getHost());
		
		Test test = ctx.getBean("test",Test.class);
		test.writeLog();
	}
	
	public void writeLog(){
		logger.info("Hello, Spring Splunk log4j Appender");
	}

}
