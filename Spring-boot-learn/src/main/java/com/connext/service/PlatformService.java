package com.connext.service;

import com.connext.entity.Platform;
import com.connext.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
public class PlatformService extends AbstractDao {

    @Autowired
    private PlatformRepository platformRepository;

    public Platform findByName(String platformName) {
        return platformRepository.findByName(platformName);
    }

    public Platform findUsedByName(String platformName) {
        return platformRepository.findByNameAndIsDeleted(platformName, false);
    }

    public Platform findByNameForBudget(String platformName) {
        return platformRepository.findByNameForBudget(platformName);
    }

    public Page<Platform> findAll(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return platformRepository.findAll(pageable);
    }

    public Page<Platform> findByNameForPage(String platformName, Pageable pageable) {
        return platformRepository.findByName(platformName, pageable);
    }

    @Transactional
    public int insert(String platformName) {

        Platform platform = new Platform();
        platform.setId(UUID.randomUUID().toString());
        platform.setCode(platformName);
        platform.setName(platformName);
        platform.setCreateTime(new Date());
        platform.setModifyTime(new Date());
        platform.setCreateUser("00000000-0000-0000-0000-000000000001");
        platform.setModifyUser("00000000-0000-0000-0000-000000000001");
        platform.setOrder(0);
        platform.setUseForBudget(false);
        platform.setDeleted(false);

        if (platformRepository.countByName(platformName) > 0) {
            throw new RuntimeException("当前平台名称已存在！");
        }
        platformRepository.save(platform);
        return 1;
    }
}

