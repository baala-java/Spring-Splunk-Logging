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
package org.springframework.data.splunk;

import java.util.HashMap;
import java.util.Map;
import com.splunk.Receiver;
import com.splunk.Service;
import org.springframework.data.splunk.model.SplunkServer;

/**
 * @author Jarred Li
 *
 */
public class SplunkDataWriter {

	private Service service;

	private Receiver receiver;



	public SplunkDataWriter(Map<String, Object> args) {
		init(args);
	}

	public SplunkDataWriter(SplunkServer splunkServer) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("host", splunkServer.getHost());
		args.put("port", splunkServer.getPort());
		args.put("username", splunkServer.getUserName());
		args.put("password", splunkServer.getPassword());
		init(args);
	}

	/**
	 * @param args
	 */
	public void init(Map<String, Object> args) {
		service = Service.connect(args);
		if (service != null) {
			receiver = service.getReceiver();
		}

	}

	public void write(String data) {
		receiver.submit(data);
	}

	public void close(){
		service.logout();
	}
}
