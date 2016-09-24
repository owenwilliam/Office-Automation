package com.linjw.myoa.model;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import com.opensymphony.xwork2.ActionContext;

public class Chart {
	

	public void PieChar(DefaultPieDataset dataset,String title,String chartname) throws IOException{

	        JFreeChart chart = ChartFactory.createPieChart3D( 
	        	            	title, // 图表标题
	                            dataset, // 数据集
	                            true,  // 是否显示图例(对于简单的柱状图必须是 false)
	                            false, // 是否生成工具
	                            false  // 是否生成 URL 链接
	                            ); 
	        //中文乱码
	        PiePlot3D plot = (PiePlot3D) chart.getPlot();
	        plot.setLabelFont(new Font("黑体", Font.PLAIN, 20));
	        TextTitle textTitle = chart.getTitle();
	        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
	        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
     /**
      * 路径
      */
	        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
			//获取路径（服务器上传的路径）
			String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
			String subPath = sdf.format(new Date());
			//如果文件夹不存在，就创建
			File dir = new File(basePath + subPath);
			if(!dir.exists()){
				dir.mkdirs();//递归的创建不存在的文件夹
			}
			//拼接路径
			String path = basePath + subPath + chartname; 
	        // 写图表对象到文件，参照柱状图生成源码
	        FileOutputStream fos_jpg = null;
	        try {
	            fos_jpg = new FileOutputStream(path);  
	        //    fos_jpg.renameTo(new File(path));//如果目标文件夹不存在，或是目标文件夹已存在，就不会成功，返回false，但不报错。
	            ChartUtilities.writeChartAsJPEG(fos_jpg, 1.0f, chart, 400, 300, 
	                    null);
	        } finally {
	            try {
	                fos_jpg.close();
	            } catch (Exception e) {
	            }
	        }
        ActionContext.getContext().getSession().put("pieChart", path);  
	}
	

}
