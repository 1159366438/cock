/**
 * 国际化资源管理
 */

export const zhCN = {
  // 菜单相关
  menu: {
    system: '员工内网',
    systemName: '考勤系统',
    today: '今日打卡',
    record: '打卡记录',
    '2': '导航二',
    '3': '导航三',
    '4': '导航四'
  },

  // 记录表格相关
  record: {
    date: '日期',
    name: '姓名',
    time: '时间',
    location: '打卡地点'
  },

  // 消息提示
  messages: {
    getUserInfoFailed: '获取用户信息失败',
    getUserInfoError: '获取用户信息时发生错误',
    punchSuccess: '打卡成功',
    punchFailed: '打卡失败！',
    punchError: '打卡时发生错误',
    networkError: '网络异常，请稍后重试',
    invalidUser: '用户信息无效',
    noRecordsFound: '暂无打卡记录',
    unknownLocation: '未知地点',
    fetchUserInfoSuccess: '获取用户信息成功',
    fetchUserInfoFailed: '获取用户信息失败',
    fetchUserInfoError: '获取用户信息时发生错误',
    updateUserInfoSuccess: '更新用户信息成功',
    updateUserInfoFailed: '更新用户信息失败',
    loginSuccess: '登录成功',
    loginFailed: '登录失败',
    logoutSuccess: '退出登录成功',
    logoutFailed: '退出登录失败',
    operationSuccess: '操作成功',
    operationFailed: '操作失败',
    operationError: '操作时发生错误',
    loading: '加载中...',
    noData: '暂无数据',
    confirmDelete: '确认删除吗？',
    deleteSuccess: '删除成功',
    saveSuccess: '保存成功',
    saveFailed: '保存失败',
    fetchRecordsSuccess: '获取打卡记录成功',
    fetchRecordsFailed: '获取打卡记录失败',
    fetchRecordsError: '获取打卡记录时发生错误',
    recordsLoadError: '加载打卡记录出错',
    userPrefix: '用户',
    unknownUser: '未知用户'
  },

  // 页面标题
  pageTitles: {
    punch: '打卡页面',
    record: '打卡记录',
    user: '用户中心',
    setting: '设置'
  },

  // 打卡状态
  punchStatus: {
    punched: '已打卡',
    unpunched: '未打卡',
    success: '正常',
    late: '迟到',
    absent: '缺勤'
  },

  // 按钮文本
  buttons: {
    punchNow: '立即打卡',
    alreadyPunched: '已打卡',
    logout: '退出'
  },

  // 登录页面
  login: {
    title: '用户登录',
    usernameLabel: '用户名',
    passwordLabel: '密码',
    usernamePlaceholder: '请输入用户名',
    passwordPlaceholder: '请输入密码',
    rememberMe: '记住密码',
    button: '登录',
    forgotPassword: '忘记密码',
    register: '注册账户',
    usernameRequired: '请输入用户名',
    usernameLength: '用户名长度至少3位',
    passwordRequired: '请输入密码',
    passwordLength: '密码长度至少6位',
    success: '登录成功',
    failed: '登录失败',
    validationFailed: '请检查输入信息',
    forgotPasswordTip: '请联系管理员重置密码',
    registerTip: '请联系管理员开通账户'
  }
}

export const enUS = {
  // Menu related
  menu: {
    system: 'Employee Network',
    systemName: 'Attendance System',
    today: 'Today Punch',
    record: 'Punch Records',
    '2': 'Navigation Two',
    '3': 'Navigation Three',
    '4': 'Navigation Four'
  },

  // Record table related
  record: {
    date: 'Date',
    name: 'Name',
    time: 'Time'
  },

  // Message prompts
  messages: {
    getUserInfoFailed: 'Failed to get user information',
    getUserInfoError: 'An error occurred while getting user information',
    punchSuccess: 'Punch successful',
    punchFailed: 'Punch failed!',
    punchError: 'An error occurred during punching',
    networkError: 'Network error, please try again later',
    invalidUser: 'Invalid user information',
    noRecordsFound: 'No punch records found',
    unknownLocation: 'Unknown location',
    fetchUserInfoSuccess: 'Successfully fetched user information',
    fetchUserInfoFailed: 'Failed to fetch user information',
    fetchUserInfoError: 'An error occurred while fetching user information',
    updateUserInfoSuccess: 'Successfully updated user information',
    updateUserInfoFailed: 'Failed to update user information',
    loginSuccess: 'Login successful',
    loginFailed: 'Login failed',
    logoutSuccess: 'Logout successful',
    logoutFailed: 'Logout failed',
    operationSuccess: 'Operation successful',
    operationFailed: 'Operation failed',
    operationError: 'An error occurred during operation',
    loading: 'Loading...',
    noData: 'No data available',
    confirmDelete: 'Confirm deletion?',
    deleteSuccess: 'Deleted successfully',
    saveSuccess: 'Saved successfully',
    saveFailed: 'Save failed',
    fetchRecordsSuccess: 'Successfully fetched punch records',
    fetchRecordsFailed: 'Failed to fetch punch records',
    fetchRecordsError: 'An error occurred while fetching punch records',
    recordsLoadError: 'Error loading punch records',
    userPrefix: 'User',
    unknownUser: 'Unknown User'
  },

  // Page titles
  pageTitles: {
    punch: 'Punch Card',
    record: 'Punch Records',
    user: 'User Center',
    setting: 'Settings'
  },

  // Punch status
  punchStatus: {
    punched: 'Punched',
    unpunched: 'Not Punched',
    success: 'Success',
    late: 'Late',
    absent: 'Absent'
  },

  // Button texts
  buttons: {
    punchNow: 'Punch Now',
    alreadyPunched: 'Already Punched',
    logout: 'Logout'
  },

  // Login page
  login: {
    title: 'User Login',
    usernameLabel: 'Username',
    passwordLabel: 'Password',
    usernamePlaceholder: 'Please enter username',
    passwordPlaceholder: 'Please enter password',
    rememberMe: 'Remember Password',
    button: 'Login',
    forgotPassword: 'Forgot Password',
    register: 'Register Account',
    usernameRequired: 'Please enter username',
    usernameLength: 'Username must be at least 3 characters',
    passwordRequired: 'Please enter password',
    passwordLength: 'Password must be at least 6 characters',
    success: 'Login successful',
    failed: 'Login failed',
    validationFailed: 'Please check input information',
    forgotPasswordTip: 'Please contact administrator to reset password',
    registerTip: 'Please contact administrator to activate account'
  }
}

// 默认语言
export const DEFAULT_LOCALE = 'zh-CN'

// 当前语言
let currentLocale = DEFAULT_LOCALE

// 获取当前语言资源
export const getLocaleMessages = () => {
  return currentLocale === 'en-US' ? enUS : zhCN
}

// 设置语言
export const setLocale = (locale: string) => {
  currentLocale = locale
}

// 获取特定消息
export const t = (key: string, fallback?: string) => {
  const keys = key.split('.')
  let result: any = getLocaleMessages()
  
  for (const k of keys) {
    result = result?.[k]
  }
  
  return result || fallback || key
}