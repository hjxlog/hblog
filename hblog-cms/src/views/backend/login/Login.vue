<template>
  <el-row justify="center" class="container">
    <el-col :span="6">
      <el-card>
        <template #header>
          <div class="card-header">Welcome</div>
        </template>
        <el-form :model="form" :rules="rules" ref="formRef">
          <el-form-item label="账号" prop="username">
            <el-input v-model="form.username" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm(formRef)" style="width: 100%">登录</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import {reactive, ref} from 'vue'
import {login} from "@/api/admin/login";
import {setToken, setUser} from '../../../utils/storage'
import router from "@/router";
import {ElMessage, FormInstance, FormRules} from "element-plus";

// 规则
const rules = reactive<FormRules>({
  username: [
    {required: true, message: '请输入账号', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ]
})

const form = reactive<User>({
  username: "",
  password: "",
});

const formRef = ref()

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  try {
    const valid = await formEl.validate();
    if (!valid) return false;
    const res = await login(form);
    setToken(res.body.token);
    setUser(JSON.stringify(res.body.user));
    ElMessage.success(res.msg);
    await router.push("/");
  } catch (error) {
  }
}

</script>

<style scoped>
.container {
  height: 100vh;
  align-items: center;
  background-image: url("../../../assets/login-bg.jpeg");
}
</style>
