package dao;

import java.util.List;

import dto.CultureInfo;

public interface CultureInfoDao {
	
	List<CultureInfo> getCultureInfoList();
	
	CultureInfo getCultureInfo(Long cultureInfoId);
	
	void insertCultureInfo(CultureInfo cultureInfo);
	
	Integer updateCultureInfo(CultureInfo cultureInfo);
	
	Integer deleteCultureInfo(CultureInfo cultureInfo);
	
	void bulkInsertCultureInfo(List<CultureInfo> cultureInfos);
}
