package com.fuwei.test;

import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fuwei.entity.ProductionNotification;
import com.fuwei.util.ExportExcel;
import com.fuwei.util.StringTODate;
import com.fuwei.util.YaSuoPicture;

public class Test {
	public static void main(String[] args) {
		/*
		SampleDAO sampleDAO=new SampleDAO();
		Sample sample=sampleDAO.getSample(8);
		CompanyPriceDAO companyPriceDAO=new CompanyPriceDAO();
		CompanyPrice companyPrice=companyPriceDAO.getCompanyPrice(6);
		
		ExportExcel.exportSampleDetailExcel(sample,companyPrice, "e:/", "ee.xls");
		*/
//		ProductionNotification productionNotification=new ProductionNotification();
//		productionNotification.setCompanyName("轻纺");
//		productionNotification.setCreatTime(new Date());
//		productionNotification.setDeadlineTime(StringTODate.changeStringTODate("2014/01/01"));
//		productionNotification.setExceptProductQuantity(2310);
//		productionNotification.setFworderID(1);
//		productionNotification.setFworderNumber("J16173");
//		productionNotification.setId(0);
//		productionNotification.setJiagongdanwei("富伟");
//		productionNotification.setKezhong(230.1);
//		productionNotification.setMachineZhenXing("3针");
//		productionNotification.setNote("备注");
//		productionNotification.setNotificationNumber("FWA40001");
//		productionNotification.setPictureName("1.png");
//		productionNotification.setProductName("围脖");
//		productionNotification.setStyleNumber("款号");
//		JSONArray jsonArray=new JSONArray();
//		for (int i = 0; i < 4; i++) {
//			JSONObject jsonObject=new JSONObject();
//			jsonObject.put("sehao", "sehao"+i);
//			jsonObject.put("sebie", "sebie"+i);
//			jsonObject.put("size", "50*(50*2)"+i);
//			jsonObject.put("quantity", 1000+""+i);
//			jsonObject.put("materialName", "100%晴纶"+i);
//			jsonObject.put("materialQuantity", 100+""+i);
//			jsonObject.put("sunhao", 1.1+""+i);
//			jsonObject.put("totalMaterial", 110+""+i);
//			jsonArray.add(jsonObject);
//		}
//		productionNotification.setContentJSONString(jsonArray.toString());
//		ExportExcel.exportProductionNotification("e:/", "ee.xls",productionNotification,"e:/");
		//ExportExcel.exportKuaiDiDan("e:/", "kuaidi.xls", "胡盼", "1111", "111", "2222", "1111", "1111");
		//Print.printExcel("e:/20140309123353780813.xls", 1, PrinterNameUtil.getPrinterName("HP"));
		
		/*FWUser user=new FWUser();
		user.setAuthority(1);
		user.setChineseName("胡盼");
		user.setPassword("123456");
		user.setUserName("hp2");
		FWUserDAO fUserDAO=new FWUserDAO();
		fUserDAO.addUser(user);*/
		//ExportExcel.exportSampleSignExcel(null,"e:/","sample.xls");
	//	File file=new File("e://a.xls");
		
		
		/*UnPricedSampleDAO unPricedSampleDAO=new UnPricedSampleDAO();
		List<UnPricedSample> unList=(List<UnPricedSample>)unPricedSampleDAO.getAllUnPricedSample();
		UnPricedSample unPricedSample=unList.get(0);
		unPricedSample.setMaterial("一二三四五六七八九十一一二三四五六七八九");
		unPricedSample.setNote("一二三四五六七八九十一一二三四五六七八九");
		List<UnPricedSample> unTempList=new ArrayList<UnPricedSample>();
		unTempList.add(unPricedSample);
		ExportExcel.exportSampleSignExcel( unTempList,"e://","b.xls");*/
	}
	
	
	public void getYaSuoPicture() {
		String path="e:/";
		String pictureName="a.jpg";
		String pictureName2="b.jpg";
		YaSuoPicture yaSuoPicture = new YaSuoPicture();
		/*
		 * yaSuoPicture.compressPic(this.getServletContext().getRealPath("/") +
		 * "image", this.getServletContext().getRealPath("/") + "image/",
		 * pictureName, "s" + pictureName, 500, 500, true);
		 */

		yaSuoPicture.compressPic(path, path, pictureName, pictureName2,
				750, 750, true);
	}

}
