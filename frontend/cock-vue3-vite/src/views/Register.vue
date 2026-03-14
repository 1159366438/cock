<template>
  <div class="login-container">
    <div class="login-form">
      <div class="login-header">
        <h2>{{ registerTitle }}</h2>
      </div>
      <el-form 
        :model="registerForm" 
        :rules="registerRules" 
        ref="registerFormRef"
        class="form-content"
      >
        <el-form-item prop="username" :label="usernameLabel">
          <el-input 
            v-model="registerForm.username" 
            :placeholder="usernamePlaceholder"
            :prefix-icon="User"
          />
        </el-form-item>
        
        <el-form-item prop="password" :label="passwordLabel">
          <el-input 
            v-model="registerForm.password" 
            type="password"
            :placeholder="passwordPlaceholder"
            :prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        
        <el-form-item prop="confirmPassword" :label="confirmPasswordLabel">
          <el-input 
            v-model="registerForm.confirmPassword" 
            type="password"
            :placeholder="confirmPasswordPlaceholder"
            :prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        
        <el-form-item prop="age" :label="ageLabel" v-if="showAgeField">
          <el-input-number 
            v-model="registerForm.age" 
            :min="USER_CONSTANTS.AGE_LIMITS.MIN" 
            :max="USER_CONSTANTS.AGE_LIMITS.MAX" 
            :placeholder="agePlaceholder"
            class="full-width"
          />
        </el-form-item>
        
        <el-form-item prop="avatar" :label="avatarLabel" v-if="showAvatarField">
          <div class="avatar-selection">
            <div class="selected-avatar-preview">
              <img v-if="registerForm.avatar" :src="getAvatarUrl(registerForm.avatar)" :alt="'Selected Avatar'" class="selected-avatar" />
              <el-icon v-else class="avatar-placeholder">
                <User />
              </el-icon>
            </div>
            <div class="avatar-options">
              <span class="avatar-option" 
                    v-for="(avatar, index) in availableAvatars" 
                    :key="index"
                    :class="{ selected: registerForm.avatar === avatar }"
                    @click="selectAvatar(avatar)">
                <img :src="getAvatarUrl(avatar)" :alt="`Avatar ${index + 1}`" class="option-avatar" />
              </span>
            </div>
          </div>
        </el-form-item>
        
        <el-form-item prop="gender" :label="genderLabel" v-if="showGenderField">
          <el-radio-group v-model="registerForm.gender">
            <el-radio :label="USER_CONSTANTS.GENDER.MALE">{{ maleLabel }}</el-radio>
            <el-radio :label="USER_CONSTANTS.GENDER.FEMALE">{{ femaleLabel }}</el-radio>
            <el-radio :label="USER_CONSTANTS.GENDER.UNKNOWN">{{ unknownLabel }}</el-radio>
          </el-radio-group>
        </el-form-item>
        
        <el-form-item>
          <el-checkbox v-model="acceptTerms">{{ acceptTermsLabel }}</el-checkbox>
        </el-form-item>
        
        <el-form-item>
          <el-button 
            type="primary" 
            @click="handleRegister" 
            :loading="loading" 
            class="login-button"
          >
            {{ registerButtonLabel }}
          </el-button>
        </el-form-item>
      </el-form>
      
      <div class="login-footer">
        <el-link type="primary" @click="handleBackToLogin">{{ backToLoginLabel }}</el-link>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../store'
import { ElMessage } from 'element-plus'
import { MESSAGE_CONSTANTS } from '../constants/messages'
import { LOGIN_CONSTANTS } from '../constants/login'
import { ROUTE_CONSTANTS } from '../constants/routeConstants'
import { USER_CONSTANTS } from '../constants/userConstants'
import { STORAGE_VALUES } from '../constants/storageValues'
import { BOOLEAN_CONSTANTS } from '../constants/booleans'
import { FORM_VALIDATION_CONSTANTS } from '../constants/formValidation'

// 导入样式文件
import '../assets/css/login.css'
import '../assets/css/register.css'

// 路由和状态管理
const router = useRouter()
const userStore = useUserStore()

