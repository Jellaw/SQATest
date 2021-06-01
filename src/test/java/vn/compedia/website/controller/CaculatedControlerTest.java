package vn.compedia.website.controller;

import org.junit.Assert;
import org.junit.Test;
import vn.compedia.website.model.Calculation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CaculatedControlerTest {


    CaculatedControler caculatedControler = new CaculatedControler();
    @Test
    public void onSearch() {
        Calculation calculationBefore = new Calculation("aaa",new Date(2020,01,01),30000000,1000000,0.07,12365);
        Calculation calculationAfter = new Calculation("aaa",new Date(2020,02,01),30000000,1000000,0.07,12365);
        List<Calculation> list = new ArrayList<>();
        list.add(calculationBefore);
        list.add(calculationAfter);
        caculatedControler.setCalculations(list);
        caculatedControler.setNgayGiaiNgan(new Date(2020,01,01));
        caculatedControler.setSothangVay(12);
        caculatedControler.setSotienVay(30000000d);
        caculatedControler.setProfit(0.07f);

        Assert.assertEquals("Pass", String.valueOf(caculatedControler.onCal().size()),"13");

    }


    @Test
    public void onNhapSoTienAm() {
      String check = "true";
        Calculation calculationBefore = new Calculation("aaa",new Date(2020,01,01),30000000,1000000,0.07,12365);
        Calculation calculationAfter = new Calculation("aaa",new Date(2020,02,01),30000000,1000000,0.07,12365);
        List<Calculation> list = new ArrayList<>();
        list.add(calculationBefore);
        list.add(calculationAfter);
        caculatedControler.setCalculations(list);
        caculatedControler.setNgayGiaiNgan(new Date(2020,01,01));
        caculatedControler.setSothangVay(12);
        caculatedControler.setSotienVay(-30000000d);
        caculatedControler.setProfit(0.07f);

        list=caculatedControler.onCal();
        Calculation calculation = list.stream().filter(p->p.getRemain()<0).findFirst().orElse(null);
        if (calculation!=null){
            check = "false";
        }
        Assert.assertEquals("Fail", check,"false");

    }
}