package com.fuwei.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuwei.DAO.CompanyPriceDAO;
import com.fuwei.DAO.QuotationListDAO;
import com.fuwei.DAO.SampleDAO;
import com.fuwei.entity.CompanyPrice;
import com.fuwei.entity.QuotationList;
import com.fuwei.entity.Sample;
import com.fuwei.util.FuweiSystemData;
import com.fuwei.util.StringTODate;

public class SearchSampleByCondition extends HttpServlet {
	private static final int SEARCHBYPRODUCTNUMBER = 1;
	private static final int SEARCHBYCOMPANYNAME = 2;
	private static final int SEARCHBYDEVELOPER = 3;
	private static final int SEARCHBYSALESMAN = 4;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
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
		System.out.println("key:"+key+"\ntype:"+type);
		if(!StringTODate.canChangeStringTODate(aTime)){
			aTime=null;
		}
		if(!StringTODate.canChangeStringTODate(bTime)){
			bTime=null;
		}

		if (key == null || FuweiSystemData.DEFAULT_SEARCH_TYPE == type||key.length()<1) {
			SampleDAO sampleDAO = new SampleDAO();
			List<Sample> samples=null;
			//////////////////////////////
			
			if(aTime!=null&&bTime!=null){
				System.out.println("a===1");
				 int	totalSampleSize = sampleDAO
					.getAllSampleBetweenTwoTimeCount(StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
				int totalPage = (totalSampleSize + perPage - 1) / perPage;
				if (page > totalPage) {
					page = totalPage;
				} else if (page < 1) {
					page = 1;
				}
				 samples=(List<Sample>)sampleDAO.getAllSampleBetweenTwoTime(StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
				 redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, "", samples,aTime,bTime);
			}else if (aTime==null&&bTime!=null) {
				System.out.println("a===2");
				int totalSampleSize = sampleDAO
				.getAllSampleAfterTimeCount(StringTODate.changeStringTODate(bTime));
				int totalPage = (totalSampleSize + perPage - 1) / perPage;
				if (page > totalPage) {
					page = totalPage;
				} else if (page < 1) {
					page = 1;
				}
				 samples=(List<Sample>)sampleDAO.getAllSampleAfterTime(StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
				 redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, "", samples,"",bTime);
			}else if (aTime!=null&&bTime==null) {
				System.out.println("a===3");
				int totalSampleSize = sampleDAO
				.getAllSampleBeforeTimeCount(StringTODate.changeStringTODate(aTime));
				int totalPage = (totalSampleSize + perPage - 1) / perPage;
				if (page > totalPage) {
					page = totalPage;
				} else if (page < 1) {
					page = 1;
				}
				 samples=(List<Sample>)sampleDAO.getAllSampleBeforeTime(StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);

				 redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, "", samples,aTime,"");
			}else {
				System.out.println("a===4");
				int totalSampleSize = sampleDAO
				.getAllSampleCount();
				int totalPage = (totalSampleSize + perPage - 1) / perPage;
				if (page > totalPage) {
					page = totalPage;
				} else if (page < 1) {
					page = 1;
				}
				samples=(List<Sample>)sampleDAO.getAllSample((page - 1)* perPage, perPage);
				redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, "", samples,"","");
			}
		}else  {
			switch (type) {
			case SEARCHBYPRODUCTNUMBER: {
				//===================================================
				SampleDAO sampleDAO = new SampleDAO();
				List<Sample> samples=null;
				Set<Integer> idsSet=searchSampleIDSETByProductNumber(key);
				if (aTime==null&&bTime!=null) {
					int totalSampleSize = sampleDAO
					.getSampleCountByIDSETAfterTime(idsSet,StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByIDSETAfterTime(idsSet,StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,"",bTime);
				}else if (aTime!=null&&bTime==null) {
					int totalSampleSize = sampleDAO
					.getSampleCountByIDSETBeforeTime(idsSet,StringTODate.changeStringTODate(aTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByIDSETBeforeTime(idsSet,StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,aTime,"");
				}else if (aTime!=null&&bTime!=null) {
					 int	totalSampleSize = sampleDAO
						.getSampleCountByIDSETBetweenTwoTime(idsSet,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByIDSETBetweenTwoTime(idsSet,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,aTime,bTime);
				}else {//atime=null   btime=null;
					int totalSampleSize = sampleDAO
					.getSampleCountByIDSETWithoutTime(idsSet);
					int totalPage = (totalSampleSize +perPage - 1) / perPage;
					samples=(List<Sample>)sampleDAO.getSampleByIDSETWithoutTime(idsSet,(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,"","");
				}
			}
				break;

			case SEARCHBYCOMPANYNAME: {
				
				
				//===================================================
				SampleDAO sampleDAO = new SampleDAO();
				List<Sample> samples=null;
				Set<Integer> idsSet=searchSampleIDSETByCompanyName(key);
				if (aTime==null&&bTime!=null) {
					int totalSampleSize = sampleDAO
					.getSampleCountByIDSETAfterTime(idsSet,StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByIDSETAfterTime(idsSet,StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,"",bTime);
				}else if (aTime!=null&&bTime==null) {
					int totalSampleSize = sampleDAO
					.getSampleCountByIDSETBeforeTime(idsSet,StringTODate.changeStringTODate(aTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByIDSETBeforeTime(idsSet,StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,aTime,"");
				}else if (aTime!=null&&bTime!=null) {
					 int	totalSampleSize = sampleDAO
						.getSampleCountByIDSETBetweenTwoTime(idsSet,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByIDSETBetweenTwoTime(idsSet,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,aTime,bTime);
				}else {//atime=null   btime=null;
					int totalSampleSize = sampleDAO
					.getSampleCountByIDSETWithoutTime(idsSet);
					int totalPage = (totalSampleSize +perPage - 1) / perPage;
					samples=(List<Sample>)sampleDAO.getSampleByIDSETWithoutTime(idsSet,(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,"","");
				}
				
				
			}

				break;
			case SEARCHBYDEVELOPER: {
				//===================================================
				SampleDAO sampleDAO = new SampleDAO();
				List<Sample> samples=null;
				if (aTime==null&&bTime!=null) {
					int totalSampleSize = sampleDAO
					.getSampleCountByDeveloperAfterTime(key,StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByDeveloperAfterTime(key,StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,"",bTime);
				}else if (aTime!=null&&bTime==null) {
					int totalSampleSize = sampleDAO
					.getSampleCountByDeveloperBeforeTime(key,StringTODate.changeStringTODate(aTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByDeveloperBeforeTime(key,StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,aTime,"");
				}else if (aTime!=null&&bTime!=null) {
					 int	totalSampleSize = sampleDAO
						.getSampleCountByDeveloperBetweenTwoTime(key,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByDeveloperBetweenTwoTime(key,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,aTime,bTime);
				}else {//atime=null   btime=null;
					int totalSampleSize = sampleDAO
					.getSampleCountByDeveloperWithoutTime(key);
					int totalPage = (totalSampleSize +perPage - 1) / perPage;
					samples=(List<Sample>)sampleDAO.getSampleByDeveloperWithoutTime(key,(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,"","");
				}
			}

				break;
			case SEARCHBYSALESMAN:{
				SampleDAO sampleDAO = new SampleDAO();
				Set<Integer> idsSet=searchSampleIDSETBySalesMan(key);
				List<Sample> samples=null;
				if (aTime==null&&bTime!=null) {
					int totalSampleSize = sampleDAO
					.getSampleCountByIDSETAfterTime(idsSet,StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByIDSETAfterTime(idsSet,StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,"",bTime);
				}else if (aTime!=null&&bTime==null) {
					int totalSampleSize = sampleDAO
					.getSampleCountByIDSETBeforeTime(idsSet,StringTODate.changeStringTODate(aTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByIDSETBeforeTime(idsSet,StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,aTime,"");
				}else if (aTime!=null&&bTime!=null) {
					 int	totalSampleSize = sampleDAO
						.getSampleCountByIDSETBetweenTwoTime(idsSet,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getSampleByIDSETBetweenTwoTime(idsSet,StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,aTime,bTime);
				}else {//atime=null   btime=null;
					int totalSampleSize = sampleDAO
					.getSampleCountByIDSETWithoutTime(idsSet);
					int totalPage = (totalSampleSize +perPage - 1) / perPage;
					samples=(List<Sample>)sampleDAO.getSampleByIDSETWithoutTime(idsSet,(page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, key, samples,"","");
				}
			}
				break;

			default:{
				SampleDAO sampleDAO = new SampleDAO();
				List<Sample> samples=null;
				//////////////////////////////
				
				if(aTime!=null&&bTime!=null){
					System.out.println("a===1");
					 int	totalSampleSize = sampleDAO
						.getAllSampleBetweenTwoTimeCount(StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 samples=(List<Sample>)sampleDAO.getAllSampleBetweenTwoTime(StringTODate.changeStringTODate(aTime), StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, "", samples,aTime,bTime);
				}else if (aTime==null&&bTime!=null) {
					System.out.println("a===2");
					int totalSampleSize = sampleDAO
					.getAllSampleAfterTimeCount(StringTODate.changeStringTODate(bTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 samples=(List<Sample>)sampleDAO.getAllSampleAfterTime(StringTODate.changeStringTODate(bTime),(page - 1)* perPage, perPage);
					 redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, "", samples,"",bTime);
				}else if (aTime!=null&&bTime==null) {
					System.out.println("a===3");
					int totalSampleSize = sampleDAO
					.getAllSampleBeforeTimeCount(StringTODate.changeStringTODate(aTime));
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					 samples=(List<Sample>)sampleDAO.getAllSampleBeforeTime(StringTODate.changeStringTODate(aTime),(page - 1)* perPage, perPage);

					 redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, "", samples,aTime,"");
				}else {
					System.out.println("a===4");
					int totalSampleSize = sampleDAO
					.getAllSampleCount();
					int totalPage = (totalSampleSize + perPage - 1) / perPage;
					if (page > totalPage) {
						page = totalPage;
					} else if (page < 1) {
						page = 1;
					}
					samples=(List<Sample>)sampleDAO.getAllSample((page - 1)* perPage, perPage);
					redirect(req, resp, totalPage, page, totalSampleSize, perPage, type, "", samples,"","");
				}
			}
				break;
			}
		
		}

	}
	
	
	private void redirect(HttpServletRequest req, HttpServletResponse resp,int totalPage,int page,int totalSampleSize,int perPage,int type,String key,List<Sample> samples,String aTime,String bTime){
		try {
			if (samples == null) {
				samples = new ArrayList<Sample>();
			}
			req.setAttribute("totalPages", totalPage);
			req.setAttribute("page", page);
			req.setAttribute("totalRecords", totalSampleSize);
			req.setAttribute("perPage", perPage);
			req.setAttribute("type", type);
			req.setAttribute("key", key);
			req.setAttribute("samples", samples);
			req.setAttribute("atime", aTime);
			req.setAttribute("btime", bTime);
			req.getRequestDispatcher("Samples.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
	}

	public Set<Integer> searchSampleIDSETByProductNumber(String productNumber) {
		Set<Integer> sampleIDCollection = new HashSet<Integer>();
		CompanyPriceDAO companyPriceDAO = new CompanyPriceDAO();
		List<CompanyPrice> companyPrices = (List<CompanyPrice>) companyPriceDAO
				.searchCompanyPriceByProductNumber(productNumber);
		for (CompanyPrice companyPrice : companyPrices) {
			if (!sampleIDCollection.contains(companyPrice.getSampleId())) {
				sampleIDCollection.add(companyPrice.getSampleId());
			}
		}
		SampleDAO sampleDAO = new SampleDAO();
		List<Sample> samples = (List<Sample>) sampleDAO
				.searchSampleByProductNumber(productNumber);

		// bug修改
		if (samples == null) {
			samples = new ArrayList<Sample>();
		}
		// bug修改

		for (Sample sample : samples) {
			if (!sampleIDCollection.contains(sample.getId())) {
				sampleIDCollection.add(sample.getId());
			}
		}

		return sampleIDCollection;

	}

	private Set<Integer> searchSampleIDSETByCompanyName(String companyName) {
		CompanyPriceDAO companyPriceDAO = new CompanyPriceDAO();
		List<CompanyPrice> companyPrices = (List<CompanyPrice>) companyPriceDAO
				.searchCompanyPriceByCompanyName(companyName);
		Set<Integer> idSet = new HashSet<Integer>();
		for (CompanyPrice companyPrice : companyPrices) {
			if (!idSet.contains(companyPrice.getSampleId())) {
				idSet.add(companyPrice.getSampleId());
			}
		}
		if(idSet==null){
			System.out.println("idset==null");
		}else{
			System.out.println("idset!=null");
		}
		
		return idSet;
	}

	private Set<Integer> searchSampleIDSETBySalesMan(String salesManName) {
		CompanyPriceDAO companyPriceDAO = new CompanyPriceDAO();
		List<CompanyPrice> companyPrices = (List<CompanyPrice>) companyPriceDAO
				.searchCompanyPriceBySalesManName(salesManName);
		Set<Integer> sets = new HashSet<Integer>();
		for (CompanyPrice companyPrice : companyPrices) {
			if (!sets.contains(companyPrice.getSampleId())) {
				sets.add(companyPrice.getSampleId());
			}
		}

		return sets;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

}
