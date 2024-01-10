<template>
  <el-card shadow="hover">
    <div class="card-title">推荐文章</div>
    <div class="card-container" v-for="blog in recommendBlogList" :key="blog.id">
      <router-link :to="`/blog/${blog.id}`">
        <el-image class="cover-img" :src="blog.thumbnail" fit="cover"/>
      </router-link>
      <div class="card-content">
        <div class="title">
          <router-link :to="`/blog/${blog.id}`">{{ blog.title }}</router-link>
        </div>
        <div class="card-info">
          <el-icon>
            <Calendar/>
          </el-icon>
          <span>{{ blog.createTime }}</span>
          <el-icon>
            <View/>
          </el-icon>
          <span>{{ blog.views }}</span>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script lang="ts" setup>
import {getRecommendBlogList} from "@/api/view/blog";
import {onMounted, ref} from "vue";

const recommendBlogList = ref<BlogVo[]>([])
const getBlogList = async () => {
  const res: any = await getRecommendBlogList()
  recommendBlogList.value = res.body
}

onMounted(() => {
  getBlogList()
})

</script>

<style scoped>
.card-title {
  font-size: var(--card-title-size);
  margin-bottom: 10px;
}

.card-container {
  display: flex;
  margin: 7px 0;
}

.cover-img {
  width: 60px;
  height: 60px;
  margin-right: 8px;
}

.card-content {
  padding: 7px;
}

.title {
  font-size: 14px;
  margin-bottom: 7px;
}

.title a {
  color: #262e3d;
  text-decoration: none;
  transition: color 0.2s;
}

.title a:hover {
  color: var(--theme-color);
}

.card-info {
  display: flex;
  font-size: 12px;
}

.card-info i {
  margin-right: 3px;
}

.card-info span {
  margin-right: 14px;
}
</style>
