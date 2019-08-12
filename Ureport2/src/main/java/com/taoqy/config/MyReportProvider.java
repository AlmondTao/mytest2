package com.taoqy.config;

import com.bstek.ureport.exception.ReportException;
import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/5/26
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
//@Component
public class MyReportProvider implements ReportProvider {
    private String prefix="sanxiangzhidu:";
    private String fileStoreDir = "D:/SANXIANGZHIDU";
    private boolean disabled = false;
    @Override
    public InputStream loadReport(String file) {
        if(file.startsWith(prefix)){
            file=file.substring(prefix.length(),file.length());
        }
        String fullPath=fileStoreDir+"/"+file;
        try {
            return new FileInputStream(fullPath);
        } catch (FileNotFoundException e) {
            throw new ReportException(e);
        }
    }

    @Override
    public void deleteReport(String file) {
        if(file.startsWith(prefix)){
            file=file.substring(prefix.length(),file.length());
        }
        String fullPath=fileStoreDir+"/"+file;
        File f=new File(fullPath);
        if(f.exists()){
            f.delete();
        }
    }

    @Override
    public List<ReportFile> getReportFiles() {
        File file=new File(fileStoreDir);
        List<ReportFile> list=new ArrayList<ReportFile>();
        for(File f:file.listFiles()){
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(f.lastModified());
            list.add(new ReportFile(f.getName(),calendar.getTime()));
        }
        Collections.sort(list, new Comparator<ReportFile>(){
            @Override
            public int compare(ReportFile f1, ReportFile f2) {
                return f2.getUpdateDate().compareTo(f1.getUpdateDate());
            }
        });
        return list;
    }

    @Override
    public String getName() {
        return "三项制度报表模板";
    }

    @Override
    public void saveReport(String file,String content) {
        File fileDoc=new File(fileStoreDir);
        if(!fileDoc.exists()){
            fileDoc.mkdir();
        }
        if(file.startsWith(prefix)){
            file=file.substring(prefix.length(),file.length());
        }
        String fullPath=fileStoreDir+"/"+file;
        FileOutputStream outStream=null;
        try{
            outStream=new FileOutputStream(new File(fullPath));
            IOUtils.write(content, outStream,"utf-8");
        }catch(Exception ex){
            throw new ReportException(ex);
        }finally{
            if(outStream!=null){
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public boolean disabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setFileStoreDir(String fileStoreDir) {
        this.fileStoreDir = fileStoreDir;
    }



    @Override
    public String getPrefix() {
        return prefix;
    }
}
