package com.ksh.mockup.controller;

import com.ksh.mockup.entity.MccProcess;
import com.ksh.mockup.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MonitorController {

    @Autowired
    ProcessService processService;

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(path="/status")
    @ResponseBody()
    public MccProcess getProcessStatus(@RequestBody MccProcess mccProcess) {
        return processService.getProcessStatus(mccProcess);
    }

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(path="/pstop")
    @ResponseBody()
    public MccProcess killProcess(@RequestBody MccProcess mccProcess) {
        return processService.killProcess(mccProcess);
    }

    @CrossOrigin(maxAge = 3600)
    @RequestMapping(path="/pstart")
    @ResponseBody()
    public MccProcess startProcess(@RequestBody MccProcess mccProcess) {
        return processService.startProcess(mccProcess);
    }
}
