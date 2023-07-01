<template>
  <el-row>
    <el-col :span="24">
      <el-menu mode="horizontal" :ellipsis="false">
        <div class="flex-grow"/>
        <el-sub-menu index="1">
          <template #title>管理员</template>
          <el-menu-item index="2-1">更改密码</el-menu-item>
          <el-menu-item index="2-2">退出登陆</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
</script>

<style scoped>
.flex-grow {
  flex-grow: 1;
}
</style>