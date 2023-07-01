<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <SideBar/>
      </el-aside>
      <el-container>
        <el-header class="header">
          <HeaderBar/>
        </el-header>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import SideBar from "./components/SideBar.vue"
import HeaderBar from "./components/HeaderBar.vue"
</script>

<style scoped>
.header {
  padding: 0px;
}
</style>