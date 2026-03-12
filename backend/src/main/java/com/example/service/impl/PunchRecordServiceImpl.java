package com.example.service.impl;

import com.example.common.ResponseResult;
import com.example.dao.PunchRecordDao;
import com.example.entity.PunchRecord;
import com.example.service.PunchRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
     public ResponseResult<String> performPunchIn(Integer userId) {
         // 验证用户ID
         if (userId == null) {
             return ResponseResult.error(400, "用户ID不能为空");
         }

         // 设置为北京时间
         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         sdf.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Shanghai"));

         // 获取当前时间
         java.util.Date now = new java.util.Date();

         // 创建打卡记录
         PunchRecord punchRecord = new PunchRecord();
         punchRecord.setUserId(userId);
         punchRecord.setCheckInTime(now);
         punchRecord.setCheckInType(1); // 1-上班打卡
         punchRecord.setCheckInStatus(1); // 1-正常
         punchRecord.setCheckInLocation("公司"); // 假设打卡地点为公司

         // 保存打卡记录
         int result = punchIn(punchRecord);

         if (result > 0) {
             return ResponseResult.success("打卡成功");
         } else {
             return ResponseResult.error(500, "打卡失败");
         }
     }

     @Override
     public ResponseResult<Map<String, Object>> getPunchRecords(Integer userId, int page, int size) {
         try {
             // 计算总数
             int total = countByUserId(userId);
             
             // 获取分页数据
             List<PunchRecord> records = queryByUserIdAndPage(userId, page, size);
             
             // 构造响应数据
             Map<String, Object> responseData = new HashMap<>();
             responseData.put("records", records);
             responseData.put("total", total);
             responseData.put("page", page);
             responseData.put("size", size);
             responseData.put("pages", (int) Math.ceil((double) total / size));
             
             return ResponseResult.success(responseData);
         } catch (Exception e) {
             System.err.println("获取打卡记录失败: " + e.getMessage());
             e.printStackTrace();
             return ResponseResult.error(500, "获取打卡记录失败");
         }
     }
}