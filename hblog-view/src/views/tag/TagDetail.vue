<template>
  <el-row style="margin-bottom: 30px;">
    <el-col :span="24">
      <el-text class="name">{{ tag.name }}</el-text>
    </el-col>
  </el-row>

  <el-timeline>
    <el-timeline-item
        v-for="(blog, index) in blogList"
        :key="index"
        :timestamp="blog.createTime"
    >
      <el-link @click="router.push('/blog/'+blog.id)">{{ blog.title }}</el-link>
    </el-timeline-item>
  </el-timeline>
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
import {getBlogListByTagId} from "@/api/blog";
import {getTagDetail} from "@/api/tag";
import {useRouter} from "vue-router";

const router = useRouter();
const id: any = router.currentRoute.value.params.id;

// 分页
const queryForm = ref<Page>({
  pageNum: 1,
  pageSize: 2
})
const total = ref(0)

// 标签数据
const tag = ref<TagVo>({})
const getTagData = async () => {
  const res: any = await getTagDetail(id)
  tag.value = res.body
}

// 博客数据
const blogList = ref<BlogServeListVo[]>([])
const getBlogList = async () => {
  const res: any = await getBlogListByTagId(id)
  blogList.value = res.body.data
}

onMounted(() => {
  getTagData()
  getBlogList()
})

</script>

<style scoped>
.name {
  font-size: 1.4em;
  margin-bottom: 40px;
}
</style>