package com.example.dao;

import com.example.entity.PunchRecord;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 打卡记录(PunchRecord)数据访问层
 */
@Mapper
public interface PunchRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PunchRecord queryById(Integer id);

    /**
     * 查询所有打卡记录
     *
     * @return 打卡记录列表
     */
    List<PunchRecord> queryAll();

    /**
     * 通过用户ID查询打卡记录
     *
     * @param userId 用户ID
     * @return 打卡记录列表
     */
    List<PunchRecord> queryByUserId(Integer userId);

    /**
     * 新增打卡记录
     *
     * @param punchRecord 实例对象
     * @return 影响行数
     */
    int insert(PunchRecord punchRecord);

    /**
     * 修改打卡记录
     *
     * @param punchRecord 实例对象
     * @return 影响行数
     */
    int update(PunchRecord punchRecord);

    /**
     * 分页查询打卡记录
     *
     * @param offset 偏移量
     * @param limit 每页数量
     * @return 打卡记录列表
     */
    List<PunchRecord> queryByPage(int offset, int limit);

    /**
     * 查询总记录数
     *
     * @return 总记录数
     */
    int countAll();

    /**
     * 根据用户ID查询打卡记录总数
     *
     * @param userId 用户ID
     * @return 打卡记录总数
     */
    int countByUserId(Integer userId);

    /**
     * 根据用户ID分页查询打卡记录
     *
     * @param userId 用户ID
     * @param offset 偏移量
     * @param limit 每页数量
     * @return 打卡记录列表
     */
    List<PunchRecord> queryByUserIdAndPage(Integer userId, int offset, int limit);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
}