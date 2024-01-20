<template>
  <div id="home">
    <HeaderBar/>
    <HomeCover/>
  </div>
  <el-row class="main">
    <el-col :span="7" :xs="0">
      <SideBar/>
    </el-col>
    <el-col :span="17" :xs="24">
      <BlogList/>
    </el-col>
  </el-row>
  <Footer/>
</template>

<script lang="ts" setup>
import HeaderBar from "@/components/frontend/HeaderBar.vue"
import HomeCover from "@/components/frontend/HomeCover.vue"
import SideBar from "@/components/frontend/SideBar.vue"
import BlogList from "@/views/frontend/blog/BlogList.vue"
import Footer from "@/components/frontend/Footer.vue"
</script>

<style scoped>
#home {
  height: 100%;
  width: 100%;
}

.main {
  padding: 2% 10%;
  animation: fadeInUp 1s;
}

@media screen and (max-width: 900px) {
  .main {
    padding: 7px;
  }
}
</style>
