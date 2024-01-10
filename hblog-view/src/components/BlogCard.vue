<template>
  <div :class="container">
    <router-link :to="`/blog/${blog.id}`" :class="thumbnailLink">
      <el-image :src="blog.thumbnail" fit="cover" class="thumbnail-img"/>
    </router-link>
    <div class="article-content">
      <router-link :to="`/blog/${blog.id}`" class="article-title">
        {{ blog.title }}
      </router-link>
      <div class="article-info">
        <div class="info-item">
          <el-icon>
            <Calendar/>
          </el-icon>
          <el-text>
            {{ blog.createTime }}
          </el-text>
        </div>
        <div class="info-item">
          <el-icon>
            <FolderOpened/>
          </el-icon>
          <el-link :underline="false" @click="router.push('/category/'+blog.category.id)">
            {{ blog.category.name }}
          </el-link>
        </div>
      </div>
      <div class="article-summary">
        <el-text>
          {{ blog.summary }}
        </el-text>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive} from "vue";
import {useRouter} from "vue-router";

const router = useRouter();
const props = defineProps(['blog', 'reverse'])
const container = reactive(["card-container"]);
const thumbnailLink = reactive(["thumbnail-link"]);
if (props.reverse) {
  container.push("card-container-reversed");
  thumbnailLink.push("thumbnail-link-reversed");
}

</script>

<style scoped>
.card-container {
  height: 250px;
  display: flex;
  align-items: center;
  box-shadow: var(--card-shadow);
}

.card-container-reversed {
  flex-direction: row-reverse;
}

.thumbnail-link {
  width: 44%;
  height: 100%;
  overflow: hidden;
  box-sizing: border-box;
  border-radius: 4px 0 0 4px;
}

.thumbnail-link-reversed {
  border-radius: 0 4px 4px 0;
}

.thumbnail-img {
  width: 100%;
  height: 100%;
  transition: all 0.5s ease;
  overflow: hidden;
}

.thumbnail-img:hover {
  transform: scale(1.1);
}

.article-content {
  width: 64%;
  padding: 10px 27px;
}

.article-title {
  color: #262e3d;
  font-size: 22px;
  text-decoration: none;
  transition: color 0.2s;
}

.article-title:hover {
  color: var(--theme-color);
}

.article-info {
  display: flex;
  margin: 12px 0 5px 0;
}

.info-item {
  display: flex;
  align-items: center;
  margin-right: 14px;
}

.info-item i {
  margin-right: 5px;
}

</style>
