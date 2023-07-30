<template>
  <el-menu mode="horizontal" :ellipsis="false">
    <el-text class="logo">HJX's Site</el-text>
    <div class="flex-grow"/>
    <el-menu-item index="1" @click="router.push('/')">首页</el-menu-item>
    <el-menu-item index="2" @click="router.push('/category')">分类</el-menu-item>
    <el-menu-item index="3" @click="router.push('/tag')">标签</el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import {useRouter} from "vue-router";

const router = useRouter();
</script>

<style scoped>
.logo {
  padding-left: 20px;
}

.flex-grow {
  flex-grow: 1;
}

</style>