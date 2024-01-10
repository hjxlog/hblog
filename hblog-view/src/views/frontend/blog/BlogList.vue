<template>
  <BlogCard v-for="(blog, index) in blogList" :key="blog.id" :blog="blog" :reverse="index % 2 == 1" class="blog-card"/>
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
import BlogCard from "@/components/BlogCard.vue"
import {onMounted, ref} from "vue";
import {getBlogData} from "@/api/view/blog";

const props = defineProps(['queryParam'])

const queryForm = ref<BlogDto>({
  categoryId: undefined,
  pageNum: 1,
  pageSize: 10
})
const blogList = ref<BlogVo[]>([])
const total = ref(0)

const handleCurrentChange = (pageNum: number) => {
  queryForm.value.pageNum = pageNum
  getBlogList()
  scrollToContent()
}

const getBlogList = async () => {
  const res: any = await getBlogData(queryForm.value)
  blogList.value = res.body.data
  total.value = res.body.total
}

const scrollToContent = () => {
  const homeCoverElement = (document.getElementById("homeCover") as HTMLElement)?.scrollHeight
  if (homeCoverElement) {
    window.scrollTo({top: homeCoverElement, behavior: "smooth"})
    return
  }
}

onMounted(() => {
  queryForm.value = {...queryForm.value, ...props.queryParam};
  getBlogList()
})

</script>

<style scoped>
.blog-card {
  margin-bottom: 20px;
  border-radius: 4px;
}
</style>
