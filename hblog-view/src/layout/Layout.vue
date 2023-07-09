<template>
  <el-container style="height: 100vh">
    <el-header class="header">
      <HeaderBar/>
    </el-header>
    <el-main>
      <Main/>
    </el-main>
    <el-footer class="footer">
      <Footer/>
    </el-footer>
  </el-container>
</template>

<script lang="ts" setup>
import HeaderBar from "./components/HeaderBar.vue"
import Main from "@/layout/components/Main.vue"
import Footer from "@/layout/components/Footer.vue"

</script>

<style scoped>
.header {
  padding: 0px;
}

.footer {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>