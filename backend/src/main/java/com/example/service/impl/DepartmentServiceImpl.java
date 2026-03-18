package com.example.service.impl;

import com.example.common.PageResult;
import com.example.dao.DepartmentDao;
import com.example.dto.CreateDepartmentRequest;
import com.example.dto.QueryDepartmentRequest;
import com.example.dto.UpdateDepartmentRequest;
import com.example.entity.Department;
import com.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门业务逻辑实现类
 * @author Attendance System Team
 * @since 2026-03-18
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    @Transactional
    public Department createDepartment(CreateDepartmentRequest request) {
        // 检查部门名称是否已存在
        List<Department> existingDepartments = departmentDao.selectByCondition(request.getName());
        for (Department dept : existingDepartments) {
            if (dept.getIsDeleted() == 0 && dept.getName().equals(request.getName())) {
                throw new RuntimeException("部门名称已存在");
            }
        }

        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setManagerId(request.getManagerId());

        department.setCreateTime(LocalDateTime.now());
        department.setUpdateTime(LocalDateTime.now());
        department.setIsDeleted(0); // 未删除

        departmentDao.insert(department);
        return department;
    }

    @Override
    @Transactional
    public Department updateDepartment(UpdateDepartmentRequest request) {
        Department existingDepartment = departmentDao.selectById(request.getId());
        if (existingDepartment == null || existingDepartment.getIsDeleted() == 1) {
            throw new RuntimeException("部门不存在");
        }

        // 如果更新了部门名称，检查新名称是否已存在
        if (request.getName() != null && !request.getName().equals(existingDepartment.getName())) {
            List<Department> existingDepartments = departmentDao.selectByCondition(request.getName());
            for (Department dept : existingDepartments) {
                if (dept.getIsDeleted() == 0 && dept.getId() != request.getId() && dept.getName().equals(request.getName())) {
                    throw new RuntimeException("部门名称已存在");
                }
            }
        }

        Department department = new Department();
        department.setId(request.getId());
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setManagerId(request.getManagerId());

        department.setUpdateTime(LocalDateTime.now());

        departmentDao.updateById(department);
        return department;
    }

    @Override
    @Transactional
    public Boolean deleteDepartment(Integer id) {
        Department department = departmentDao.selectById(id);
        if (department == null || department.getIsDeleted() == 1) {
            return false;
        }

        // 逻辑删除
        return departmentDao.updateToDelete(id) > 0;
    }

    @Override
    public Department getDepartmentById(Integer id) {
        Department department = departmentDao.selectById(id);
        if (department != null && department.getIsDeleted() == 1) {
            return null; // 如果已被逻辑删除，则返回null
        }
        return department;
    }

    @Override
    public PageResult<Department> getDepartmentList(QueryDepartmentRequest request) {
        // 计算总数
        int total = departmentDao.countByCondition(request.getName());
        
        // 获取分页数据
        // 注意：这里需要根据实际的分页逻辑实现，可能需要额外的查询方法
        
        // 暂时返回一个简化版本
        List<Department> departments = departmentDao.selectByCondition(request.getName());
        
        // 过滤掉已删除的记录
        departments.removeIf(dept -> dept.getIsDeleted() == 1);
        
        return new PageResult<>(
                departments,
                (long)total,
                request.getPage(),
                request.getSize(),
                (long)(Math.ceil(total / (double)request.getSize()))
        );
    }
    
    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.selectAll();
    }
}