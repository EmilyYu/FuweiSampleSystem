package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.SampleDAO;
import com.fuwei.DAO.UnPricedSampleDAO;
import com.fuwei.entity.Sample;
import com.fuwei.entity.UnPricedSample;

public class AddPriceToSampleServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		try {
			String detail = req.getParameter("detail");
			double price = Double.valueOf(req.getParameter("price"));
			int id = Integer.valueOf(req.getParameter("id"));
			UnPricedSampleDAO unPricedSampleDAO = new UnPricedSampleDAO();
			UnPricedSample unPricedSample = unPricedSampleDAO
					.getUnPricedSampleBYId(id);
			if (unPricedSample != null) {
				Sample sample = new Sample();
				sample.setCost(price);
				sample.setDate(unPricedSample.getDate());
				sample.setDetail(detail);
				sample.setDeveloperId(unPricedSample.getDeveloperId());
				sample.setMaterial(unPricedSample.getMaterial());
				sample.setPicturePath(unPricedSample.getPicturePath());
				sample.setProductNumber(unPricedSample.getProductNumber());
				sample.setSize(unPricedSample.getSize());
				sample.setWeight(unPricedSample.getWeight());
				sample.setMachine(unPricedSample.getMachine());
				sample.setMemo(unPricedSample.getMemo());
				SampleDAO sampleDAO=new SampleDAO();
				System.out.println(sample.getDetail());
				int sampleId=sampleDAO.addSample(sample);
				unPricedSampleDAO.deleteUnPricedSampleById(id);
				JSONObject jObject = new JSONObject();
				jObject.put("OK", true);
				jObject.put("sample_id", sampleId);
				PrintWriter printWriter = resp.getWriter();
				printWriter.write(jObject.toString());
				printWriter.flush();
				printWriter.close();
			}else {
				JSONObject jObject = new JSONObject();
				jObject.put("OK", false);
				PrintWriter printWriter = resp.getWriter();
				printWriter.write(jObject.toString());
				printWriter.flush();
				printWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			JSONObject jObject = new JSONObject();
			jObject.put("OK", false);
			jObject.put("message", e.getMessage());
			PrintWriter printWriter = resp.getWriter();
			printWriter.write(jObject.toString());
			printWriter.flush();
			printWriter.close();
		}

	}

}
