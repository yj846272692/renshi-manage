package com.company.chart;

import com.company.factory.ServiceFactory;
import com.company.entity.EveryTimeSalaryCount;
import com.company.entity.Staff;
import com.company.service.CountService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**

 * 个人工资每次发放时的最终工资柱状图
 */
public class PersonalSalaryBarChart {
    private Staff staff;
    ChartPanel chartPanel;
    String[] name;
    List<EveryTimeSalaryCount> list = new ArrayList<>();
    CountService countService = ServiceFactory.getCountServiceInstance();
    int i;
    int length;
    public PersonalSalaryBarChart(Staff staff){
        this.staff = staff;
        list = countService.getPersonalEveryTimeCount(staff.getStaffNumber());
        length = list.size();
        name = new String[length];
        for (i = 0;i<length;i++){
            name[i] = list.get(i).getEveryTime();
        }
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("按发布时间统计", // 图表标题
                "时间", // 目录轴的显示标签
                "工资", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true, // 是否显示图例(对于简单的柱状图必须是false)
                false, // 是否生成工具
                false // 是否生成URL链接
        );

        // 从这里开始
        CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

        // 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        chartPanel = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

    }
    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public CategoryDataset getDataSet(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (i = 0;i<length;i++){
            dataset.addValue(list.get(i).getSalaryCount(),list.get(i).getEveryTime(),list.get(i).getEveryTime());
        }
        return dataset;
    }
}