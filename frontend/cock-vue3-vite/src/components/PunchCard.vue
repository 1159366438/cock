<template>
  <div class="punch-card">
    <div class="punch-header">
      <h2>今日打卡</h2>
      <span class="date">{{ todayDate }}</span>
    </div>
    <div class="punch-body">
      <el-card class="punch-card-item">
        <div class="punch-status">
          <el-icon size="48" color="#67C23A" v-if="isPunched"><CircleCheck /></el-icon>
          <el-icon size="48" color="#F56C6C" v-else><CircleClose /></el-icon>
          <p>{{ isPunched ? '今日已打卡' : '今日未打卡' }}</p>
        </div>
        <el-button type="primary" size="large" @click="handlePunchIn" v-if="!isPunched">
          立即打卡
        </el-button>
        <el-button type="success" size="large" disabled v-else>
          已打卡（{{ punchedTime }}）
        </el-button>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, getCurrentInstance, computed } from 'vue'
import { CircleCheck, CircleClose } from '@element-plus/icons-vue'
import { usePunchStore } from '../store/punch'
import { useUserStore } from '../store/user'
import { formatDate } from '../utils'
import { PUNCH_CONSTANTS } from '../constants/punch'

// 响应式数据
const todayDate = ref('')
const punchStore = usePunchStore()
const userStore = useUserStore()
const isPunched = computed(() => punchStore.isPunched)
const punchedTime = computed(() => punchStore.punchedTime)

// 格式化今日日期
const formatTodayDate = () => {
  todayDate.value = formatDate(new Date(), 'date')
}

const { proxy } = getCurrentInstance() as any

// 打卡操作
const handlePunchIn = async () => {
  // 确保用户信息已加载
  if (!userStore.userInfo.name) {
    await userStore.fetchUserInfo()
  }
  
  const username = userStore.userInfo.name || '未知用户'
  
  try {
    // 调用store中的打卡方法（已封装API调用）
    const success = await punchStore.punchIn(username)
    
    if (success) {
      proxy.$message.success(PUNCH_CONSTANTS.MESSAGES.SUCCESS)
    } else if (punchStore.error) {
      proxy.$message.error(punchStore.error)
    } else {
      proxy.$message.error(PUNCH_CONSTANTS.MESSAGES.FAILED)
    }
  } catch (error) {
    proxy.$message.error(PUNCH_CONSTANTS.MESSAGES.ERROR)
    console.error('打卡失败:', error)
  }
}

// 初始化
onMounted(() => {
  formatTodayDate()
  // 模拟已打卡状态（实际项目从接口获取）
  // isPunched.value = true
  // punchedTime.value = '09:05:30'
})
</script>

<style scoped>
.punch-card {
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.punch-header {
  margin-bottom: 30px;
  text-align: center;
}

.punch-header h2 {
  font-size: 34px;
  color: #333;
  margin-bottom: 8px;
}

.punch-header .date {
  font-size: 14px;
  color: #666;
}

.punch-body {
  width: 400px;
}

.punch-card-item {
  text-align: center;
  padding: 30px;
}

.punch-status {
  margin-bottom: 20px;
}

.punch-status p {
  margin-top: 10px;
  font-size: 18px;
  color: #333;
}
</style>