<template>
  <el-container class="container">
    <el-aside class="aside" :style="{ width: asideWidth + 'px'}">
      <el-scrollbar>
        <SideBar/>
      </el-scrollbar>
    </el-aside>
    <el-container class="container">
      <el-header class="header">
        <HeaderBar/>
      </el-header>
      <el-main class="main">
        <el-scrollbar>
          <Main/>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import SideBar from "./components/AdminSideBar.vue"
import HeaderBar from "./components/HeaderBar.vue"
import Main from "./components/Main.vue"
import {computed} from 'vue';
import {menuItem} from "@/store/store";

const asideWidth = computed(() => {
  return menuItem.isCollapse ? '64' : '200'
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
