package org.readus.executescript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ExecuteScript.java
 * @Package
 * @Description: Java类中调用外部可执行脚本
 * @author: Carya
 * @date 2014-5-23 上午8:58:52
 */

public class ExecuteScript {

	//以Runtime.exec的方式调用脚本
	public void executeScript() {
		String scriptPath = Thread.currentThread().getContextClassLoader()
				.getResource("log_analysis.sh").getPath();
		String logPath = Thread.currentThread().getContextClassLoader()
				.getResource("log.txt").getPath();

		Runtime runtime = Runtime.getRuntime();
		String[] cmdarray = { "sh", scriptPath, logPath };
		BufferedReader br = null;
		BufferedReader bre = null;
		try {
			Process process = runtime.exec(cmdarray);
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			StringBuffer linesBuffer = new StringBuffer();
			String line = null;
			while (null != (line = br.readLine())) {
				linesBuffer.append(line).append("\n");
			}
			System.out.println("get output: \n" + linesBuffer.toString());

			bre = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			StringBuffer errorBuffer = new StringBuffer();
			String errorline = null;
			while (null != (errorline = bre.readLine())) {
				errorBuffer.append(errorline).append("\n");
			}
			System.out.println("get error output: \n" + errorBuffer.toString());

			int exitValue = process.waitFor();
			if (0 == exitValue) {
				System.out.println("execute script success.");
			} else {
				System.out.println("execute script failed: exitValue = "
						+ exitValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("execute script failed:" + e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("execute script failed:" + e);
		} finally {
			if (null != br) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != bre) {
				try {
					bre.close();
					bre = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//以ProcessBuilder.start方式调用脚本
	public void executeScript1() {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		String scriptPath = classLoader.getResource("log_analysis.sh")
				.getPath();
		String logPath = classLoader.getResource("log.txt").getPath();

		List<String> commandsList = new ArrayList<String>();
		commandsList.add("sh");
		commandsList.add(scriptPath);
		commandsList.add(logPath);
		ProcessBuilder processBuilder = new ProcessBuilder(commandsList);

		BufferedReader br = null;
		BufferedReader bre = null;
		try {
			Process process = processBuilder.start();

			try {
				br = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				StringBuffer linesBuffer = new StringBuffer();
				String line = null;
				while (null != (line = br.readLine())) {
					linesBuffer.append(line).append("\n");
				}
				System.out.println("get output: \n" + linesBuffer.toString());

				bre = new BufferedReader(new InputStreamReader(
						process.getErrorStream()));
				StringBuffer errorBuffer = new StringBuffer();
				String errorline = null;
				while (null != (errorline = bre.readLine())) {
					errorBuffer.append(errorline).append("\n");
				}
				System.out.println("get error output: \n"
						+ errorBuffer.toString());

				int exitValue = process.waitFor();
				if (0 == exitValue) {
					System.out.println("execute script success.");
				} else {
					System.out.println("execute script failed: exitValue = "
							+ exitValue);
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("execute script failed:" + e);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("execute script failed:" + e);
			} finally {
				if (null != br) {
					try {
						br.close();
						br = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (null != bre) {
					try {
						bre.close();
						bre = null;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//测试ProcessBuilder的redirectErrorStream属性设置
	public void executeScript2() {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		String scriptPath = classLoader.getResource("log_analysis.sh")
				.getPath();
		String logPath = classLoader.getResource("log.txt").getPath();

		List<String> commandsList = new ArrayList<String>();
		commandsList.add("sh");
		commandsList.add(scriptPath);
		commandsList.add(logPath);
		ProcessBuilder processBuilder = new ProcessBuilder(commandsList);
		processBuilder.redirectErrorStream(true);
		BufferedReader br = null;
		try {
			Process process = processBuilder.start();

			try {
				br = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				StringBuffer linesBuffer = new StringBuffer();
				String line = null;
				while (null != (line = br.readLine())) {
					linesBuffer.append(line).append("\n");
				}
				System.out.println("get output: \n" + linesBuffer.toString());

				int exitValue = process.waitFor();
				if (0 == exitValue) {
					System.out.println("execute script success.");
				} else {
					System.out.println("execute script failed: exitValue = "
							+ exitValue);
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("execute script failed:" + e);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("execute script failed:" + e);
			} finally {
				if (null != br) {
					try {
						br.close();
						br = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		ExecuteScript executeScript = new ExecuteScript();
//		executeScript.executeScript();
//		executeScript.executeScript1();
		executeScript.executeScript2();
	}

}
