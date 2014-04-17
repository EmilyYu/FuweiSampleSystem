package com.fuwei.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.QuotationListDAO;
import com.fuwei.entity.QuotationList;
import com.fuwei.util.FuweiSystemData;
import com.fuwei.util.StringTODate;

public class SearchQuotationList extends HttpServlet {
	
	public static final int SEARCH_QUOTATIONLIST_BY_QUOTATIONLISTNUMBER=1;
	public static final int SEARCH_QUOTATIONLIST_BY_COMPANY=2;
	public static final int SEARCH_QUOTATIONLIST_BY_SALESMAN=3;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("utf-8");
			int page = FuweiSystemData.DEFAULT_PAGE;
			int perPage = FuweiSystemData.DEFAULT_PERPAGE;
			int type = FuweiSystemData.DEFAULT_SEARCH_TYPE;
			String aTime = null;
			String bTime = null;
			String key = null;
			Enumeration enuma = req.getParameterNames();
			while (enuma.hasMoreElements()) {
				String parameterName = (String) enuma.nextElement();
				if (parameterName.equals("page")) {
					page = Integer.valueOf(req.getParameter(parameterName));
				} else if (parameterName.equals("perPage")) {
					perPage = Integer.valueOf(req.getParameter(parameterName));
				} else if (parameterName.equals("type")) {
					type = Integer.valueOf(req.getParameter(parameterName));
				} else if (parameterName.equals("key")) {
					key = req.getParameter(parameterName);
				} else if (parameterName.equals("atime")) {
					aTime = req.getParameter(parameterName);
				} else if (parameterName.equals("btime")) {
					bTime = req.getParameter(parameterName);
				}
			}
			if(!StringTODate.canChangeStringTODate(aTime)){
				aTime=null;
			}
			System.out.println("hhhhhhhh");
			if(!StringTODate.canChangeStringTODate(bTime)){
				bTime=null;
			}
			System.out.println("key====="+key);
			if (key == null || FuweiSystemData.DEFAULT_SEARCH_TYPE == type||key.length()<1) {
				QuotationListDAO quotationListDAO = new QuotationListDAO();
				
				List<QuotationList> quotationList=null;
				
				if(aTime!=null&&bTime!=null){
					System.out.println("a===1");
					 int	totalQuotationListSize = quotationListDAO
						.getAllQuotationListBetweenTwoTimeCount(StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 quotationList=(List<QuotationList>)quotationListDAO.getAllQuotationListBetweenTwoTime(StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, "", quotationList,aTime,bTime);
				}else if (aTime==null&&bTime!=null) {
					System.out.println("a===2");
					int totalQuotationListSize = quotationListDAO
					.getAllQuotationListAfterTimeCount(StringTODate.changeStringTODate(bTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 quotationList=(List<QuotationList>)quotationListDAO.getAllQuotationListAfterTime(StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, "", quotationList,"",bTime);
				}else if (aTime!=null&&bTime==null) {
					System.out.println("a===3");
					int totalQuotationListSize = quotationListDAO
					.getAllQuotationListBeforeTimeCount(StringTODate.changeStringTODate(aTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 quotationList=(List<QuotationList>)quotationListDAO.getAllQuotationListBeforeTime(StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);

					 redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, "", quotationList,aTime,"");
				}else {
					System.out.println("a===4");
					int totalQuotationListSize = quotationListDAO
					.getAllQuotationListCount();
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					quotationList=(List<QuotationList>)quotationListDAO.getAllQuotationList((page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, "", quotationList,"","");
				}

			}else if (SearchQuotationList.SEARCH_QUOTATIONLIST_BY_COMPANY==type) {
				QuotationListDAO quotationListDAO = new QuotationListDAO();
				List<QuotationList> quotationList=null;
				if (aTime==null&&bTime!=null) {
					int totalQuotationListSize = quotationListDAO
					.getQuotationListByCompanyAfterTimeCount(key,StringTODate.changeStringTODate(bTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 quotationList=(List<QuotationList>)quotationListDAO.getQuotationListByCompanyAfterTime(key,StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,"",bTime);
				}else if (aTime!=null&&bTime==null) {
					int totalQuotationListSize = quotationListDAO
					.getQuotationListByCompanyBeforeTimeCount(key,StringTODate.changeStringTODate(aTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					quotationList=(List<QuotationList>)quotationListDAO.getQuotationListByCompanyBeforeTime(key,StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);

					redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,aTime,"");
				}else if (aTime!=null&&bTime!=null) {
					 int	totalQuotationListSize = quotationListDAO
						.getQuotationListCountByCompanyBetweenTwoTime(key,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 quotationList=(List<QuotationList>)quotationListDAO.getQuotationListByCompanyBetweenTwoTime(key,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,aTime,bTime);
				}else {//atime=null   btime=null;
					quotationListDAO=new QuotationListDAO();
					int totalQuotationListSize = quotationListDAO
					.getQuotationListCountByCompanyWithoutTime(key);
					int totalPage = (totalQuotationListSize +perPage - 1) / perPage;
					quotationList=(List<QuotationList>)quotationListDAO.getQuotationListByCompanyWithoutTime(key,(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,"","");
				}
			}else if(SearchQuotationList.SEARCH_QUOTATIONLIST_BY_QUOTATIONLISTNUMBER==type){
				QuotationListDAO quotationListDAO = new QuotationListDAO();
				List<QuotationList> quotationList=null;
				if (aTime==null&&bTime!=null) {
					int totalQuotationListSize = quotationListDAO
					.getQuotationListCountByQuotationListNumberAfterTime(key,StringTODate.changeStringTODate(bTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 quotationList=(List<QuotationList>)quotationListDAO.getQuotationListByQuotationListNumberAfterTime(key,StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,"",bTime);
				}else if (aTime!=null&&bTime==null) {
					int totalQuotationListSize = quotationListDAO
					.getQuotationListCountByQuotationListNumberBeforeTime(key,StringTODate.changeStringTODate(aTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					quotationList=(List<QuotationList>)quotationListDAO.getQuotationListByQuotationListNumberBeforeTime(key,StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);

					redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,aTime,"");
				}else if (aTime!=null&&bTime!=null) {
					 int	totalQuotationListSize = quotationListDAO
						.getQuotationListCountByQuotationListNumberBetweenTwoTime(key,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 quotationList=(List<QuotationList>)quotationListDAO.getQuotationListByQuotationListNumberBetweenTwoTime(key,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,aTime,bTime);
				}else {//atime=null   btime=null;
					quotationListDAO=new QuotationListDAO();
					int totalQuotationListSize = quotationListDAO
					.getQuotationListCountByQuotationListNumberWithoutTime(key);
					int totalPage = (totalQuotationListSize +perPage - 1) / perPage;
					quotationList=(List<QuotationList>)quotationListDAO.getQuotationListByQuotationListNumberWithoutTime(key,(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,"","");
				}
				
			}else if (SearchQuotationList.SEARCH_QUOTATIONLIST_BY_SALESMAN==type) {
				
				QuotationListDAO quotationListDAO = new QuotationListDAO();
				List<QuotationList> quotationList=null;
				if (aTime==null&&bTime!=null) {
					int totalQuotationListSize = quotationListDAO
					.getQuotationListCountBySalesmanAfterTime(key,StringTODate.changeStringTODate(bTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 quotationList=(List<QuotationList>)quotationListDAO.getQuotationListBySalesmanAfterTime(key,StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,"",bTime);
				}else if (aTime!=null&&bTime==null) {
					int totalQuotationListSize = quotationListDAO
					.getQuotationListCountBySalesmanBeforeTime(key,StringTODate.changeStringTODate(aTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					quotationList=(List<QuotationList>)quotationListDAO.getQuotationListBySalesmanBeforeTime(key,StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);

					redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,aTime,"");
				}else if (aTime!=null&&bTime!=null) {
					 int	totalQuotationListSize = quotationListDAO
						.getQuotationListCountBySalesmanBetweenTwoTime(key,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
					int totalPage = (totalQuotationListSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 quotationList=(List<QuotationList>)quotationListDAO.getQuotationListBySalesmanBetweenTwoTime(key,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,aTime,bTime);
				}else {//atime=null   btime=null;
					quotationListDAO=new QuotationListDAO();
					int totalQuotationListSize = quotationListDAO
					.getQuotationListCountBySalesmanWithoutTime(key);
					int totalPage = (totalQuotationListSize +perPage - 1) / perPage;
					quotationList=(List<QuotationList>)quotationListDAO.getQuotationListBySalesmanWithoutTime(key,(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalQuotationListSize, perPage, type, key, quotationList,"","");
				}
				
			}else {
				QuotationListDAO quotationListDAO=new QuotationListDAO();
				int totalQuotationListSize = quotationListDAO
				.getAllQuotationListCount();
				int totalPage = (totalQuotationListSize + FuweiSystemData.DEFAULT_PERPAGE - 1) / FuweiSystemData.DEFAULT_PERPAGE;
				
				List<QuotationList> quotationList=(List<QuotationList>)quotationListDAO.getAllQuotationList((1 - 1)* FuweiSystemData.DEFAULT_PERPAGE, FuweiSystemData.DEFAULT_PERPAGE);
				redirect(req, resp, totalPage, 1, totalQuotationListSize, FuweiSystemData.DEFAULT_PERPAGE, FuweiSystemData.DEFAULT_SEARCH_TYPE, "", quotationList,"","");

			}
		} catch (Exception e) {
			System.out.println("异常");
			e.printStackTrace();
			QuotationListDAO quotationListDAO=new QuotationListDAO();
			int totalQuotationListSize = quotationListDAO
			.getAllQuotationListCount();
			int totalPage = (totalQuotationListSize + FuweiSystemData.DEFAULT_PERPAGE - 1) / FuweiSystemData.DEFAULT_PERPAGE;
			
			List<QuotationList> quotationList=(List<QuotationList>)quotationListDAO.getAllQuotationList((1 - 1)* FuweiSystemData.DEFAULT_PERPAGE, FuweiSystemData.DEFAULT_PERPAGE);
			redirect(req, resp, totalPage, 1, totalQuotationListSize, FuweiSystemData.DEFAULT_PERPAGE, FuweiSystemData.DEFAULT_SEARCH_TYPE, "", quotationList,"","");

		}

	}
	
	
	private void redirect(HttpServletRequest req, HttpServletResponse resp,int totalPage,int page,int totalQuotationListSize,int perPage,int type,String key,List<QuotationList> quotationList,String aTime,String bTime){
		try {
			System.out.println("atime===="+aTime+"\nbtime-====="+bTime);
			req.setAttribute("totalPages", totalPage);
			req.setAttribute("page", page);
			req.setAttribute("totalRecords", totalQuotationListSize);
			req.setAttribute("perPage", perPage);
			req.setAttribute("type", type);
			req.setAttribute("key", key);
			req.setAttribute("quotationList", quotationList);
			req.setAttribute("atime", aTime);
			req.setAttribute("btime", bTime);
			req.getRequestDispatcher("quotationLists.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
