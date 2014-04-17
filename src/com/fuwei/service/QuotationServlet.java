package com.fuwei.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fuwei.DAO.QuotationDAO;
import com.fuwei.entity.Quotation;
import com.fuwei.util.FuweiSystemData;

public class QuotationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		int operationType = -1;
		Enumeration enuma = req.getParameterNames();
		int companyPriceID = -1;
		Set<Integer> ids = null;
		while (enuma.hasMoreElements()) {
			String parameterName = (String) enuma.nextElement();
			if (parameterName.equals("operationType")) {
				operationType = Integer
						.valueOf(req.getParameter(parameterName));
				System.out.println("operationType:" + operationType);
			} else if (parameterName.equals("companyPriceID")) {
				companyPriceID = Integer.valueOf(req
						.getParameter(parameterName));
			} else if (parameterName.equals("quotationIDS")) {

				String idString = req.getParameter(parameterName);
				String[] idStrings = idString.split(",");

				if (idStrings.length > 0) {
					ids = new HashSet<Integer>();
					for (String string : idStrings) {

					}
				}
			}

		}

		switch (operationType) {
		case FuweiSystemData.ADD_QUOTATION: {
			System.out.println("ADD_QUOTATION");
			System.out.println("companyPriceID:" + companyPriceID);
			if (companyPriceID > -1) {
				try {
					QuotationDAO quotationDAO = new QuotationDAO();
					List list = (List) quotationDAO
							.getQuotationByCompanyPriceID(companyPriceID);
					if (list.size() < 1) {
						Quotation quotation = new Quotation();
						quotation.setCompanyPriceID(companyPriceID);
						quotation.setCreateTime(new Date());

						int result = quotationDAO.addQuotation(quotation);
					}
					System.out.println();
					JSONObject jObject = new JSONObject();
					jObject.put("OK", true);
					PrintWriter printWriter = resp.getWriter();
					printWriter.write(jObject.toString());
					printWriter.flush();
					printWriter.close();
				} catch (Exception e) {
					System.out.println("报错");
					JSONObject jObject = new JSONObject();
					jObject.put("OK", false);
					jObject.put("message", e.getMessage());
					PrintWriter printWriter = resp.getWriter();
					printWriter.write(jObject.toString());
					printWriter.flush();
					printWriter.close();
				}

				System.out.println("add完成");
			}

		}

			break;
		case FuweiSystemData.DELETE_QUOTATION: {
			if (ids != null && ids.size() > 0) {
				QuotationDAO quotationDAO = new QuotationDAO();
				int result = quotationDAO.deleteQutationWithCompanyPriceIDSet(ids);
				System.out.println("result:" + result);
				req.setAttribute("note", result);
				req.getRequestDispatcher("showQuotationList.jsp").forward(req,
						resp);
			}
		}
			break;

		case FuweiSystemData.ALL_QUOTATION: {
			QuotationDAO quotationDAO = new QuotationDAO();
			List<Quotation> quotations = (List<Quotation>) quotationDAO
					.getAllQuotation();
			if (quotations != null) {
				req.setAttribute("quotations", quotations);
			} else {
				req.setAttribute("quotations", "");
			}
			req.getRequestDispatcher("showQuotationList.jsp")
					.forward(req, resp);
		}

		default:
			break;
		}

		System.out.println("servlet结束");
	}

}
