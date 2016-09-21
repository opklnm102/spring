package servera.spring.hello;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SortOrderService sortOrder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		formattedDate = "2015-11-27 Thur. 4:33:59";
		model.addAttribute("serverTime", formattedDate);

		return "main";
	}

	@RequestMapping(value = "/contents", method = RequestMethod.GET)
	public String contents(Locale locale, Model model) {

		return "contents";
	}

	private int noTotalArticles;
	private int noTotalPages;
	ArrayList<Article> articles;

	public void showPageX(int pageNum, int pageSize) {
		Connection con = null;
		articles = new ArrayList<Article>();

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://C:/users.mdb";
			con = DriverManager.getConnection(url);

		 String SQL = "select subject,name,created,hit,id from bbs_studypds order by id ";
			String sort = sortOrder.getSortOrder();
			
			System.out.println(SQL + sort);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL + sort);

			for (int i = 0; i < pageNum * pageSize; i++)
				rs.next();

			int no = 0;

			while (rs.next() && no < pageSize) {
				Article art = new Article();
				art.setNo(noTotalArticles - pageNum * pageSize - no);
				art.setTitle(rs.getString("subject"));
				art.setName(rs.getString("name"));
				art.setKey(rs.getInt("id"));

				String sDate = "";
				sDate += rs.getObject("created");
				int ndx = sDate.indexOf("00:");
				art.setDate(sDate.substring(0, ndx));

				art.setHit(rs.getInt("hit"));
				articles.add(art);
				no++;
			}
			st.close();
			con.close();
			System.out.println("well done!!");
		} catch (SQLException e) {
			System.out.println("Error on sql");
		} catch (Exception e) {
			System.out.println("Error on DB-open ");
		}
	}

	// 페이지 수 초기화
	public void initListPage(int pageNum, int pageSize) {
		Connection con = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			String url = "jdbc:ucanaccess://C:/users.mdb";
			con = DriverManager.getConnection(url);

		 String SQL = "select * from bbs_studypds order by id ";
			String sort = sortOrder.getSortOrder();
			
			System.out.println(SQL + sort);

			int noArticles = pageNum;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL + sort);

			while (rs.next()) {
				noArticles++;
			}

			noTotalArticles = noArticles; // 총 아이템 수
			noTotalPages = (noArticles + (pageSize - 1)) / pageSize; // 총 페이지 수

			System.out.println("noTotalArticles " + noTotalArticles + " noTotalPages " + noTotalPages);

			st.close();
			con.close();
			System.out.println("well done!!");
		} catch (SQLException e) {
			System.out.println("Error on sql");
		} catch (Exception e) {
			System.out.println("Error on DB-open ");
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String artList(Locale locale, Model model, HttpServletRequest request) {
		int pageSize = 0;
		int pageNum = 0;

		if (request.getParameter("page") != null && request.getParameter("size") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
			pageSize = Integer.parseInt(request.getParameter("size"));

			initListPage(pageNum, pageSize);
			showPageX(pageNum, pageSize);
		}

		// model.addAttribute("strError", sError );
		model.addAttribute("noTotalArticles", noTotalArticles);
		model.addAttribute("noTotalPages", noTotalPages);
		// model.addAttribute("articleList", articles);
		request.setAttribute("articleList", articles);
		return "artList";
	}
}
