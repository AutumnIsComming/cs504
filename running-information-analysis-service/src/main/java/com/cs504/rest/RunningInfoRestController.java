package com.cs504.rest;

import com.cs504.domain.RunningInfo;
import com.cs504.service.RunningInfoAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RunningInfoRestController {
    private RunningInfoAnalysisService runningInfoAnalysisService;

    @Autowired
    public RunningInfoRestController(RunningInfoAnalysisService runningInfoAnalysisService) {
        this.runningInfoAnalysisService = runningInfoAnalysisService;
    }

    @RequestMapping(value = "running", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInfo> runningInfos) {
        runningInfoAnalysisService.saveRunningInformation(runningInfos);
    }

    @RequestMapping(value = "purge", method = RequestMethod.DELETE)
    public void purge() {
        runningInfoAnalysisService.deleteAll();
    }

    @RequestMapping(value = "running/userName/{userName}", method = RequestMethod.GET)
    public Page<RunningInfo> findByUserName(@PathVariable String userName, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return runningInfoAnalysisService.findByUserName(userName, new PageRequest(page, size));
    }

    @RequestMapping(value = "running/runningId/{runningId}", method = RequestMethod.GET)
    public Page<RunningInfo> findByRunningId(@PathVariable String runningId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return runningInfoAnalysisService.findByRunningId(runningId, new PageRequest(page, size));
    }

    @Transactional
    @RequestMapping(value = "delete/{runningId}", method = RequestMethod.DELETE)
    public void deleteByRunningId(@PathVariable String runningId) {
        runningInfoAnalysisService.deleteByRunningId(runningId);
    }

    @RequestMapping(value = "running/getAll", method = RequestMethod.GET)
    public Page<RunningInfo> findAllOrderByHealthWarningLevelDesc(@RequestParam(name = "page") int page) {
        return runningInfoAnalysisService.findAllOrderByHealthWarningLevelDesc(new PageRequest(page, 2));
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public RunningInfo update(@RequestBody RunningInfo runningInfo, @PathVariable Long id) {
        runningInfo.setId(id);
        return runningInfoAnalysisService.update(runningInfo);
    }
}
