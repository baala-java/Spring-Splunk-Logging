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
package org.springframework.data.splunk.logging.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.springframework.data.splunk.SplunkDataWriter;
import org.springframework.data.splunk.model.SplunkServer;

/**
 * Appender which write data into Splunk. To use it, add the following into your "log4j.properties".
 * 
 * {@code
log4j.appender.Splunk=org.springframework.data.splunk.logging.log4j.SplunkAppender
log4j.appender.Splunk.host=${some_host}
log4j.appender.Splunk.port=8089
log4j.appender.Splunk.userName=admin
log4j.appender.Splunk.password=changeme
log4j.appender.Splunk.layout=org.apache.log4j.PatternLayout
log4j.appender.Splunk.layout.ConversionPattern=%d [%t] %-5p %c - %m%n

 * }
 * @author Jarred Li
 *
 */
public class SplunkAppender extends AppenderSkeleton {

	private String host;
	private int port;
	private String userName;
	private String password;


	private SplunkServer splunkServer;
	private SplunkDataWriter dataWriter;

	private boolean initialized = false;

	/**
	 * 
	 */
	public synchronized void init() {
		splunkServer = new SplunkServer();
		splunkServer.setHost(host);
		splunkServer.setPort(port);
		splunkServer.setUserName(userName);
		splunkServer.setPassword(password);
		dataWriter = new SplunkDataWriter(splunkServer);
		initialized = true;
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.Appender#close()
	 */
	@Override
	public void close() {
		dataWriter.close();
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.Appender#requiresLayout()
	 */
	@Override
	public boolean requiresLayout() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	protected void append(LoggingEvent event) {
		if (!initialized) {
			init();
		}
		dataWriter.write(super.getLayout().format(event));
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
