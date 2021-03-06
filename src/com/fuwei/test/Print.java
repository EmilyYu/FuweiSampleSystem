package com.fuwei.test;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Print {
	//private static final String PRINT_NAME=new String("FX7AF20D"); 
	/**
	 * 说明
	 *  <p>（1）首先将JACOB的DLL拷贝至 C:\Windows\System32 中</p>
	 *  <p>（2）DCom Server Process Launcher（DcomLaunch）服务需要打开</p>
	 *  <p>（3）需要安装Microsoft office 2003+</p>
	 *  <p>打印属性参见 <a href="http://msdn.microsoft.com/zh-cn/library/office/ff838253.aspx" target="_blank">PrintOut 方法 (Excel)</a>
	 * @param path 打印路径地址，形如 \\XX\\YY.xls
	 * @param copies 打印份数
	 */
	public static void printExcel(String path,int copies){
		if(path.length() <= 0||copies<1){
			return;
		}
		//初始化COM线程
		ComThread.InitSTA();
		//新建Excel对象
		ActiveXComponent xl=new ActiveXComponent("Excel.Application");
		try { 
			System.out.println("Version=" + xl.getProperty("Version"));
			//设置是否显示打开Excel  
			Dispatch.put(xl, "Visible", new Variant(true));
			//打开具体的工作簿
			Dispatch workbooks = xl.getProperty("Workbooks").toDispatch(); 
			Dispatch excel=Dispatch.call(workbooks,"Open",System.getProperty("user.dir")+path).toDispatch(); 
			String printNameString="\\\\Fuweiserver\\HP LaserJet Professional M1212nf MFP";
			//设置打印属性并打印
			Dispatch.callN(excel,"PrintOut",new Object[]{Variant.VT_MISSING, Variant.VT_MISSING, new Integer(copies),
					new Boolean(false),/*PRINT_NAME*/printNameString, new Boolean(true),Variant.VT_MISSING, ""});
			
			//关闭文档
			Dispatch.call(excel, "Close", new Variant(false));  
		} catch (Exception e) { 
			e.printStackTrace(); 
		} finally{
			//xl.invoke("Quit",new Variant[0]);
			//始终释放资源 
			ComThread.Release(); 
		} 
	}

	public static void main(String[] args) {
		Print.printExcel("e://3.xls", 1);
	}
	
}
