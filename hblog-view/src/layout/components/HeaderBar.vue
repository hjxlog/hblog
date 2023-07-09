<template>
  <el-menu mode="horizontal" :ellipsis="false">
    <el-text class="logo">HJX's Site</el-text>
    <div class="flex-grow"/>
    <el-menu-item index="1">首页</el-menu-item>
    <el-menu-item index="2">分类</el-menu-item>
    <el-menu-item index="3">标签</el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import router from "@/router";
import {ElMessage} from "element-plus";

</script>

<style scoped>
.logo {
  padding-left: 20px;
}

.flex-grow {
  flex-grow: 1;
}

</style>