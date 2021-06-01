package vn.compedia.website.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.annotation.SessionScope;
import vn.compedia.website.model.Calculation;
import vn.compedia.website.util.FacesUtil;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;


@Setter
@Getter
@SessionScope
@Named
public class CaculatedControler implements Serializable {

    private List<Calculation> calculations;
    private Double sotienVay;
    private int sothangVay;
    private float profit;
    private Date ngayGiaiNgan;

    public CaculatedControler() {
    }

    public CaculatedControler(List<Calculation> calculations, Double sotienVay, int sothangVay, float profit, Date ngayGiaiNgan) {
        this.calculations = calculations;
        this.sotienVay = sotienVay;
        this.sothangVay = sothangVay;
        this.profit = profit;
        this.ngayGiaiNgan = ngayGiaiNgan;
    }

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            reserAll();
        }
    }
    public void reserAll() {
    }
    public void refresh(){
        sotienVay=null;
        sothangVay=0;
        profit= 0;
        ngayGiaiNgan=null;
    }

    public void onSearch(){
        calculations = new ArrayList<>();
        double gocTraHangThang = sotienVay/sothangVay;
        double tam = sotienVay;
        Calendar start = Calendar.getInstance();
        start.setTime(ngayGiaiNgan);

        double sumProfit =0;
        double sumAmount =0 ;
        double sumAll =0;
        for (int i=1;i<=sothangVay ;i++){
            Calculation calculation = new Calculation();
            //ky han
            String a = Integer.toString(i);
            calculation.setPeriod(a);
            //cac thang dong lai
            start.add(Calendar.MONTH,1);
            calculation.setPaymentDate(start.getTime());
            //lai tra hang thang
            double profitPerMonth = (tam*(profit/100)*30)/360;
            calculation.setProfitPerMonth(profitPerMonth);
            //so goc con lai
            tam -= gocTraHangThang;
            calculation.setRemain(tam);
            //goc tra hang thang
            calculation.setMoneyPerMonth(gocTraHangThang);
            //tong so tien phai tra hang thang
            double sum = gocTraHangThang+profitPerMonth;
            calculation.setAmountPerMonth(sum);

            sumProfit += profitPerMonth;
            sumAmount += gocTraHangThang;
            sumAll += sum;
            calculations.add(calculation);
        }
        calculations.add(new Calculation("Tổng" ,null,0,sumAmount,sumProfit,sumAll));
        FacesUtil.updateView("searchForm");
    }

    public List<Calculation> onCal(){
        calculations = new ArrayList<>();
        double gocTraHangThang = sotienVay/sothangVay;
        double tam = sotienVay;
        Calendar start = Calendar.getInstance();
        start.setTime(ngayGiaiNgan);

        double sumProfit =0;
        double sumAmount =0 ;
        double sumAll =0;
        for (int i=1;i<=sothangVay ;i++){
            Calculation calculation = new Calculation();
            //ky han
            String a = Integer.toString(i);
            calculation.setPeriod(a);
            //cac thang dong lai
            start.add(Calendar.MONTH,1);
            calculation.setPaymentDate(start.getTime());
            //lai tra hang thang
            double profitPerMonth = (tam*(profit/100)*30)/360;
            calculation.setProfitPerMonth(profitPerMonth);
            //so goc con lai
            tam -= gocTraHangThang;
            calculation.setRemain(tam);
            //goc tra hang thang
            calculation.setMoneyPerMonth(gocTraHangThang);
            //tong so tien phai tra hang thang
            double sum = gocTraHangThang+profitPerMonth;
            calculation.setAmountPerMonth(sum);

            sumProfit += profitPerMonth;
            sumAmount += gocTraHangThang;
            sumAll += sum;
            calculations.add(calculation);
        }
        calculations.add(new Calculation("Tổng" ,null,0,sumAmount,sumProfit,sumAll));


        return calculations ;
    }
}
