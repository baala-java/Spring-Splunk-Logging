Spring-Splunk-Logging

A log4j append to store log data into Splunk by using Spring Framework

~~~~~ xml

<dependency>
			<groupId>org.springframework.data.splunk</groupId>
			<artifactId>Spring-Splunk-Logging</artifactId>
			<version>${spring.splunk.version}</version>
</dependency>

~~~~~


Put the following into your log4j.properties:

~~~~~ xml

log4j.rootLogger=DEBUG, Splunk

log4j.appender.Splunk=org.springframework.data.splunk.logging.log4j.SplunkAppender
log4j.appender.Splunk.host=${splunk_host}
log4j.appender.Splunk.port=8089
log4j.appender.Splunk.userName=admin
log4j.appender.Splunk.password=password
log4j.appender.Splunk.layout=org.apache.log4j.PatternLayout
log4j.appender.Splunk.layout.ConversionPattern=%d [%t] %-5p %c - %m%n

~~~~~


How to run the examples:

1. checkout the code:
$git clone https://github.com/leejianwei/Spring-Splunk-Logging.git

2. the path where Spring Splunk is stored local is ${spring.splunk.home}

3. change to ${spring.splunk.home}/samples/helloworld
$cd samples/helloworld

4. update "src/main/resources/log4j.properties". 
Change "log4j.appender.Splunk.host" in "log4j.properties" to the host where
Splunk is running.
Change port, userName, password accordingly to login Splunk 

5. run maven command
$mvn -Dspring.splunk.home=${spring.splunk.home} verify

6. In the Splunk server, search "Spring Splunk". 





