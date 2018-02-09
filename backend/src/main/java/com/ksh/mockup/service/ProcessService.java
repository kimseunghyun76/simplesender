package com.ksh.mockup.service;

import com.ksh.mockup.entity.MccProcess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Service
public class ProcessService {

    @Value("${mcc.elasticsearch.executecmd}")
    private String elasticsearchCmd;
    @Value("${mcc.elasticsearch.executepath}")
    private String elasticsearchPath;

    @Value("${mcc.logstash.executecmd}")
    private String logstashCmd;
    @Value("${mcc.logstash.executepath}")
    private String logstashPath;

    @Value("${mcc.filebeat.executecmd}")
    private String filebeatCmd;
    @Value("${mcc.filebeat.executepath}")
    private String filebeatPath;

    @Value("${mcc.kibana.executecmd}")
    private String kibanaCmd;
    @Value("${mcc.kibana.executepath}")
    private String kibanaPath;

    @Value("${mcc.mccgateway.executecmd}")
    private String mccgatewayCmd;
    @Value("${mcc.mccgateway.executepath}")
    private String mccgatewayPath;

    @Value("${mcc.mccdaemon.executecmd}")
    private String mccdaemonCmd;
    @Value("${mcc.mccdaemon.executepath}")
    private String mccdaemonPath;

    @Value("${mcc.rabbitmq.executecmd}")
    private String rabbitmqCmd;
    @Value("${mcc.rabbitmq.executepath}")
    private String rabbitmqPath;

    @Value("${mcc.mysql.executecmd}")
    private String mysqlCmd;
    @Value("${mcc.mysql.executepath}")
    private String mysqlPath;

    public MccProcess getProcessStatus(MccProcess mccProcess) {
        String line= "";
        mccProcess.setStatus("OFF");
        mccProcess.setUpdateDateTime(LocalDateTime.now());
        String filePidCommand ="";
        int loopcnt = 0;
         try{
             if(mccProcess.getIp().equals("")){
                 filePidCommand = "tasklist /fi \"imagename eq "+mccProcess.getName()+"\" /v";
             }else{
                 filePidCommand = "netstat -aon | findstr " + mccProcess.getIp() +":"+mccProcess.getPort();
             }
            String[] command = {"CMD", "/C",filePidCommand};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                if(mccProcess.getIp().equals("")) {
                    if(loopcnt > 2  && line.length() > 34){
                        mccProcess.setPid(line.substring(26,34).trim());
                        mccProcess.setSessionname(line.substring(35,51).trim());
                        mccProcess.setSession(line.substring(52,64).trim());
                        mccProcess.setUsedmem(line.substring(64,76).trim());
                        mccProcess.setMemusingstatus(line.substring(77,92).trim());
                        mccProcess.setUser(line.substring(93,143).trim());
                        mccProcess.setCputime(line.substring(144,156).trim());
                        mccProcess.setPath(line.substring(157,229).trim());
                        mccProcess.setStatus("ON");
                        break;
                    }
                    loopcnt++;
                }else{
                    mccProcess.setPid(line.trim().split("LISTENING")[1].trim());
                    mccProcess.setStatus("ON");
                    break;
                }
            }
            input.close();
        }catch (Exception e){
            e.printStackTrace();
            mccProcess.setStatus("ERROR");
        }
        return mccProcess;
    }

    public MccProcess killProcess(MccProcess mccProcess) {
        Process process;
        try{
            process = Runtime.getRuntime().exec("taskkill /F /PID " + mccProcess.getPid());
            mccProcess.setStatus("OFF");
        }catch (Exception e){
            e.printStackTrace();
            mccProcess.setStatus("ERROR");
        }
        return mccProcess;
    }

    public MccProcess startProcess(MccProcess mccProcess) {
        File  file = null;
        StringBuilder cmd = new StringBuilder("cmd /c start ");
        if(mccProcess.getName().equals("elasticsearch")){
            cmd.append(elasticsearchCmd);
            file = new File(elasticsearchPath);
        }else if(mccProcess.getName().equals("filebeat.exe")){
            cmd.append(filebeatCmd);
            file = new File(filebeatPath);
        }else if(mccProcess.getName().equals("logstash")){
            cmd.append(logstashCmd);
            file = new File(logstashPath);
        }else if(mccProcess.getName().equals("mccgateway")){
            cmd.append(mccgatewayCmd);
            file = new File(mccgatewayPath);
        }else if(mccProcess.getName().equals("mccdaemon.exe")){
            cmd.append(mccdaemonCmd);
            file = new File(mccdaemonPath);
        }else if(mccProcess.getName().equals("rabbitmq")){
            cmd.append(rabbitmqCmd);
            file = new File(rabbitmqPath);
        }else if(mccProcess.getName().equals("mysql")){
            cmd.append(mysqlCmd);
            file = new File(mysqlPath);
        }else if(mccProcess.getName().equals("kibana")){
            cmd.append(kibanaCmd);
            file = new File(kibanaPath);
        }

        try{
            Runtime.getRuntime().exec(cmd.toString(),null ,file).isAlive();
            mccProcess.setStatus("START");
        }catch (Exception e){
            e.printStackTrace();
            mccProcess.setStatus("ERROR");
        }
        return mccProcess;
    }
}
