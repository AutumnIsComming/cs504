package com.cs504.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface RunningInfoRepository extends JpaRepository<RunningInfo, Long> {
    Page<RunningInfo> findRunningInfoByRunningId(@Param("runningId") String runningId, Pageable pageable);

    Page<RunningInfo> findByUserInfoUserName(@Param("userName") String userName, Pageable pageable);

    Page<RunningInfo> findAllByHealthWarningLevelNotNullOrderByHealthWarningLevelDesc(Pageable pageable);

    void deleteRunningInfoByRunningId(@Param("runningId") String runningId);

    RunningInfo findById(@Param("id") Long id);
}
