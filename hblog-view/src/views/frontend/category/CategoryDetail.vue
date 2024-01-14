<template>
  <PageCover>
    {{ categoryName }}
  </PageCover>
  <el-row justify="center">
    <el-col :span="20" :xs="24" class="list-container">
      <BlogList :queryParam="queryBlogDto"/>
    </el-col>
  </el-row>
  <Footer/>
</template>


<script lang="ts" setup>
import PageCover from "@/views/frontend/layout/PageCover.vue"
import BlogList from "@/views/frontend/blog/BlogList.vue"
import Footer from "@/views/frontend/layout/Footer.vue"
import {getCategoryDetail} from "@/api/view/category";

import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import {getBlogData} from "@/api/view/blog";

const router = useRouter();
const categoryId = ref(router.currentRoute.value.params.id)
const queryBlogDto = ref({
  categoryId: categoryId.value,
  pageSize: 2
});
const categoryName = ref('')
const getCategory = async () => {
  const res: any = await getCategoryDetail(categoryId.value)
  categoryName.value = res.body.name
}


onMounted(() => {
  getCategory()
})


</script>

<style scoped>
.list-container {
  margin-top: 20px;
}

@media screen and (max-width: 900px) {
  .list-container {
    padding: 7px;
  }
}

</style>
