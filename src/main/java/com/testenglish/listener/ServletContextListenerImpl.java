package com.testenglish.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.testenglish.dao.ConnectionFactory;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		
		// Initialize connection pool
		ConnectionFactory.init(context.getInitParameter("jdbc_resource_name"));
		
		// Load dependencies
		Properties URLs = loadProperties(context.getRealPath(context.getInitParameter("urls_prop_file")));
		Properties webpages = loadProperties(context.getRealPath(context.getInitParameter("webpages_prop_file")));
		Properties messages = loadProperties(context.getRealPath(context.getInitParameter("messages_prop_file")));
		
		context.setAttribute("URLs", toMap(URLs));
		context.setAttribute("webpages", toMap(webpages));
		context.setAttribute("messages", toMap(messages));
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {}
	
	private Properties loadProperties(String filePath) {
		Properties prop = new Properties();
		try (
			FileInputStream inputStream = new FileInputStream(filePath);
		) {
			prop.load(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	private Map<String, String> toMap(Properties prop) {
		Map<String, String> propMap = new HashMap<>();
		for (String name : prop.stringPropertyNames()) {
			propMap.put(name, prop.getProperty(name));
		}
		return propMap;
	}
	
}