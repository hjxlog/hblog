<template>
  <el-container class="container">
    <el-aside class="aside" :style="{ width: asideWidth + 'px'}">
      <SideBar/>
    </el-aside>
    <el-container class="container">
      <el-header class="header">
        <HeaderBar/>
      </el-header>
      <el-main class="main">
        <Main/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import SideBar from "./AdminSideBar.vue"
import HeaderBar from "./HeaderBar.vue"
import Main from "./Main.vue"
import {computed, onMounted, ref} from 'vue';
import {menuItem} from "@/store/store";

const asideWidth = computed(() => {
  return menuItem.isCollapse ? (menuItem.isMobile ? '0' : '64') : '200'
});

</script>

<style scoped>
.container {
  height: 100%;
}

.aside {
  transition: width 0.3s ease-in-out;
}

.header {
  padding: 0px;
}

</style>
