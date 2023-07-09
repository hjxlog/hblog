<template>
  <!--  博客卡片-->
  <el-card shadow="hover" v-for="blog in blogList" :key="blog.id" class="card">
    <el-link class="title" @click="this.$router.push('/blog/'+blog.id)">{{ blog.title }}</el-link>
    <div class="info">
      <el-icon style=" margin-right: 5px">
        <Calendar/>
      </el-icon>
      <el-text style=" margin-right: 8px">{{ blog.createTime }}</el-text>
      <el-icon style=" margin-right: 5px">
        <FolderOpened/>
      </el-icon>
      <el-link :underline="false" @click="this.$router.push('/category/'+blog.category.id)">{{ blog.category.name }}</el-link>
    </div>
    <div class="summary">
      <el-text style=" margin-right: 8px">{{ blog.summary }}</el-text>
    </div>
    <el-divider style="margin: 10px 0px"/>
    <el-link :underline="false" v-for="tag in blog.tags" :key="tag.id" :style="'margin-right:8px;'">
      # {{ tag.name }}
    </el-link>
  </el-card>
  <!--  分页区域-->
  <el-pagination
      v-model:current-page="queryForm.pageNum"
      v-model:page-size="queryForm.pageSize"
      layout="prev, pager, next"
      :total="total"
      @current-change="handleCurrentChange"
      style="justify-content: center"
  />
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {getBlogData} from "@/api/blog";

// 查询表单
const queryForm = ref<Page>({
  pageNum: 1,
  pageSize: 2
})

// 博客数据
const blogList = ref<BlogVo[]>([])
const total = ref(0)
// 处理分页操作
const handleCurrentChange = (pageNum: number) => {
  queryForm.value.pageNum = pageNum
  getBlogList()
}

const getBlogList = async () => {
  const res: any = await getBlogData(queryForm.value)
  blogList.value = res.body.data
  total.value = res.body.total
}

onMounted(() => {
  getBlogList()
})

</script>

<style scoped>
.card {
  margin-bottom: 15px;
}

.title {
  font-size: 1.2em;
  margin-bottom: 14px;
}

.info {
  display: flex;
  margin-bottom: 10px;
  align-items: center;
}

.summary {
  margin-bottom: 17px;
}
</style>