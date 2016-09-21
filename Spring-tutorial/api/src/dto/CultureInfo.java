package dto;

import java.io.Serializable;
import java.util.Date;

public class CultureInfo implements Serializable {

	private static final long serialVersionUID = 6533053129775332151L;

	private Integer seq; // 고유값
	private String title;
	private Date startDate;
	private Date endDate;
	private String place; // 장소
	private String category;
	private String area;
	private String price;
	private String homepage;
	private String phone;
	private String mainPosterUrl;
	private String detailPosterUrl; // 파싱해야함
	private Double gpsX;
	private Double gpsY;
	private String placeUrl;
	private String placeAddr; // 주소
	private Integer placeSeq;

	public CultureInfo() {

		this.seq = 0;
		this.title = "";
		this.startDate = null;
		this.endDate = null;
		this.place = "";
		this.category = "";
		this.area = "";
		this.price = "";
		this.homepage = "";
		this.phone = "";
		this.mainPosterUrl = "";
		this.detailPosterUrl = "";
		this.gpsX = 0.0;
		this.gpsY = 0.0;
		this.placeUrl = "";
		this.placeAddr = "";
		this.placeSeq = 0;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMainPosterUrl() {
		return mainPosterUrl;
	}

	public void setMainPosterUrl(String mainPosterUrl) {
		this.mainPosterUrl = mainPosterUrl;
	}

	public String getDetailPosterUrl() {
		return detailPosterUrl;
	}

	public void setDetailPosterUrl(String detailPosterUrl) {
		this.detailPosterUrl = detailPosterUrl;
	}

	public Double getGpsX() {
		return gpsX;
	}

	public void setGpsX(Double gpsX) {
		this.gpsX = gpsX;
	}

	public Double getGpsY() {
		return gpsY;
	}

	public void setGpsY(Double gpsY) {
		this.gpsY = gpsY;
	}

	public String getPlaceUrl() {
		return placeUrl;
	}

	public void setPlaceUrl(String placeUrl) {
		this.placeUrl = placeUrl;
	}

	public String getPlaceAddr() {
		return placeAddr;
	}

	public void setPlaceAddr(String placeAddr) {
		this.placeAddr = placeAddr;
	}

	public Integer getPlaceSeq() {
		return placeSeq;
	}

	public void setPlaceSeq(Integer placeSeq) {
		this.placeSeq = placeSeq;
	}

	@Override
	public String toString() {
		return "CultureInfo [seq=" + seq + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", place=" + place + ", category=" + category + ", area=" + area + ", price=" + price + ", homepage="
				+ homepage + ", phone=" + phone + ", mainPosterUrl=" + mainPosterUrl + ", detailPosterUrl="
				+ detailPosterUrl + ", gpsX=" + gpsX + ", gpsY=" + gpsY + ", placeUrl=" + placeUrl + ", placeAddr="
				+ placeAddr + ", placeSeq=" + placeSeq + "]";
	}

}
