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
import HeaderBar from "@/views/frontend/layout/HeaderBar.vue"
import Main from "@/layout/view/components/Main.vue"
import Footer from "@/views/frontend/layout/Footer.vue"

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
