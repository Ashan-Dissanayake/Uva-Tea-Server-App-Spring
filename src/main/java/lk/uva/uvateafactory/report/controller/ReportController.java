package lk.uva.uvateafactory.report.controller;

import lk.uva.uvateafactory.report.dao.*;
import lk.uva.uvateafactory.report.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/reports")
public class ReportController {

    @Autowired
    private CountByDesignationDao countbydesignaitondao;

    @Autowired
    private CountByAreaDao countByAreaDao;

    @Autowired
    private TeaCropSummaryDao teaCropSummaryDao;

    @Autowired
    private AreaTeaQuantityDao areaTeaQuantityDao;

    @Autowired
    private FertilizerdistributionsummaryDao fertilizerdistributionsummaryDao;

    @Autowired
    private FertilizerremainingDao fertilizerremainingDao;

    @GetMapping(path ="/countbydesignation",produces = "application/json")
    public List<CountByDesignation> getDesignation() {

        List<CountByDesignation> designations = this.countbydesignaitondao.countByDesignation();
        long totalCount = 0;

        for (CountByDesignation countByDesignation : designations) {
            totalCount += countByDesignation.getCount();
        }

        for (CountByDesignation countByDesignation : designations) {
            long count = countByDesignation.getCount();
            double percentage = (double) count / totalCount * 100;
            percentage = Math.round(percentage * 100.0) / 100.0;
            countByDesignation.setPercentage(percentage);
        }

        return designations;
    }

    @GetMapping(path ="/countbyareas",produces = "application/json")
    public List<CountByArea> getAreas() {

        List<CountByArea> areas = this.countByAreaDao.countByArea();
        long totalCount = 0;

        for (CountByArea countByArea : areas) {
            totalCount += countByArea.getCount();
        }

        for (CountByArea countByArea : areas) {
            long count = countByArea.getCount();
            double percentage = (double) count / totalCount * 100;
            percentage = Math.round(percentage * 100.0) / 100.0;
            countByArea.setPercentage(percentage);
        }

        return areas;
    }

    @GetMapping(path ="/areateaquantity",produces = "application/json")
    public List<AreaTeaQuantity> getTeaQuantity(@RequestParam HashMap<String,String> params) {

        String date = params.get("sdate");

//        System.out.println(date);

        Date getDate = Date.valueOf(date);

//        Date getDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);

//        System.out.println("new: "+getDate);

//        Date getDate = LocalDate.parse(date);

        List<AreaTeaQuantity> teaQuantities = this.areaTeaQuantityDao.teaQuantity(getDate);
        long totalCount = 0;

        for (AreaTeaQuantity areaTeaQuantity : teaQuantities) {
            totalCount += areaTeaQuantity.getTeaTotal();
        }

        for (AreaTeaQuantity areaTeaQuantity : teaQuantities) {
            long count = areaTeaQuantity.getTeaTotal();
            double percentage = (double) count / totalCount * 100;
            percentage = Math.round(percentage * 100.0) / 100.0;
            areaTeaQuantity.setPercentage(percentage);
        }

        return teaQuantities;
//        return null;
    }

    @GetMapping(path ="/teacropsummary",produces = "application/json")
    public List<TeaCropSummary> getTeaCropsSummary(@RequestParam HashMap<String,String> params) {

        String pastdate = params.get("pastdate");
        String presentdate = params.get("presentdate");

        Date pastdateconvert = Date.valueOf(pastdate);
        Date presentdateconvert = Date.valueOf(presentdate);

        List<TeaCropSummary> teaCropSummaryFilter = new ArrayList<TeaCropSummary>();

        List<TeaCropSummary> teaCropSummaries = this.teaCropSummaryDao.teacropsummary(pastdateconvert,presentdateconvert);

        long totalCount = 0;

        for(TeaCropSummary teaCropSummary: teaCropSummaries ) {

            if(teaCropSummary.getCurrenttotal()!=0 && teaCropSummary.getPrevtotal()!=0 ) {
                teaCropSummaryFilter.add(teaCropSummary);
            }

        }

        for (TeaCropSummary teaCropSummaryfil: teaCropSummaryFilter ) {

            Long difference = teaCropSummaryfil.getCurrenttotal() - teaCropSummaryfil.getPrevtotal();
            teaCropSummaryfil.setDifference(difference);

            if(difference <0 ) teaCropSummaryfil.setStatus("downgrade");
            else if(difference>0) teaCropSummaryfil.setStatus("upgrade");
            else teaCropSummaryfil.setStatus("NoChange");

        }

        return teaCropSummaryFilter;
    }

