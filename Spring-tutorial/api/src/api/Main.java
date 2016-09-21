package api;

import java.util.ArrayList;

import dao.CultureInfoDao;
import dao.CultureInfoDaoImpl;
import dto.CultureInfo;

public class Main {
	public static void main(String[] args) {

		String from = "2015-01-01";
		String to = "2016-12-31";

		// 목록에서 seq 가져오기
		ArrayList<CultureInfo> list = CultureXmlParser.getInstance().getPerformanceList(from, to);

		System.out.println(" " + list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getSeq());
		}
		
		ArrayList<CultureInfo> list2 = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			CultureInfo item = list.get(i);

			
			// 가져온 seq를 기반으로 상세정보 조회
			item = CultureXmlParser.getInstance().getPerformanceDetail(item);

			list2.add(item);
			
			System.out.println(item);
		}
		
		CultureInfoDao dao = new CultureInfoDaoImpl();
		
		dao.bulkInsertCultureInfo(list2);
		
//		for(int i=0; i<list2.size(); i++){
//			dao.insertCultureInfo(list2.get(i));
//		}	
		
		System.out.println("commit");
	}
}
