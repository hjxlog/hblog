<template>
  <el-menu mode="horizontal" :ellipsis="false">
    <el-text class="logo">HJX's Site</el-text>
    <div class="flex-grow"/>
    <el-menu-item index="1" @click="this.$router.push('/')">首页</el-menu-item>
    <el-menu-item index="2" @click="this.$router.push('/category')">分类</el-menu-item>
    <el-menu-item index="3" @click="this.$router.push('/tag')">标签</el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>

</script>

<style scoped>
.logo {
  padding-left: 20px;
}

.flex-grow {
  flex-grow: 1;
}

</style>