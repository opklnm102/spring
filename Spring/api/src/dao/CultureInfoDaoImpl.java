package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dto.CultureInfo;
import utils.ConnectionMaker;
import utils.SimpleConnectionMaker;
import utils.TimeUtils;

public class CultureInfoDaoImpl implements CultureInfoDao {

	private static final String INSERT = "INSERT INTO culture"
			+ "(seq, title, startDate, endDate, place, category, area, price, homepage, phone, mainPosterUrl, detailPosterUrl, gpsX, gpsY, placeUrl, placeAddr, placeSeq) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public List<CultureInfo> getCultureInfoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CultureInfo getCultureInfo(Long cultureInfoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCultureInfo(CultureInfo cultureInfo) {

		try {
			ConnectionMaker connMaker = new SimpleConnectionMaker();
			connMaker.makeNewConnection();
			Connection conn = connMaker.makeNewConnection();

			PreparedStatement ps = conn.prepareStatement(INSERT);

			ps.setLong(1, cultureInfo.getSeq());
			ps.setString(2, cultureInfo.getTitle());
			ps.setDate(3, TimeUtils.convertFromUtilDateToSqlDate(cultureInfo.getStartDate()));
			ps.setDate(4, TimeUtils.convertFromUtilDateToSqlDate(cultureInfo.getEndDate()));
			ps.setString(5, cultureInfo.getPlace());
			ps.setString(6, cultureInfo.getCategory());
			ps.setString(7, cultureInfo.getArea());
			ps.setString(8, cultureInfo.getPrice());
			ps.setString(9, cultureInfo.getHomepage());
			ps.setString(10, cultureInfo.getPhone());
			ps.setString(11, cultureInfo.getMainPosterUrl());
			ps.setString(12, cultureInfo.getDetailPosterUrl());
			ps.setDouble(13, cultureInfo.getGpsX());
			ps.setDouble(14, cultureInfo.getGpsY());
			ps.setString(15, cultureInfo.getPlaceUrl());
			ps.setString(16, cultureInfo.getPlaceAddr());
			ps.setLong(17, cultureInfo.getPlaceSeq());

			ps.executeUpdate();
			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer updateCultureInfo(CultureInfo cultureInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteCultureInfo(CultureInfo cultureInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bulkInsertCultureInfo(List<CultureInfo> cultureInfos) {
		
		final int batchSize = 1000;
	
		try {
			ConnectionMaker connMaker = new SimpleConnectionMaker();
			connMaker.makeNewConnection();
			Connection conn = connMaker.makeNewConnection();

			PreparedStatement ps = conn.prepareStatement(INSERT);
			
			conn.setAutoCommit(false);
			
			for (int i = 0; i<cultureInfos.size(); i++) {
				
				CultureInfo cultureInfo = cultureInfos.get(i);

				ps.setLong(1, cultureInfo.getSeq());
				ps.setString(2, cultureInfo.getTitle());
				ps.setDate(3, TimeUtils.convertFromUtilDateToSqlDate(cultureInfo.getStartDate()));
				ps.setDate(4, TimeUtils.convertFromUtilDateToSqlDate(cultureInfo.getEndDate()));
				ps.setString(5, cultureInfo.getPlace());
				ps.setString(6, cultureInfo.getCategory());
				ps.setString(7, cultureInfo.getArea());
				ps.setString(8, cultureInfo.getPrice());
				ps.setString(9, cultureInfo.getHomepage());
				ps.setString(10, cultureInfo.getPhone());
				ps.setString(11, cultureInfo.getMainPosterUrl());
				ps.setString(12, cultureInfo.getDetailPosterUrl());
				ps.setDouble(13, cultureInfo.getGpsX());
				ps.setDouble(14, cultureInfo.getGpsY());
				ps.setString(15, cultureInfo.getPlaceUrl());
				ps.setString(16, cultureInfo.getPlaceAddr());
				ps.setLong(17, cultureInfo.getPlaceSeq());

				ps.addBatch(); // Insert구문을 더한다.

				// 배치에 너무 많은 건수를 입력할 경우
				// OutofMemoryError이 발생하기 때문에 적당하게 나누어 주어야한다.
				if (i % batchSize == 0) {
					ps.executeBatch();
				}
			}
			ps.executeBatch();  //insert remaining records

			conn.commit();
		
			ps.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// <dependency>
	// <!-- jsoup HTML parser library @ http://jsoup.org/ -->
	// <groupId>org.jsoup</groupId>
	// <artifactId>jsoup</artifactId>
	// <version>1.8.3</version>
	// </dependency>
}
