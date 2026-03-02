# Vue 3 + TypeScript + Vite

This template should help get you started developing with Vue 3 and TypeScript in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

Learn more about the recommended Project Setup and IDE Support in the [Vue Docs TypeScript Guide](https://vuejs.org/guide/typescript/overview.html#project-setup).

# 下载 Node.js
https://nodejs.cn/download/

# 在管理员权限的 PowerShell 中输入以下命令并回车：
Set-ExecutionPolicy RemoteSigned

# 检查 Node.js 版本（推荐 18.x/20.x LTS 版本）
node -v
# 检查 npm 版本（Node.js 自带，无需单独安装）
npm -v

# 切换国内 npm 镜像
npm config set registry https://registry.npmmirror.com/

# 创建项目（my-vue3-vite 是项目名称，可自定义，比如 vue3-demo）
# npm create vite@latest my-vue3-vite -- --template vue-ts

# 安装 Element Plus 图标库
# 安装 router
# 使用 axios
# 使用 element-plus
# 使用 pinia

npm install @element-plus/icons-vue vue-router@4 axios element-plus pinia

# 进入项目文件夹（注意替换成你的项目名称）
# cd my-vue3-vite

# 安装项目依赖（Vite 项目必须执行这一步）
npm install

# 启动项目
npm run dev

# 项目核心结构：
cock-vue3-vite/
├── public/             # 静态资源
├── src/                # 源代码
│   ├── api/            # API接口封装
│   ├── assets/         # 静态资源
│   ├── components/     # 组件
│   ├── constants/      # 常量定义
│   ├── router/         # 路由配置
│   ├── views/          # 页面视图
│   ├── App.vue         # 根组件
│   ├── main.ts         # 入口文件
│   └── style.css       # 全局样式
├── node_modules/       # 开发依赖
├── .gitignore          # Git忽略配置
├── README.md           # 项目说明
├── index.html          # HTML入口
├── package.json        # 项目配置
├── tsconfig.*.json     # TypeScript配置
└── vite.config.ts      # Vite配置