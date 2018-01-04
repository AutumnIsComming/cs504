package com.cs504.service;

import com.cs504.domain.RunningInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RunningInfoAnalysisService {
    List<RunningInfo> saveRunningInformation(List<RunningInfo> runningInfoList);

    void deleteAll();

    Page<RunningInfo> findByUserName(String userName, Pageable pageable);

    Page<RunningInfo> findByRunningId(String runningId, Pageable pageable);

    void deleteByRunningId(String runningId);

    Page<RunningInfo> findAllOrderByHealthWarningLevelDesc(Pageable pageable);

    RunningInfo update(RunningInfo runningInfo);

    RunningInfo findById(Long id);
}
