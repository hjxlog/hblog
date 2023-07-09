<template>
  <el-card>
    <el-text class="title">{{ blog.title }}</el-text>
    <div class="info">
      <el-icon style=" margin-right: 5px">
        <Calendar/>
      </el-icon>
      <el-text style=" margin-right: 8px">{{ blog.createTime }}</el-text>
      <el-icon style=" margin-right: 5px">
        <FolderOpened/>
      </el-icon>
      <el-link :underline="false" @click="this.$router.push('/category/'+blog.category.id)">{{ Object.assign({}, blog.category).name }}</el-link>
    </div>
    <div class="content">
      <el-text style=" margin-right: 8px">{{ blog.content }}</el-text>
    </div>
    <el-divider style="margin: 10px 0px"/>
    <el-link :underline="false" v-for="tag in blog.tags" :key="tag.id" :style="'margin-right:8px;'">
      # {{ tag.name }}
    </el-link>
  </el-card>
</template>

<script lang="ts" setup>

// 博客数据
import {onMounted, ref} from "vue";
import {getBlogDetail} from "@/api/blog";
import {useRouter} from "vue-router";

const router = useRouter();
const id: number = router.currentRoute.value.params.id;

const blog = ref<BlogServeVo>({})

const getBlogDetailData = async () => {
  const res: any = await getBlogDetail(id)
  blog.value = res.body
}

onMounted(() => {
  getBlogDetailData()
})

</script>

<style scoped>
.title {
  display: flex;
  font-size: 1.5em;
  margin-bottom: 14px;
}

.info {
  display: flex;
  margin-bottom: 10px;
  align-items: center;
}

.content {
  margin-bottom: 17px;
}
</style>