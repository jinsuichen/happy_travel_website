package xyz.chenjinsui.travel.domain;

import java.util.List;

/**
 * @author FengLing
 */
public class Route {

    private Integer rid;
    private String rname;
    private Double price;
    private String routeIntroduce;
    private Integer rflag;
    private String rdate;
    private Integer isThemeTour;
    private Integer count;
    private Integer cid;
    private String rimage;
    private Integer sid;
    private Integer sourcedId;

    private Category category;
    private Seller seller;
    private List<RouteImg> routeImgList;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public Integer getRflag() {
        return rflag;
    }

    public void setRflag(Integer rflag) {
        this.rflag = rflag;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public Integer getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(Integer isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSourcedId() {
        return sourcedId;
    }

    public void setSourcedId(Integer sourcedId) {
        this.sourcedId = sourcedId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<RouteImg> getRouteImgList() {
        return routeImgList;
    }

    public void setRouteImgList(List<RouteImg> routeImgList) {
        this.routeImgList = routeImgList;
    }

    @Override
    public String toString() {
        return "Route{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", price=" + price +
                ", routeIntroduce='" + routeIntroduce + '\'' +
                ", rflag=" + rflag +
                ", rdate='" + rdate + '\'' +
                ", isThemeTour=" + isThemeTour +
                ", count=" + count +
                ", cid=" + cid +
                ", rimage='" + rimage + '\'' +
                ", sid=" + sid +
                ", sourcedId=" + sourcedId +
                ", category=" + category +
                ", seller=" + seller +
                ", routeImgList=" + routeImgList +
                '}';
    }
}
