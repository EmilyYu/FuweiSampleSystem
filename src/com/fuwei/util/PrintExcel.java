package com.fuwei.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class PrintExcel {
	public static void printExcel(String path) {
		ComThread.InitSTA();
		ActiveXComponent xl = new ActiveXComponent("Excel.Application");
		try {
			// System.out.println("version=" + xl.getProperty("Version"));
			// 不打开文档
			Dispatch.put(xl, "Visible", new Variant(false));
			Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
			// 打开文档
			Dispatch excel = Dispatch.call(workbooks, "Open", path)
					.toDispatch();
			// 开始打印
			Dispatch.get(excel, "PrintOut");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 始终释放资源
			ComThread.Release();
		}
	}
}
