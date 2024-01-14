<template>
  <div id="home">
    <HeaderBar/>
    <HomeCover/>
  </div>
  <div class="content">
    <el-row>
      <el-col :span="7" :xs="0">
        <div class="sidebar">
          <OverviewCard/>
          <RecommendBlogCard/>
          <CategoryCard/>
          <TagCard/>
        </div>
      </el-col>
      <el-col :span="17" :xs="24" class="right">
        <BlogList/>
      </el-col>
    </el-row>
  </div>
  <Footer/>
</template>

<script lang="ts" setup>
import HeaderBar from "@/views/frontend/layout/HeaderBar.vue"
import HomeCover from "@/views/frontend/layout/HomeCover.vue"
import OverviewCard from "@/components/OverviewCard.vue"
import RecommendBlogCard from "@/components/RecommendBlogCard.vue"
import CategoryCard from "@/components/CategoryCard.vue"
import TagCard from "@/components/TagCard.vue"
import BlogList from "@/views/frontend/blog/BlogList.vue"
import Footer from "@/views/frontend/layout/Footer.vue"
</script>

<style scoped>
#home {
  height: 100%;
  width: 100%;
}

.content {
  padding: 2% 6%;
  animation: fadeInUp 1s;
}

.sidebar div {
  margin-right: 12px;
  margin-bottom: 20px;
  box-shadow: var(--card-shadow);
}

@media screen and (max-width: 900px) {
  .sidebar {
    display: none;
  }

  .content {
    padding: 0;
  }

  .right {
    padding: 7px;
  }

}
</style>
