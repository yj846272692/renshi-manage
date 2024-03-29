package com.company.chart;


import com.company.factory.ServiceFactory;
import com.company.entity.PersonalSalaryCount;
import com.company.entity.Staff;
import com.company.service.CountService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

import org.jfree.chart.plot.PiePlot;

import org.jfree.data.general.DefaultPieDataset;


import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;



public class SalaryPieChart  {
    private Staff staff;
    private String time;
    ChartPanel chartPanel;
    String[] name;
    List<PersonalSalaryCount> list = new ArrayList<>();
    CountService countService = ServiceFactory.getCountServiceInstance();
    int i;
    int length;
    public SalaryPieChart(Staff staff,String time){
        this.staff = staff;
        this.time = time;
        list = countService.getPersonalSalaryCountByType(staff.getStaffNumber(),time);
        length = list.size();
        name = new String[length];
        for (i = 0; i < length; i++) {
            name[i] = list.get(i).getSalaryType();
        }
        DefaultPieDataset data = getDataSet();
        JFreeChart chart = ChartFactory.createPieChart3D("按工资类型统计", data, true, false, false);
        //设置百分比
        PiePlot pieplot = (PiePlot) chart.getPlot();
        DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
        NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
        StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
        pieplot.setLabelGenerator(sp1);//设置饼图显示百分比

        //没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);

        pieplot.setIgnoreNullValues(true);//设置不显示空值
        pieplot.setIgnoreZeroValues(true);//设置不显示负值
        chartPanel = new ChartPanel(chart, true);
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
        PiePlot piePlot = (PiePlot) chart.getPlot();//获取图表区域对象
        piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));//解决乱码
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
        chartPanel = new ChartPanel(chart, true);
    }


    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    private DefaultPieDataset getDataSet() {
    DefaultPieDataset dataset = new DefaultPieDataset();
    for (i = 0; i < length; i++) {
        dataset.setValue(list.get(i).getSalaryType(), list.get(i).getSalaryCount());
    }
    return dataset;
}


}
