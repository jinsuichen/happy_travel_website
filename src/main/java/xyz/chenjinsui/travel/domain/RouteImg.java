package xyz.chenjinsui.travel.domain;

/**
 * @author FengLing
 */
public class RouteImg {

    private Integer rgid;
    private Integer rid;
    private String bigPig;
    private String smallPig;

    public Integer getRgid() {
        return rgid;
    }

    public void setRgid(Integer rgid) {
        this.rgid = rgid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getBigPig() {
        return bigPig;
    }

    public void setBigPig(String bigPig) {
        this.bigPig = bigPig;
    }

    public String getSmallPig() {
        return smallPig;
    }

    public void setSmallPig(String smallPig) {
        this.smallPig = smallPig;
    }

    @Override
    public String toString() {
        return "RouteImg{" +
                "rgid=" + rgid +
                ", rid=" + rid +
                ", bigPig='" + bigPig + '\'' +
                ", smallPig='" + smallPig + '\'' +
                '}';
    }
}
