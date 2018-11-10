package com.phn.action;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.phn.util.BackupAndRestoreUtil;
import com.phn.util.DateUtil;
@SuppressWarnings("all")
public class BackupAndRestoreAction extends ActionSupport {
	//service需要生成set方法
	private File sqlfile;
	private String sqlfileContentType;
	private String sqlfileFileName;
	public String backup() throws Exception {
		BackupAndRestoreUtil.backup();
		//以时间为名称，后缀名为.sql文件
		String msg01="数据库文件已经在e盘备份成功";
		ActionContext.getContext().put("msg01", msg01);
		return "backupandrestore";
	}
	public String restore() throws Exception{
		//相对tomcat的临时文件的目录
		//String path = sqlfile.getPath();
		String msg01="所指定的数据库文件已经恢复成功";
		BackupAndRestoreUtil.restore("e:\\", sqlfileFileName);
		System.out.println(sqlfileContentType);
		//System.out.println(sqlfileFileName);
		ActionContext.getContext().put("msg01", msg01);
		//System.out.println("结束");
		return "backupandrestore";
	}
	/*public static void backup2() {
        try {
            Runtime rt = Runtime.getRuntime();
            String execStr ="mysqldump -uroot -p123456 luntan";
            // 调用 调用mysql的安装目录的命令
            Process child = rt.exec(execStr);
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
            String currentString =DateUtil.getOrderNum();
            String path="e:\\"+currentString+".sql";
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream(path);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
            System.out.println("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
	public File getSqlfile() {
		return sqlfile;
	}
	public void setSqlfile(File sqlfile) {
		this.sqlfile = sqlfile;
	}
	public String getSqlfileContentType() {
		return sqlfileContentType;
	}
	public void setSqlfileContentType(String sqlfileContentType) {
		this.sqlfileContentType = sqlfileContentType;
	}
	public String getSqlfileFileName() {
		return sqlfileFileName;
	}
	public void setSqlfileFileName(String sqlfileFileName) {
		this.sqlfileFileName = sqlfileFileName;
	}
}