// 响应式数据
const registerForm = reactive({
  username: '',
  password: '',
  confirmPassword: '',
  age: undefined as number | undefined,
  avatar: '',
  gender: USER_CONSTANTS.GENDER.UNKNOWN as number | undefined,
})
const acceptTerms = ref(BOOLEAN_CONSTANTS.FALSE)
const loading = ref(BOOLEAN_CONSTANTS.FALSE)
const registerFormRef = ref()

// 可用头像列表
const availableAvatars = [
  'https://ui-avatars.com/api/?name=A&background=0ea5e9&color=fff&size=128',
  'https://ui-avatars.com/api/?name=B&background=8b5cf6&color=fff&size=128',
  'https://ui-avatars.com/api/?name=C&background=ec4899&color=fff&size=128',
  'https://ui-avatars.com/api/?name=D&background=f97316&color=fff&size=128',
  'https://ui-avatars.com/api/?name=E&background=22c55e&color=fff&size=128'
]

// 计算属性
const registerTitle = computed(() => LOGIN_CONSTANTS.TEXTS.REGISTER_TITLE())
const usernameLabel = computed(() => LOGIN_CONSTANTS.TEXTS.USERNAME_LABEL())
const passwordLabel = computed(() => LOGIN_CONSTANTS.TEXTS.PASSWORD_LABEL())
const confirmPasswordLabel = computed(() => LOGIN_CONSTANTS.TEXTS.CONFIRM_PASSWORD_LABEL())
const ageLabel = computed(() => LOGIN_CONSTANTS.TEXTS.AGE_LABEL())
const avatarLabel = computed(() => LOGIN_CONSTANTS.TEXTS.AVATAR_LABEL())
const genderLabel = computed(() => LOGIN_CONSTANTS.TEXTS.GENDER_LABEL())
const maleLabel = computed(() => LOGIN_CONSTANTS.TEXTS.MALE_LABEL())
const femaleLabel = computed(() => LOGIN_CONSTANTS.TEXTS.FEMALE_LABEL())
const unknownLabel = computed(() => LOGIN_CONSTANTS.TEXTS.UNKNOWN_LABEL())
  const usernamePlaceholder = computed(() => LOGIN_CONSTANTS.TEXTS.USERNAME_PLACEHOLDER())
const passwordPlaceholder = computed(() => LOGIN_CONSTANTS.TEXTS.PASSWORD_PLACEHOLDER())
const confirmPasswordPlaceholder = computed(() => LOGIN_CONSTANTS.TEXTS.CONFIRM_PASSWORD_PLACEHOLDER())
const agePlaceholder = computed(() => LOGIN_CONSTANTS.TEXTS.AGE_PLACEHOLDER())

const acceptTermsLabel = computed(() => LOGIN_CONSTANTS.TEXTS.ACCEPT_TERMS())
const registerButtonLabel = computed(() => LOGIN_CONSTANTS.TEXTS.REGISTER_BUTTON())
const backToLoginLabel = computed(() => LOGIN_CONSTANTS.TEXTS.BACK_TO_LOGIN())

// 显示选项
const showAgeField = computed(() => LOGIN_CONSTANTS.FEATURE_FLAGS.SHOW_AGE_FIELD())
const showAvatarField = computed(() => LOGIN_CONSTANTS.FEATURE_FLAGS.SHOW_AVATAR_FIELD())
const showGenderField = computed(() => LOGIN_CONSTANTS.FEATURE_FLAGS.SHOW_GENDER_FIELD())

// 表单验证规则
const validateUsername = (_rule: any, value: any, callback: any) => {
  if (!value) {
    callback(new Error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.USERNAME_REQUIRED()))
  } else if (value.length < 3) {
    callback(new Error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.USERNAME_TOO_SHORT()))
  } else if (value.length > 50) {
    callback(new Error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.USERNAME_TOO_LONG()))
  } else {
    callback()
  }
}

const validatePassword = (_rule: any, value: any, callback: any) => {
  if (!value) {
    callback(new Error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.PASSWORD_REQUIRED()))
  } else if (value.length < 6) {
    callback(new Error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.PASSWORD_TOO_SHORT()))
  } else {
    callback()
  }
}

