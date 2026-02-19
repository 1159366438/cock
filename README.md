# Vue 3 + TypeScript + Vite

This template should help get you started developing with Vue 3 and TypeScript in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

Learn more about the recommended Project Setup and IDE Support in the [Vue Docs TypeScript Guide](https://vuejs.org/guide/typescript/overview.html#project-setup).

# 员工打卡项目
# 前后端分离
# 前段暂定技术
    axios        : 1.13.5,
    element-plus : 2.13.2,
    vue          : 3.5.25,
    vue-router   : 4.6.4
# 后端暂定技术
    Springboot4  : 4.0.2
    mybatis      : 3.0.5
    mysql        : 无需手动指定

# 下载 Node.js
https://nodejs.cn/download/

# 在管理员权限的 PowerShell 中输入以下命令并回车：
Set-ExecutionPolicy RemoteSigned

# 创建项目（my-vue3-vite 是项目名称，可自定义，比如 vue3-demo）
# npm create vite@latest my-vue3-vite -- --template vue-ts

# 检查 Node.js 版本（推荐 18.x/20.x LTS 版本）
node -v
# 检查 npm 版本（Node.js 自带，无需单独安装）
npm -v

# 切换国内 npm 镜像
npm config set registry https://registry.npmmirror.com/

# 安装 Element Plus 图标库
# 安装 router
# 使用 axios
# 使用 element-plus

npm install @element-plus/icons-vue vue-router@4 axios element-plus

# 进入项目文件夹（注意替换成你的项目名称）
# cd my-vue3-vite

# 安装项目依赖（Vite 项目必须执行这一步）
npm install

# 启动项目
npm run dev

# 项目核心结构：
my-vue3-vite/
├── node_modules/    # 依赖包（npm install 生成）
├── src/             # 源码目录（开发主要写这里）
│   ├── assets/      # 静态资源（图片、样式）
│   ├── components/  # 组件目录
│   ├── App.vue      # 根组件
│   └── main.ts      # 入口文件（Vue 3 特有 createApp 写法）
├── index.html       # Vite 入口 HTML（区别于 Vue CLI）
├── package.json     # 项目配置（包含依赖、脚本）
└── vite.config.ts   # Vite 配置文件（可配置代理、端口等）
