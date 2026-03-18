/**
 * 部门管理API接口
 * 提供部门增删改查等操作接口
 * @author Attendance System Team
 * @since 2026-03-18
 */
import service from './axios'
import { APP_CONSTANTS } from '../constants'

// 部门相关API接口
const departmentApi = {
  /**
   * 获取部门列表
   */
  getDepartments: (params: {
    name?: string
    page: number
    size: number
  }) => {
    return service.get(APP_CONSTANTS.ROUTE.PATHS.API.DEPARTMENT.LIST, { params })
    
  },

  /**
   * 根据ID获取部门详情
   */
  getDepartmentById: (id: number) => {
    return service.get(`${APP_CONSTANTS.ROUTE.PATHS.API.DEPARTMENT.DETAIL}${id}`)
  },

  /**
   * 创建部门
   */
  createDepartment: (data: {
    name: string
    description?: string
    managerId?: number
  }) => {
    return service.post(`${APP_CONSTANTS.ROUTE.PATHS.API.DEPARTMENT.CREATE}`, data)
  },

  /**
   * 更新部门
   */
  updateDepartment: (id: number, data: {
    name?: string
    description?: string
    managerId?: number
  }) => {
    return service.put(`${APP_CONSTANTS.ROUTE.PATHS.API.DEPARTMENT.UPDATE}${id}`, data)
  },

  /**
   * 删除部门（逻辑删除）
   */
  deleteDepartment: (id: number) => {
    return service.delete(`${APP_CONSTANTS.ROUTE.PATHS.API.DEPARTMENT.DELETE}${id}`)
  }
}

export default departmentApi