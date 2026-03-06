package com.example.service.impl;

import com.example.dao.PunchRecordDao;
import com.example.entity.PunchRecord;
import com.example.service.PunchRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * 打卡记录服务实现类
 */
@Service
public class PunchRecordServiceImpl implements PunchRecordService {

    @Autowired
    private PunchRecordDao punchRecordDao;

    @Override
    public PunchRecord queryById(Integer id) {
        return punchRecordDao.queryById(id);
    }

    @Override
    public List<PunchRecord> queryAll() {
        return punchRecordDao.queryAll();
    }

    @Override
    public List<PunchRecord> queryByUserId(Integer userId) {
        return punchRecordDao.queryByUserId(userId);
    }

    @Override
    public int insert(PunchRecord punchRecord) {
        // 设置创建时间和更新时间（北京时间）
        Date now = new Date();
        punchRecord.setCreateTime(now);
        punchRecord.setUpdateTime(now);
        return punchRecordDao.insert(punchRecord);
    }

    @Override
    public int update(PunchRecord punchRecord) {
        // 更新修改时间（北京时间）
        Date now = new Date();
        punchRecord.setUpdateTime(now);
        return punchRecordDao.update(punchRecord);
    }

    @Override
    public int deleteById(Integer id) {
        return punchRecordDao.deleteById(id);
    }

    @Override
    public int punchIn(PunchRecord punchRecord) {
        // 执行打卡操作，实际上就是插入一条打卡记录
        return insert(punchRecord);
    }

    @Override
    public List<PunchRecord> queryByPage(int page, int size) {
        int offset = (page - 1) * size;
        return punchRecordDao.queryByPage(offset, size);
    }

    @Override
    public int countAll() {
        return punchRecordDao.countAll();
    }

    @Override
    public int countByUserId(Integer userId) {
        return punchRecordDao.countByUserId(userId);
    }

    @Override
    public List<PunchRecord> queryByUserIdAndPage(Integer userId, int page, int size) {
        int offset = (page - 1) * size;
        return punchRecordDao.queryByUserIdAndPage(userId, offset, size);
    }
}