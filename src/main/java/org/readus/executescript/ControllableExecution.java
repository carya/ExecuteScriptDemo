/**     
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author: Carya   
 * @date: May 25, 2014 10:36:24 PM
 * @version: V1.0     
 */
package org.readus.executescript;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ControllableExecution implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		String scriptPath = classLoader.getResource("daemon.sh")
				.getPath();
		List<String> commandsList = new ArrayList<String>();
		commandsList.add("sh");
		commandsList.add(scriptPath);
		commandsList.add("start");
		ProcessBuilder processBuilder = new ProcessBuilder(commandsList);
		try {
			Process process = processBuilder.start();
			//以下两行代码是关键
			ServletContext servletContext = sce.getServletContext();
			servletContext.setAttribute("daemon", process);
			int exitValue = process.waitFor();
			if (0 == exitValue) {
				
			} else {

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		String scriptPath = classLoader.getResource("daemon.sh")
				.getPath();
		List<String> commandsList = new ArrayList<String>();
		commandsList.add("sh");
		commandsList.add(scriptPath);
		commandsList.add("stop");
		ProcessBuilder processBuilder = new ProcessBuilder(commandsList);
		try {
			Process process = processBuilder.start();
			int exitValue = process.waitFor();
			if (0 == exitValue) {
				
			} else {

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
