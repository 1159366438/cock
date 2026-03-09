<template>
  <div class="record-card">
    <el-table 
      :data="tableData" 
      stripe 
      style="width: 100%" 
      v-loading="loading"
      :empty-text="noRecordsText"
    >
      <el-table-column :label="dateLabel" width="180">
        <template #default="{ row }">
          {{ formatDateField(row.checkInTime) }}
        </template>
      </el-table-column>
      <el-table-column :label="nameLabel" width="180">
        <template #default="{ row }">
          {{ formatUserId(row.userId) }}
        </template>
      </el-table-column>
      <el-table-column :label="timeLabel" width="180">
        <template #default="{ row }">
          {{ formatTimeField(row.checkInTime) }}
        </template>
      </el-table-column>
      <el-table-column 
        prop="checkInLocation" 
        :label="locationLabel" 
        :formatter="locationFormatter"
      />
    </el-table>
    
    <!-- 分页组件 -->
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[15, 30, 50, 100]"
      :small="false"
      :disabled="false"
      :background="false"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalRecords"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      class="pagination"
    />
  </div>
</template>

<script setup lang="ts">
import { onMounted, computed, onErrorCaptured, ref } from 'vue'
import { usePunchStore, useUserStore } from '../../store'
import { t } from '../../locales'
import { formatDate } from '../../utils'
import type { PunchRecord } from '../../types'
import { ElMessage } from 'element-plus'
import { PUNCH_CONSTANTS } from '../../constants/punch'

// Store
const punchStore = usePunchStore()
const userStore = useUserStore()

// Labels
const dateLabel = t('record.date', 'Date')
const nameLabel = t('record.name', 'Name')
const timeLabel = t('record.time', 'Time')
const locationLabel = t('record.location', '打卡地点')
const noRecordsText = t('messages.noRecordsFound', '暂无打卡记录')

// Computed properties
const tableData = computed<PunchRecord[]>(() => punchStore.pagination.records || [])
const loading = computed(() => punchStore.loading)
const totalRecords = computed(() => punchStore.pagination.total)
const currentPage = computed({
  get: () => punchStore.pagination.page,
  set: (_value) => {
    // 不直接修改，而是在change事件中处理
  }
})
const pageSize = computed({
  get: () => punchStore.pagination.size,
  set: (_value) => {
    // 不直接修改，而是在change事件中处理
  }
})

// Refs
const currentPageRef = ref(1)
const pageSizeRef = ref(15)

// Methods
const formatDateField = (dateValue: Date | string | undefined): string => {
  if (!dateValue) return '-'
  try {
    return formatDate(new Date(dateValue), 'date')
  } catch (err) {
    console.warn('日期格式化错误:', err)
    return '-'
  }
}

const formatTimeField = (dateValue: Date | string | undefined): string => {
  if (!dateValue) return '-'
  try {
    return formatDate(new Date(dateValue), 'time')
  } catch (err) {
    console.warn('时间格式化错误:', err)
    return '-'
  }
}

const formatUserId = (userId: number | undefined): string => {
  if (typeof userId !== 'number') return '-'
  // 如果需要显示用户名而非ID，可以在这里查询用户信息
  return `${t('messages.userPrefix', '用户')}${userId}`
}

const locationFormatter = (_row: PunchRecord, _column: unknown, cellValue: string): string => {
  return cellValue || t('messages.unknownLocation', '未知地点')
}

// 分页大小改变事件
const handleSizeChange = async (size: number) => {
  pageSizeRef.value = size
  currentPageRef.value = 1  // 改变每页大小时回到第一页
  await loadPunchRecords(currentPageRef.value, pageSizeRef.value)
}

// 页码改变事件
const handleCurrentChange = async (page: number) => {
  currentPageRef.value = page
  await loadPunchRecords(currentPageRef.value, pageSizeRef.value)
}

// 加载打卡记录
const loadPunchRecords = async (page: number, size: number) => {
  try {
    // 确保用户信息已加载
    if (!userStore.userInfo.name) {
      await userStore.fetchUserInfo()
    }
    const userId = userStore.userInfo.userId || 1
    const success = await punchStore.fetchPunchRecords(userId, page, size)
    if (success) {
      ElMessage.success(PUNCH_CONSTANTS.MESSAGES.FETCH_RECORDS_SUCCESS())
      console.log('获取打卡记录成功:', {
        total: punchStore.pagination.total,
        currentPage: punchStore.pagination.page,
        recordsCount: punchStore.pagination.records.length
      })
    }
    else if (punchStore.error) {
      console.error('else if获取打卡记录失败:', punchStore.error)
      ElMessage.error(punchStore.error)
    } else {
      ElMessage.error(PUNCH_CONSTANTS.MESSAGES.FETCH_RECORDS_FAILED())
    }
  } catch (err) {
    console.error('获取打卡记录失败:', err)
    ElMessage.error(PUNCH_CONSTANTS.MESSAGES.FETCH_RECORDS_ERROR())
    return false
  }
}

// Lifecycle
onMounted(async () => {
  // 确保用户信息已加载
  if (!userStore.userInfo.name) {
    await userStore.fetchUserInfo()
  }
  await loadPunchRecords(currentPageRef.value, pageSizeRef.value)
})

// Error handling
onErrorCaptured((err) => {
  console.error('RecordCard组件捕获错误:', err)
})
</script>

<style scoped>
.record-card {
  display: flex;
  flex-direction: column;
  min-height: 400px;
}

.pagination {
  margin-top: 20px;
  justify-content: center;
}
</style>