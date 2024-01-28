<template>
  <div class="test">
    <div class="comment-container">
      <el-row class="user-info">
        <el-col :span="8">
          <el-input v-model="input" placeholder="昵称"/>
        </el-col>
        <el-col :span="8">
          <el-input v-model="input" placeholder="邮箱"/>
        </el-col>
        <el-col :span="8">
          <el-input v-model="input" placeholder="网址（http://）"/>
        </el-col>
      </el-row>
      <el-input
          v-model="textarea"
          :rows="7"
          type="textarea"
          placeholder="你的友好留言会让这里更温馨哦"
          class="content"
      />
      <div class="btn-container">
        <div class="spacer"></div>
        <el-button @click="submit">提交</el-button>
      </div>

    </div>
  </div>

</template>

<script lang="ts" setup>
import {ref} from 'vue'

const input = ref('')
const textarea = ref('')

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
