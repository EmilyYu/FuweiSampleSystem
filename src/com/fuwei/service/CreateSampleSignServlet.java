package com.fuwei.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.SampleDAO;
import com.fuwei.DAO.UnPricedSampleDAO;
import com.fuwei.entity.Sample;
import com.fuwei.entity.UnPricedSample;
import com.fuwei.util.ExportExcel;
import com.fuwei.util.PrintExcel;
import com.fuwei.util.UploadImageName;

public class CreateSampleSignServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		Enumeration enuma=req.getParameterNames();
		String type=null;
		try{
			while (enuma.hasMoreElements()) {
				String parameterName = (String) enuma.nextElement();
				if (parameterName.equals("type")) {
					type=req.getParameter("type");
				}
			}
			List<UnPricedSample> unPricedSampleList = null;
			if(type==null){
				Set<Integer> idSet = new HashSet<Integer>();
				String idString = req.getParameter("id");
				String[] ids = idString.split(",");
				
				for (String id : ids) {
					int tempId = Integer.valueOf(id);
					if (!idSet.contains(tempId)) {
						idSet.add(tempId);
					}
				}
				UnPricedSampleDAO unPricedSampleDAO = new UnPricedSampleDAO();
				unPricedSampleList = (List<UnPricedSample>) unPricedSampleDAO
						.getUnPricedSampleByIDSET(idSet);
			}else {
				int id=Integer.valueOf(req.getParameter("id"));
				SampleDAO sampleDAO=new SampleDAO();
				Sample sample=sampleDAO.getSample(id);
				UnPricedSample unPricedSample=new UnPricedSample();
				unPricedSample.setDate(sample.getDate());
				unPricedSample.setDeveloperId(sample.getDeveloperId());
				unPricedSample.setMachine(sample.getMachine());
				unPricedSample.setMaterial(sample.getMaterial());
				unPricedSample.setProductNumber(sample.getProductNumber());
				unPricedSample.setSize(sample.getSize());
				unPricedSample.setWeight(sample.getWeight());
				unPricedSample.setId(sample.getId());
				unPricedSample.setPicturePath(sample.getPicturePath());
				unPricedSampleList=new ArrayList<UnPricedSample>();
				unPricedSampleList.add(unPricedSample);
			}
			
			
			UploadImageName uploadImageName = new UploadImageName();
			String suijiNameString = uploadImageName.getIPTimeRand();
			
			ExportExcel.exportSampleSignExcel(unPricedSampleList,this.getServletContext().getRealPath("/") + "excel/", suijiNameString+".xls");
			new Thread(new printThread(this.getServletContext().getRealPath("/") + "excel/"+suijiNameString+".xls")).start();
			
			JSONObject jObject = new JSONObject();
			jObject.put("OK", true);
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
			
		}catch(Exception e){
			JSONObject jObject = new JSONObject();
			jObject.put("OK", false);
			jObject.put("message", e.getMessage());
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		}
	}
	
	class printThread implements Runnable{
		private String filePath;
		public printThread(String filePath){
			this.filePath=filePath;
		}
		public void run() {
			PrintExcel.printExcel(filePath);
			File file=new File(filePath);
			if(file.exists()){
				file.delete();
			}
		}
		
	}

}
