package com.cs504.service.impl;

import com.cs504.domain.RunningInfo;
import com.cs504.domain.RunningInfoRepository;
import com.cs504.service.RunningInfoAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RunningInfoAnalysisServiceImpl implements RunningInfoAnalysisService {
    private RunningInfoRepository runningInfoRepository;

    @Autowired
    public RunningInfoAnalysisServiceImpl(RunningInfoRepository runningInfoRepository) {
        this.runningInfoRepository = runningInfoRepository;
    }

    @Override
    public List<RunningInfo> saveRunningInformation(List<RunningInfo> runningInfoList) {
        for (RunningInfo runningInfo : runningInfoList) {
            runningInfo.setHealthWarningLevel(getHealthWarningLevel(runningInfo));
        }

        return runningInfoRepository.save(runningInfoList);
    }

    private RunningInfo.HealthWarningLevel getHealthWarningLevel(RunningInfo runningInfo) {
        Random random = new Random();
        int heartRate = random.nextInt(140) + 60;
        runningInfo.setHeartRate(heartRate);
        RunningInfo.HealthWarningLevel healthWarningLevel = heartRate <= 75 ? RunningInfo.HealthWarningLevel.LOW :
                (heartRate > 120 ? RunningInfo.HealthWarningLevel.HIGH : RunningInfo.HealthWarningLevel.NORMAL);
        return healthWarningLevel;
    }

    @Override
    public void deleteAll() {
        runningInfoRepository.deleteAll();
    }

    @Override
    public Page<RunningInfo> findByUserName(String userName, Pageable pageable) {
        return runningInfoRepository.findByUserInfoUserName(userName, pageable);
    }

    @Override
    public Page<RunningInfo> findByRunningId(String runningId, Pageable pageable) {
        return runningInfoRepository.findRunningInfoByRunningId(runningId, pageable);
    }

    @Override
    public void deleteByRunningId(String runningId) {
        runningInfoRepository.deleteRunningInfoByRunningId(runningId);
    }

    @Override
    public Page<RunningInfo> findAllOrderByHealthWarningLevelDesc(Pageable pageable) {
        return runningInfoRepository.findAllByHealthWarningLevelNotNullOrderByHealthWarningLevelDesc(pageable);
    }

    @Override
    public RunningInfo update(RunningInfo runningInfo) {
        runningInfo.setHealthWarningLevel(getHealthWarningLevel(runningInfo));
        if (runningInfo.getUserInfo().getId() == null) {
            Long id = findById(runningInfo.getId()).getUserInfo().getId();
            runningInfo.getUserInfo().setId(id);
        }
        return runningInfoRepository.save(runningInfo);
    }

    @Override
    public RunningInfo findById(Long id) {
        return runningInfoRepository.findById(id);
    }
}
