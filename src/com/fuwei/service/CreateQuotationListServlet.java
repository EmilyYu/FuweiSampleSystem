package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.CompanyPriceDAO;
import com.fuwei.DAO.QuotationDAO;
import com.fuwei.DAO.QuotationListDAO;
import com.fuwei.entity.CompanyPrice;
import com.fuwei.entity.QuotationList;
import com.fuwei.util.DateFormateUtil;
import com.fuwei.util.ExportExcel;
import com.fuwei.util.UploadImageName;

public class CreateQuotationListServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		try {
			String idString = req.getParameter("ids");
			String companyName = req.getParameter("companyName");
			String salesName = req.getParameter("salesName");
			System.out.println("ids:"+idString+"\ncompanyName:"+companyName+"\nsalesName:"+salesName);
			String[] ids = idString.split(",");
			Set<Integer> idSet = new HashSet<Integer>();
			for (String id : ids) {
				idSet.add(Integer.valueOf(id));
			}
			QuotationList quotationList = new QuotationList();
			quotationList.setCompanyName(companyName);
			quotationList.setSalesName(salesName);
			quotationList.setIdString(idString);
			quotationList.setTime(new Date());
			quotationList.setQuotationNumber("FW");
			UploadImageName uploadImageName = new UploadImageName();
			String suijiNameString=uploadImageName.getIPTimeRand();
			String fileName = this.getServletContext().getRealPath("/")
					+ "excel/" + suijiNameString + ".xls";
			CompanyPriceDAO companyPriceDAO = new CompanyPriceDAO();
			List<CompanyPrice> companyPriceList = (List<CompanyPrice>) companyPriceDAO
					.getCompanyPriceBYIDSET(idSet);
			
			quotationList.setExcelName(suijiNameString);

			QuotationListDAO quotationListDAO = new QuotationListDAO();
			quotationListDAO.addQuotation(quotationList);
			quotationList=(QuotationList)(quotationListDAO.getQuotationListById(quotationList.getId()));
			ExportExcel.exportExcel(fileName, companyPriceList, this
					.getServletContext().getRealPath("/")
					+ "image/", companyName, salesName, DateFormateUtil.formateDate(quotationList.getTime()),quotationList.getQuotationNumber());
			//生成详情报价表后 删除购物车中的相应报价
			new Thread(new deleteQuotationsByIdsetTHREAD(idString)).start();
			req.setAttribute("quotationList", quotationList);
			req.getRequestDispatcher("quotationListDetail.jsp").forward(req, resp);
		} catch (Exception e) {
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
	
	class deleteQuotationsByIdsetTHREAD implements Runnable{
		private String idString;
		public deleteQuotationsByIdsetTHREAD(String idString){
			this.idString=idString;
		}
		
		public void run() {
			String[] ids=idString.split(",");
			Set<Integer> idSet=new HashSet<Integer>();
			for (String id : ids) {
				idSet.add(Integer.valueOf(id));
			}
			QuotationDAO quotationDAO=new QuotationDAO();
			
			quotationDAO.deleteQutationWithCompanyPriceIDSet(idSet);
		}
		
	}

}
