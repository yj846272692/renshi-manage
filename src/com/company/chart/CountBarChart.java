package com.company.chart;


import com.company.factory.ServiceFactory;
import com.company.entity.TypeCheckCount;
import com.company.service.CountService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**

 * 全员考勤统计图
 */
public class CountBarChart {
    ChartPanel chartPanel;
    String[] type;
    List<TypeCheckCount> list = new ArrayList<>();
    CountService countService = ServiceFactory.getCountServiceInstance();
    int i;
    int lenth;

    public CountBarChart(){
        list = countService.getCountBycheckType();
        lenth = list.size();
        type = new String[lenth];
        for (i=0;i<lenth ; i++){
            type[i] = list.get(i).getCheckType();
        }

        CategoryDataset dataset= getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("按考勤状况",
                "类型","人数",
                dataset, PlotOrientation.VERTICAL,true,false,false);

        CategoryPlot plot= chart.getCategoryPlot();
        org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));//水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));//垂直标题
        org.jfree.chart.axis.ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,15));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
        chartPanel = new ChartPanel(chart,true);
    }

    public ChartPanel getChartPanel(){
        return chartPanel;
    }

    public CategoryDataset getDataSet(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (i=0;i<lenth;i++){
            dataset.addValue(list.get(i).getCount(),list.get(i).getCheckType(),list.get(i).getCheckType());

        }
        return dataset;
    }
}
