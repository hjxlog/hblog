<template>
  <div class="test">
    <div class="comment-container">
      <el-form :model="form" :rules="rules">
        <el-row class="user-info">
          <el-col :span="8">
            <el-form-item prop="name">
              <el-input placeholder="昵称" v-model="form.nickname"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-input placeholder="邮箱" v-model="form.email"/>
          </el-col>
          <el-col :span="8">
            <el-input placeholder="网址（http://）" v-model="form.website"/>
          </el-col>
        </el-row>
        <el-input
            v-model="form.content"
            :rows="7"
            type="textarea"
            placeholder="你的友好留言会让这里更温馨哦"
            class="content"
        />
        <div class="btn-container">
          <div class="spacer"></div>
          <el-button @click="onSubmit">提交</el-button>
        </div>
      </el-form>

    </div>
  </div>

</template>

<script lang="ts" setup>
import {ref, reactive} from 'vue'
import type {FormInstance, FormRules} from 'element-plus'
import {ElMessage} from "element-plus";

const input = ref('')
const textarea = ref('')

const form = reactive({
  nickname: '',
  email: '',
  website: '',
  content: ''
})

const rules = reactive<FormRules<RuleForm>>({})

const onSubmit = () => {
  console.log('submit!')
  console.log(form)
  // 手动判断邮箱格式
  if (!isValidEmail(form.email)) {
    // 邮箱格式不符合要求，进行相应的提示或处理
    console.log('邮箱格式不正确');
    ElMessage.warning('邮箱格式不正确');
    return;
  }
}

const isValidEmail = (email) => {
  if (email.trim() === '') {
    return '请输入邮箱'
  }
  // 简单的邮箱格式判断，你可以根据实际需求使用正则表达式等更精确的方式
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailPattern.test(email);
};

</script>

<style lang="less" scoped>
.test {
  padding: 100px;
}

.comment-container {
  border-radius: 4px;
  border: 1px solid var(--el-border-color);
  padding: 7px;
}

.user-info {
  padding-bottom: 3px;
  border-bottom: 1px solid var(--el-border-color);
}

.user-info .el-form-item {
  margin-bottom: 0px;
}

:deep(.el-input) {
  .el-input__wrapper {
    border: none;
    border-radius: 0;
    box-shadow: none;
  }
}

::v-deep(.el-textarea) {
  .el-textarea__inner {
    border: none;
    border-radius: 0;
    box-shadow: none;

  }
}


.content {

}

.btn-container {
  display: flex;
  margin-top: 7px;
}

.spacer {
  flex: 1;
}

</style>
