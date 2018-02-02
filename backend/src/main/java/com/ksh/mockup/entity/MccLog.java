package com.ksh.mockup.entity;

import lombok.Data;

@Data
public class MccLog {

    private String timeStamp;
    private String componentCode;
    private String programCode;
    private String severityCode;
    private String msgCode;
    private String uuid;
    private String srcMrn;
    private String dstMrn;
    private String logMsg;

    public String getTimeStamp(){
        StringBuilder ts = new StringBuilder();
        ts.append(timeStamp.substring(2,4));
        ts.append("-");
        ts.append(timeStamp.substring(4,6));
        ts.append("-");
        ts.append(timeStamp.substring(6,8));
        ts.append(" ");
        ts.append(timeStamp.substring(8,10));
        ts.append(":");
        ts.append(timeStamp.substring(10,12));
        ts.append(":");
        ts.append(timeStamp.substring(12,14));
        ts.append(" ");
        ts.append(timeStamp.substring(14,17));
        return ts.toString();
    }

    public String getProgramCode(){
        if (programCode.equals("0")) {
            return "DAEMON";
        }else if (programCode.equals("1")) {
            return "GATEWAY";
        }
        return "-";
    }


    public String getComponentCode(){
        if (componentCode.equals("0")) {
            return "CONTROL";
        }else if (componentCode.equals("1")) {
            return "NETWORK";
        }else if (componentCode.equals("2")) {
            return "WRITE";
        }else if (componentCode.equals("3")) {
            return "READ";
        }else if (componentCode.equals("4")) {
            return "ACCESS";
        }
        return "-";
    }


    public String getSeverityCode(){
        if (severityCode.equals("0")) {
            return "FATAL";
        }else if (severityCode.equals("1")) {
            return "ERROR";
        }else if (severityCode.equals("2")) {
            return "WARN";
        }else if (severityCode.equals("3")) {
            return "INFO";
        }else if (severityCode.equals("4")) {
            return "DEBUG";
        }
        return "-";
    }

    public String getMsgCode(){

        StringBuilder ts = new StringBuilder();
        ts.append("[");
        ts.append(msgCode);
        ts.append("]");
        switch (msgCode){
            case "212":
                ts.append("(1->)mcc got a request from mms");
                break;
            case "210":
                ts.append("(2->)mcc pushed to queue");
                break;
            case "213":
                ts.append("(3->)mcc sent a response to mms");
                break;
            case "203":
                ts.append("(<-1)mcc received a request from queue");
                break;
            case "219":
                ts.append("(<-2)mcc sent a request to mms ");
                break;
            case "220":
                ts.append("(<-3)mcc received a response from mms");
                break;
            case "200":
                ts.append("Verify Signed  DATA");
                break;
            case "444":
                ts.append("UNAUTHORIZED DATA");
                break;
            case "445":
                ts.append("the dstMRN is not the  same as  MRN in VerifySignedData");
                break;
        }
        return ts.toString();
    }

}
