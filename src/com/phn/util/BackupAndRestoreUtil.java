package com.phn.util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
public class BackupAndRestoreUtil {
	//备份数据到d盘，新的需要该相关的密码，备份数据库，备份的数据名为当前日期
	//需要配置相关的MYSQL_HOME这样方便任何的路径下的命令行可以调用
	public static void backup() {
        try {
            Runtime rt = Runtime.getRuntime();
            String dbname=PropertiesUtils.dbname();
            String username=PropertiesUtils.username();
            String password=PropertiesUtils.password();
            String execStr ="mysqldump -u"+username+" -p"+password+" "+dbname;
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
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public static void restore(String filePath,String fileName) {
        try {
            Runtime runtime = Runtime.getRuntime();
            String dbname=PropertiesUtils.dbname();
            String username=PropertiesUtils.username();
            String password=PropertiesUtils.password();
            String execStr ="mysql -u"+username+" -p"+password+" "+dbname;
            Process process = runtime
                    .exec(execStr);
            OutputStream outputStream = process.getOutputStream();
            String filepathAndName=filePath+fileName;
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filepathAndName), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,
                    "utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            br.close();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
