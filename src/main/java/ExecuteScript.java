import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**  
 * @Title: ExecuteScript.java  
 * @Package   
 * @Description: TODO(用一句话描述该文件做什么)  
 * @author: Carya 
 * @date 2014-5-23 上午8:58:52     
 */

/**  
 * @ClassName: ExecuteScript  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author: Carya
 *  
 */
public class ExecuteScript {
	
	public void executeScript() {
		Runtime runtime = Runtime.getRuntime();
		String[] cmdarray = {"cat", "lines.txt"};
		BufferedReader bf = null;
		try {
			Process process = runtime.exec(cmdarray);
			bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuffer linesBuffer = new StringBuffer();
			String line = null;
			while (null != (line = bf.readLine())) {
				linesBuffer.append(line);
			}
			
			System.out.println("get output: " + linesBuffer.toString());
			int exitValue = process.waitFor();
			if (0 == exitValue) {
				System.out.println("execute script success.");
			} else {
				System.out.println("execute script failed: exitValue = " + exitValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("execute script failed:" + e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("execute script failed:" + e);
		} finally {
			if (null != bf) {
				try {
					bf.close();
					bf = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**  
	 * @Title: main  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param args    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
