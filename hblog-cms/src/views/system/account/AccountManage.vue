<template>
  <el-text class="title">基本信息</el-text>
  <el-row style="margin-top: 20px;">
    <el-col :span="8">
      <el-form :model="userForm" ref="userFormRef" label-width="50px">
        <el-form-item label="账号">
          <el-input disabled v-model="curUser.username"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input :disabled="canEditNickname" v-model="userForm.nickname" clearable @clear="cancelEditNickname">
            <template #append>
              <el-button v-show="canEditNickname" icon="Edit" @click="editNick"/>
              <el-button v-show="!canEditNickname" icon="Finished" @click="saveUserInfo"/>
            </template>
          </el-input>
        </el-form-item>
      </el-form>
      <el-button type="primary" link @click="showEditPassword">更改密码</el-button>
    </el-col>
  </el-row>
  <!-- 更改密码弹窗 -->
  <el-dialog :model-value="editPasswordDialogVisible" title="更改密码" width="30%" @close="handleClosePasswordEdit(passwordFormRef)">
    <el-form :model="passwordForm" ref="passwordFormRef" :rules="passwordRules" label-width="93px">
      <el-form-item label="输入旧密码" prop="oldPassword">
        <el-input v-model="passwordForm.oldPassword" type="password"/>
      </el-form-item>
      <el-form-item label="输入新密码" prop="newPassword">
        <el-input v-model="passwordForm.newPassword" type="password"/>
      </el-form-item>
      <el-form-item label="确认新密码" prop="confirmPassword">
        <el-input v-model="passwordForm.confirmPassword" type="password"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editPasswordDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSavePassword(passwordFormRef)">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref, toRefs} from 'vue';
import {getCurUser, setUser} from "@/utils/storage";
import {ElMessage, FormInstance, FormRules} from "element-plus";
import {updatePassword, updateUserInfo} from "@/api/user";
import {logout} from "@/api/login";
import router from "@/router";

// 当前用户
let curUser = reactive<User>({});

// 账户基本信息
const userForm = reactive<UserInfoDto>({
  id: 0,
  nickname: ''
});
const getUserInfo = () => {
  curUser = getCurUser()
  userForm.id = curUser.id || 0
  userForm.nickname = curUser.nickname || ''
  passwordForm.id = curUser.id || 0
}

// 昵称编辑
const canEditNickname = ref(true)
const editNick = () => {
  canEditNickname.value = false
}
const cancelEditNickname = () => {
  userForm.nickname = curUser.nickname || ''
  canEditNickname.value = true
}
const saveUserInfo = async () => {
  try {
    const res: any = await updateUserInfo(userForm);
    setUser(JSON.stringify(res.body));
    canEditNickname.value = true
    ElMessage.success(res.msg)
  } catch (error) {
  }
}

// 编辑密码
const editPasswordDialogVisible = ref(false)
const passwordForm = reactive<UserUpdatePasswordDto>({})
const passwordFormRef = toRefs(passwordForm)
const showEditPassword = () => {
  editPasswordDialogVisible.value = true;
}
const validatePass = (rule: any, value: any, callback: any) => {
  if (value !== passwordForm.newPassword) {
    callback(new Error("两次新密码输入不一致。"))
  } else {
    callback()
  }
}
const passwordRules = reactive<FormRules<typeof ruleForm>>({
  oldPassword: [
    {required: true, message: '请输入旧密码', trigger: 'blur'}
  ],
  newPassword: [
    {required: true, message: '请输入新密码', trigger: 'blur'}
  ],
  confirmPassword: [
    {required: true, message: '请确认新密码', trigger: 'blur'},
    {validator: validatePass, trigger: 'blur'}
  ]
})
const handleSavePassword = async (formEl: FormInstance) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (valid) {
      try {
        await updatePassword(passwordForm)
        const res = await logout();
        localStorage.removeItem('token')
        ElMessage.success(res.msg)
        await router.push("/login");
      } catch (error) {
      }
    } else {
      return false
    }
  })
}

const handleClosePasswordEdit = (formEl: FormInstance) => {
  editPasswordDialogVisible.value = false
  formEl.resetFields()
}

onMounted(() => {
  getUserInfo()
});
</script>

<style>
.title {
  font-size: 1.2em;
}
</style>
