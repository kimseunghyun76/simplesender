package com.ksh.mockup.controller;

import com.ksh.mockup.entity.MyFile;
import com.ksh.mockup.entity.VueTableRequest;
import com.ksh.mockup.entity.VueTableResponse;
import com.ksh.mockup.repository.ElasticSearchRepository;
import com.ksh.mockup.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MccEsController {

    @Autowired
    private ElasticSearchRepository elasticSearchRepository;

    /**
     * ES 에서 데이터 추출
     * @param vueTableRequest
     * @return
     */
    @CrossOrigin(maxAge = 3600)
    @RequestMapping(path="/MccLogList")
    @ResponseBody()
    public VueTableResponse getESLogResponseList(@RequestBody VueTableRequest vueTableRequest) {
        return elasticSearchRepository.findMessageWithPrefix(vueTableRequest);
    }

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(path="/MccLogDelete")
    @ResponseBody()
    public boolean getESLogAllDelete() {
        return elasticSearchRepository.deleteAllData();
    }


    @Autowired
    FileService fileService;


    @CrossOrigin(maxAge = 3600)
    @RequestMapping(path="/MccMsgcode")
    public String  getMccMsgCode() {
        MyFile mcSvFile = new MyFile();
        mcSvFile.setFileName("msg.json");
        return fileService.getJsonFile(mcSvFile);
    }
}
