/**     
* @Description: TODO(用一句话描述该文件做什么)   
* @author: Carya   
* @date: May 25, 2014 5:15:04 PM
* @version: V1.0     
*/
package org.readus.executescript;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class StartDaemon implements ServletContextAware {
	
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public void startDaemon() {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		//假定log_analysis.sh启动的是一个长时间运行的Process
		String scriptPath = classLoader.getResource("log_analysis.sh")
				.getPath();
		String logPath = classLoader.getResource("log.txt").getPath();

		List<String> commandsList = new ArrayList<String>();
		commandsList.add("sh");
		commandsList.add(scriptPath);
		commandsList.add(logPath);
		ProcessBuilder processBuilder = new ProcessBuilder(commandsList);
		try {
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			servletContext.setAttribute("daemon", process);
			//读取一个长时间运行的进程的输入输出流很可能造成当前线程block在此处
//			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			StringBuffer stringBuffer = new StringBuffer();
//			String line = null;
//			while (null != (line = br.readLine())) {
//				stringBuffer.append(line);
//			}
//			System.out.println("output of start daemon: " + stringBuffer.toString());
			
			int exitValue = process.waitFor();
			if (0 == exitValue) {
				System.out.println("execute script success.");
			} else {
				System.out.println("execute script failed: exitValue = "
						+ exitValue);
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
