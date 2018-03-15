package com.ksh.mockup.repository;

import com.ksh.mockup.entity.MccLog;
import com.ksh.mockup.entity.VueTableRequest;
import com.ksh.mockup.entity.VueTableResponse;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ElasticSearchRepository {

    private RestClient client;

    @Autowired
    public ElasticSearchRepository(RestClient client){
        this.client = client;
    }

    public VueTableResponse findMessageWithPrefix(VueTableRequest vueTableRequest) {
        Response response = null;

        String searchValue = vueTableRequest.getSearchvalue();
        String searchName = vueTableRequest.getSearchname();

        String searchProgram = vueTableRequest.getProgram();
        String searchComponent = vueTableRequest.getControl();
        String searchSeverity= vueTableRequest.getSeverity();
        String searchmsgCode= vueTableRequest.getMsgCode();
        String searchSize= Integer.toString(vueTableRequest.getPagesize());

        String startDate= vueTableRequest.getStartDate();
        String endDate= vueTableRequest.getEndDate();

        Map<String, String> params = new HashMap<>();
        params.put("pretty", "true");
        params.put("size", searchSize);

        StringBuilder querydsl = new StringBuilder("{");
        querydsl.append("  \"query\": {");
        querydsl.append("        \"bool\" : { ");
        querydsl.append("            \"must\" : [");
        if("".equals(searchValue) && "".equals(searchProgram) && "".equals(searchComponent) && "".equals(searchSeverity) && "".equals(searchmsgCode)  ) {
            querydsl.append("        {\"exists\" : { ");
            querydsl.append("              \"field\" : \"dstMrn\" ");
            querydsl.append("         }},");

            querydsl.append("        {\"range\" : { ");
            querydsl.append("               \"@timestamp\" : { ");
            querydsl.append("                    \"gte\" : \"");
            querydsl.append(startDate);
            querydsl.append("\",");
            querydsl.append("                    \"lte\" : \"");
            querydsl.append(endDate);
            querydsl.append("\"  }");
            querydsl.append("            }}");
        }else{
            if(!"".equals(searchComponent)){
                querydsl.append("            { \"match\": { \"componentCode\":   \"");
                querydsl.append(searchComponent);
                querydsl.append("\"        }}");
                if(!"".equals(searchProgram) || !"".equals(searchSeverity) || !"".equals(searchValue) ){
                    querydsl.append("           ,");
                }
            }
            if(!"".equals(searchProgram)){
                querydsl.append("            { \"match\": { \"programCode\":   \"");
                querydsl.append(searchProgram);
                querydsl.append("\"        }}");
                if(!"".equals(searchSeverity) || !"".equals(searchValue) ){
                    querydsl.append("           ,");
                }
            }
            if(!"".equals(searchSeverity)){
                querydsl.append("            { \"match\": { \"severityCode\":   \"");
                querydsl.append(searchSeverity);
                querydsl.append("\"        }}");
                if(!"".equals(searchValue) ){
                    querydsl.append("           ,");
                }
            }
            if(!"".equals(searchmsgCode)){
                querydsl.append("            { \"match\": { \"msgCode\":   \"");
                querydsl.append(searchmsgCode);
                querydsl.append("\"        }}");
                if(!"".equals(searchValue) ){
                    querydsl.append("           ,");
                }
            }
            if(!"".equals(searchValue)) {
                querydsl.append("            { \"match\" : { ");
                querydsl.append("               \"");
                querydsl.append(searchName);
                querydsl.append("\" : { ");
                querydsl.append("                    \"query\" : \"");
                querydsl.append(searchValue);
                querydsl.append("\",");
                querydsl.append("                    \"operator\" : \"and\" ");
                querydsl.append("               }");
                querydsl.append("            }}");
            }

            if(!"".equals(startDate)) {
                querydsl.append("            ,{ \"range\" : { ");
                querydsl.append("               \"@timestamp\" : { ");
                querydsl.append("                    \"gte\" : \"");
                querydsl.append(startDate);
                querydsl.append("\",");
                querydsl.append("                    \"lte\" : \"");
                querydsl.append(endDate);
                querydsl.append("\"  }");
                querydsl.append("            }}");
            }
        }

        querydsl.append("         ]");     //must
        querydsl.append("      }");      //bool
        querydsl.append("   }");           //query
        querydsl.append("}") ;
        HttpEntity entity = new NStringEntity(querydsl.toString(), ContentType.APPLICATION_JSON);
        int total= 0;
        List<MccLog> mccLogList = new ArrayList<>();
        try{
            response = client.performRequest("GET", "/mcc/_search",params,  entity);
            String esJson = EntityUtils.toString(response.getEntity());
            if(response != null) {
                //parsing JSON
                try {
                    JSONObject obj  = new JSONObject(esJson);
                    JSONObject hitsObj = obj.getJSONObject("hits");
                    total = hitsObj.getInt("total");
                    if(total > 0) {
                        JSONArray list = hitsObj.getJSONArray("hits");
                        for (int i = list.length() -1 ; 0 <= i; i--) {
                            JSONObject logObj = (JSONObject)list.get(i);
                            JSONObject source = (JSONObject)logObj.get("_source");
                            MccLog mccLog = new MccLog();
                            mccLog.setTimeStamp(source.get("timeStamp").toString());
                            mccLog.setComponentCode(source.get("componentCode").toString());
                            mccLog.setProgramCode(source.get("programCode").toString());
                            mccLog.setSeverityCode(source.get("severityCode").toString());
                            mccLog.setMsgCode(source.get("msgCode").toString());
                            mccLog.setUuid(source.get("uuid").toString());
                            mccLog.setSrcMrn(source.get("srcMrn").toString());
                            mccLog.setDstMrn(source.get("dstMrn").toString());
                            mccLog.setLogMsg(source.get("logMsg").toString());
                            mccLogList.add(mccLog);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return new VueTableResponse(total,mccLogList);
    }


    public boolean deleteAllData() {
        boolean isOk = true;
        try{
            Response response = client.performRequest("DELETE", "/mcc");
        }catch (IOException e){
            isOk = false;
            System.out.println(e.getMessage());
        }
        return isOk;
    }
}