    @GetMapping(path = "/fertilizerreamining",produces = "application/json")
    public List<Fertilizerremaining> getFertilizerRemaining() {

        List<Fertilizerremaining> fertilizerremainings = fertilizerremainingDao.remainFertilizer();
        return fertilizerremainings;

    }

    @GetMapping(path ="/fertilizerdistributionsummary",produces = "application/json")
    public List<Fertilizerdistributionsummary> getfertisum(@RequestParam HashMap<String,String> params) {

//        Integer date1 = 2022;
//        Integer date2 = 2023;
        String date1str = params.get("pastyear");
        String date2str = params.get("presentyear");

//        String date1str = "2022-10-24";
//        String date2str = "2023-04-18";

        Integer date1year = Integer.parseInt(date1str.substring(0,4));
        Integer date2year = Integer.parseInt(date2str.substring(0,4));

        ArrayList<Fertilizerdistributionsummary> fertilizerdisfilter = new ArrayList<Fertilizerdistributionsummary>();

//        String date1 = "2022";
//        String date2 = "2023";

//        String name = "Hk:tyh:jkjkjk";
//        String[] sperated = name.split(":");
//        System.out.println("First "+sperated[0]);
//        System.out.println("Second "+sperated[1]);
//        System.out.println("Third "+sperated[2]);
//
//        String pastdate = params.get("pastdate");
//        String presentdate = params.get("presentdate");
//
//        Date pastdateconvert = Date.valueOf(date1);
//        Date presentdateconvert = Date.valueOf(date2);
//
//        List<TeaCropSummary> teaCropSummaryFilter = new ArrayList<TeaCropSummary>();
//        System.out.println( this.fertilizerdistributionsummaryDao.fertilizerdistributionsummary(date1,date2));
        List<Fertilizerdistributionsummary> fertilizerdistributionsummarys = this.fertilizerdistributionsummaryDao.getfertilizerdistributionsummary(date1year,date2year);

        for(Fertilizerdistributionsummary ferdissummary: fertilizerdistributionsummarys ) {

            if(ferdissummary.getPrevyear().compareTo(BigDecimal.ZERO)>0 && ferdissummary.getCurrentyear().compareTo(BigDecimal.ZERO)>0 ) {
               fertilizerdisfilter.add(ferdissummary);
            }
        }

        return fertilizerdisfilter;


//        long totalCount = 0;

//        for(TeaCropSummary teaCropSummary: teaCropSummaries ) {
//
//            if(teaCropSummary.getCurrenttotal()!=0 && teaCropSummary.getPrevtotal()!=0 ) {
//                teaCropSummaryFilter.add(teaCropSummary);
//            }
//
//        }

//        for (TeaCropSummary teaCropSummaryfil: teaCropSummaryFilter ) {
//
//            Long difference = teaCropSummaryfil.getCurrenttotal() - teaCropSummaryfil.getPrevtotal();
//            teaCropSummaryfil.setDifference(difference);
//
//            if(difference <0 ) teaCropSummaryfil.setStatus("downgrade");
//            else if(difference>0) teaCropSummaryfil.setStatus("upgrade");
//            else teaCropSummaryfil.setStatus("NoChange");
//
//        }
//
    }



}
