<script setup lang="ts"></script>

<template>
  <el-tabs type="border-card" class="demo-tabs">
    <el-tab-pane>
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><calendar /></el-icon>
          <span>Route</span>
        </span>
      </template>
      Route
    </el-tab-pane>

    <el-tab-pane label="Config">
      <!-- 输入框 -->
      <el-input 
        v-model="inputValue" 
        style="width: 240px" 
        placeholder="Please input"
      />
     <!-- 可选：显示输入的值，验证双向绑定是否生效 -->
    <p>你输入的内容：{{ inputValue }}</p>
    <!-- 发送按钮 -->
    <el-button 
      type="primary" 
      style="margin-left: 10px"
      @click="sendInputToBackend"
    >
      查询
    </el-button>
    
    </el-tab-pane>
    <el-tab-pane label="Role">Role</el-tab-pane>
    <el-tab-pane label="Task">Task</el-tab-pane>
  </el-tabs>
</template>

<script lang="ts" setup>
import { Calendar } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
// 必须导入 ref（响应式核心）
import { ref } from 'vue';
import axios from 'axios';

// 1. 定义响应式变量，绑定输入框（注意：避免用 input 作为变量名，可能冲突）
const inputValue = ref('');

// 2. 定义发送请求的方法
const sendInputToBackend = async () => {
  
  // 先校验输入内容（非空校验）
  if (!inputValue.value.trim()) {
    ElMessage.warning('请输入内容后再发送！');
    return;
  }

    const response = await axios.get('/api/input/send', {
      params: { content: inputValue.value } // 关键：params 传参
    });
};

</script>

<style>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
.demo-tabs .custom-tabs-label .el-icon {
  vertical-align: middle;
}
.demo-tabs .custom-tabs-label span {
  vertical-align: middle;
  margin-left: 4px;
}
</style>