const validateConfirmPassword = (_rule: any, value: any, callback: any) => {
  if (!value) {
    callback(new Error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.CONFIRM_PASSWORD_REQUIRED()))
  } else if (value !== registerForm.password) {
    callback(new Error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.PASSWORD_MISMATCH()))
  } else {
    callback()
  }
}

const validateGender = (_rule: any, value: any, callback: any) => {
  if (value === undefined || value === null) {
    callback(new Error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.GENDER_REQUIRED()))
  } else if (![USER_CONSTANTS.GENDER.UNKNOWN, USER_CONSTANTS.GENDER.MALE, USER_CONSTANTS.GENDER.FEMALE].includes(value)) {
    callback(new Error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.GENDER_INVALID()))
  } else {
    callback()
  }
}

const registerRules = reactive({
  username: [
    { validator: validateUsername, trigger: FORM_VALIDATION_CONSTANTS.TRIGGERS.BLUR },
    { min: 3, max: 50, message: LOGIN_CONSTANTS.VALIDATION_MESSAGES.USERNAME_LENGTH(), trigger: FORM_VALIDATION_CONSTANTS.TRIGGERS.CHANGE }
  ],
  password: [
    { validator: validatePassword, trigger: FORM_VALIDATION_CONSTANTS.TRIGGERS.BLUR }
  ],
  confirmPassword: [
    { validator: validateConfirmPassword, trigger: FORM_VALIDATION_CONSTANTS.TRIGGERS.BLUR }
  ],
  age: [
    { type: USER_CONSTANTS.FORM_TYPES.NUMBER, min: USER_CONSTANTS.AGE_LIMITS.MIN, max: USER_CONSTANTS.AGE_LIMITS.MAX, message: LOGIN_CONSTANTS.VALIDATION_MESSAGES.AGE_RANGE(), trigger: FORM_VALIDATION_CONSTANTS.TRIGGERS.CHANGE }
  ],
  gender: [
    { validator: validateGender, trigger: FORM_VALIDATION_CONSTANTS.TRIGGERS.CHANGE }
  ]
})

// 注册处理函数
const handleRegister = async () => {
  try {
    // 检查是否同意条款
    if (!acceptTerms.value) {
      ElMessage.warning(LOGIN_CONSTANTS.VALIDATION_MESSAGES.TERMS_NOT_ACCEPTED())
      return
    }
    
    // 表单验证
    await registerFormRef.value.validate()
    loading.value = BOOLEAN_CONSTANTS.TRUE
    
    // 调用用户存储的注册方法
    const result = await userStore.register(
      registerForm.username, 
      registerForm.password, 
      registerForm.confirmPassword,
      registerForm.age,
      registerForm.avatar
    )
    
    if (result.success) {
      ElMessage.success(MESSAGE_CONSTANTS.USER_INFO.REGISTER_SUCCESS())
      
      // 设置认证状态
      localStorage.setItem(USER_CONSTANTS.STORAGE_KEYS.IS_LOGGED_IN, STORAGE_VALUES.AUTH_STATUS.LOGGED_IN)
      
      // 注册成功后跳转到登录页，让用户登录
      router.push({ path: ROUTE_CONSTANTS.PATHS.AUTH.LOGIN })
    } else {
      ElMessage.error(result.message || LOGIN_CONSTANTS.VALIDATION_MESSAGES.REGISTRATION_FAILED())
    }
  } catch (error) {
    console.error('Registration error:', error)
    ElMessage.error(LOGIN_CONSTANTS.VALIDATION_MESSAGES.VALIDATION_FAILED())
  } finally {
    loading.value = BOOLEAN_CONSTANTS.FALSE
  }
}

// 选择头像
const selectAvatar = (avatar: string) => {
  registerForm.avatar = avatar
}

// 获取头像URL
const getAvatarUrl = (avatar: string) => {
  // 直接返回头像URL
  return avatar
}

// 返回登录页面
const handleBackToLogin = () => {
  router.push({ path: ROUTE_CONSTANTS.PATHS.AUTH.LOGIN })
}
</script>