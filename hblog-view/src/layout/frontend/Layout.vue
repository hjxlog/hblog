<template>
  <el-container>
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
import HeaderBar from "@/components/frontend/HeaderBar.vue"
import Main from "@/layout/frontend/components/Main.vue"
import Footer from "@/components/frontend/Footer.vue"

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
