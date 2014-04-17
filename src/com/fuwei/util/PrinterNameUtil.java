package com.fuwei.util;

import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;

public class PrinterNameUtil {
	public static String getPrinterName(String name){
		HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet(); 

		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;  

		String printerName=null;
		//查找所有的可用的打印服务  

		PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, pras);  



		for (int i =0; i<printService.length ;i++ )
		{

			if(printService[i].getName().contains(name)){
				printerName=printService[i].getName();
			}

		}	
		
		return printerName;
	}
	
	public static List<String> getAllPrintName(){
		
		List<String> printNameList=new ArrayList<String>();
		HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet(); 
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;  
		//查找所有的可用的打印服务  
		PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, pras);  



		for (int i =0; i<printService.length ;i++ )
		{
				printNameList.add(printService[i].getName());
		}	
		
		return printNameList;
	}
}
