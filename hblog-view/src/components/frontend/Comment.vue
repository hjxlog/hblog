<template>
  <div class="test">
    <div class="comment-container">
      <el-form :model="form">
        <el-row class="user-info">
          <el-col :span="8">
            <el-input placeholder="昵称" v-model="form.nickname"/>
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
import {ref, reactive, onMounted} from 'vue'
import type {FormInstance, FormRules} from 'element-plus'
import {ElMessage} from "element-plus";
import {addComment} from "@/api/view/comment";
import {addCategory, updateCategory} from "@/api/admin/category";

const input = ref('')
const textarea = ref('')

const form = reactive({
  nickname: '',
  email: '',
  website: '',
  content: '',
  pageType: '1',
  isAdmin: false,
  parentId: -1
})

const onSubmit = async () => {
  const warningMsg = checkData()
  if (warningMsg != '') {
    ElMessage.warning(warningMsg);
    return;
  }
  const res = await addComment(form)
  const commentUser = {
    nickname: form.nickname,
    email: form.email,
    website: form.website,
  }
  const commentUserJSON = JSON.stringify(commentUser);
  localStorage.setItem('commentUser', commentUserJSON);
  form.content = ''
  ElMessage.success(res.msg)
}

// 校验内容
const checkData = () => {
  let warningMsg = ""
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  const email = form.email.trim()
  if (email !== '' && !emailPattern.test(email)) {
    warningMsg += '邮箱格式不正确；'
  }
  if (form.content.trim() === '') {
    warningMsg += '评论内容为空；'
  }
  return warningMsg
}


onMounted(() => {
  // 从 localStorage 中获取存储的 JSON 字符串
  const commentUserJSON = localStorage.getItem('commentUser');
  if (commentUserJSON) {
    // 将 JSON 字符串转换为 JavaScript 对象
    const commentUser = JSON.parse(commentUserJSON);
    form.nickname = commentUser.nickname
    form.email = commentUser.email
    form.website = commentUser.website
  }
})

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
