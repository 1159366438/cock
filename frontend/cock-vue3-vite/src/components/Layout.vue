<template>
  <div class="layout-container">
    <!-- 左侧菜单（优化布局结构） -->
    <div class="layout-sidebar"> <!-- 替换el-row/el-col，简化结构+优化布局 -->
      <div class="sidebar-header">
        <h5>{{ getMenuText('system') }}</h5>
      </div>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo sidebar-menu"
        default-active="today"
        text-color="#fff"
        @open="handleOpen"
        @close="handleClose"
        v-model="activeTab"
        @select="handleMenuSelect"
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon class="menu-icon"><Location /></el-icon>
            <span >{{ getMenuText('systemName') }}</span>
          </template>
          <el-menu-item index="today">
            <span @click="updateMenuText('today')" >{{ getMenuText('today') }}</span>
          </el-menu-item>
          <el-menu-item index="record">
            <span @click="updateMenuText('record')" >{{ getMenuText('record') }}</span>
          </el-menu-item>
        </el-sub-menu>
        <el-menu-item index="2">
          <el-icon class="menu-icon"><IconMenu /></el-icon>
          <span>Navigator Two</span>
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon class="menu-icon"><Document /></el-icon>
          <span>Navigator Three</span>
        </el-menu-item>
        <el-menu-item index="4">
          <el-icon class="menu-icon"><Setting /></el-icon>
          <span>Navigator Four</span>
        </el-menu-item>
      </el-menu>
    </div>

    <!-- 右侧内容区（保持不变） -->
    <div class="layout-content">
      <div class="content-header">
        <UserInfo :currentMenuText="currentMenuText" />
      </div>
      <div class="content-body">
        <PunchCard v-if="activeTab === 'today'" />
        <RecordCard v-if="activeTab === 'record'" />
        <div v-if="activeTab !== 'today' && activeTab !== 'record'" class="analysis-content">
          <el-empty description="暂无相关内容" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import UserInfo from './UserInfo.vue'
import PunchCard from './PunchCard.vue'
import RecordCard from './RecordCard.vue'
import { getMenuText, setSafeMenuKey, type MenuKey , type MenuText} from '../constants/menu';

// 注释：如果没有实际的punchApi，可先注释，避免报错
// import { punchApi } from '../api/punchApi'

import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'

// 新增：当前要传递给UserInfo的菜单文本（初始化显示今日打卡）
const currentMenuText = ref<MenuText>(getMenuText('today'));

// 通用方法：更新菜单文本（所有菜单共用这一个方法）
const updateMenuText = (key: MenuKey) => {
  currentMenuText.value = getMenuText(key);
};


const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const activeTab = ref('today');

const handleMenuSelect = (key: string) => {
  console.log('切换到菜单：', key)
  // 安全设置activeTab，解决TS类型报错
  activeTab.value = setSafeMenuKey(key);
  // 选中菜单项时，同步更新菜单文本
  updateMenuText(setSafeMenuKey(key));
}

// 注释：如果没有实际的接口，先注释，避免运行报错
/* const getPunchRecord = async () => {
  try {
    // 模拟接口返回
    punchRecord.value = [
      { date: '2026-02-19', time: '09:05', status: '正常' },
      { date: '2026-02-18', time: '08:58', status: '正常' }
    ]
    // const res = await punchApi.getPunchRecord()
    // if (res.code === 200) {
    //   punchRecord.value = res.data
    // }
  } catch (error) {
    console.error('获取打卡记录失败：', error)
  }
} */

/* watch(activeTab, (newVal) => {
  if (newVal === 'record') {
    getPunchRecord()
  }
}) */

/* onMounted(() => {
  if (activeTab.value === 'record') {
    getPunchRecord()
  }
}) */
</script>

<style scoped>
.layout-container {
  display: flex;
  height: calc(100vh - 20px);
  gap: 10px;
  padding: 10px;
}

/* ========== 左侧菜单核心优化样式 ========== */
.layout-sidebar {
  width: 240px;
  background: #fff;
  border-radius: 8px; /* 圆角加大，更现代 */
  box-shadow: 0 2px 16px 0 rgba(0, 0, 0, 0.08); /* 柔和阴影 */
  overflow: hidden; /* 防止内容溢出圆角 */
  display: flex;
  flex-direction: column;
}

/* 菜单头部标题样式 */
.sidebar-header {
  padding: 16px 20px;
  border-bottom: 1px solid #f5f5f5; /* 分隔线更柔和 */
}

.sidebar-header h5 {
  margin: 0;
  font-size: 16px;
  font-weight: 600;
  color: #333;
}

/* 菜单主体样式 */
.sidebar-menu {
  flex: 1; /* 占满剩余高度 */
  border-right: none !important; /* 去掉Element默认右侧边框 */
  background-color: #545c64 !important; /* 强制继承背景色 */
}

/* 图标间距优化 */
.menu-icon {
  margin-right: 8px; /* 图标和文字间距统一 */
  font-size: 16px;
}

/* 菜单选项内边距优化（更舒适的点击区域） */
:deep(.el-menu-item),
:deep(.el-sub-menu__title) {
  padding-left: 20px !important; /* 统一左内边距 */
  height: 48px !important; /* 加高选项，提升点击体验 */
  line-height: 48px !important; /* 垂直居中 */
}

/* 子菜单选项内边距（缩进更合理） */
:deep(.el-sub-menu .el-menu-item) {
  padding-left: 36px !important;
}

/* 选中态样式强化 */
:deep(.el-menu-item.is-active) {
  background-color: rgba(255, 208, 75, 0.2) !important; /* 选中背景渐变 */
}

/* ========== 右侧内容区样式（少量优化） ========== */
.layout-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  background: #fff;
  border-radius: 8px; /* 和左侧菜单圆角统一 */
  box-shadow: 0 2px 16px 0 rgba(0, 0, 0, 0.08); /* 统一阴影风格 */
  padding: 20px; /* 加大内边距，更透气 */
}

.content-header {
  text-align: right;
  padding-bottom: 16px;
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
}

.content-body {
  flex: 1;
  overflow: auto;
  padding: 0; /* 去掉多余内边距 */
}

.analysis-content {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>