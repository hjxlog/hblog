<template>
  <PageCover>
    {{ tagName }}
  </PageCover>
  <el-row justify="center">
    <el-col :span="20" :xs="24" class="list-container">
      <BlogList :queryParam="queryBlogDto"/>
    </el-col>
  </el-row>
  <Footer/>
</template>


<script lang="ts" setup>
import PageCover from "@/components/frontend/PageCover.vue"
import BlogList from "@/views/frontend/blog/BlogList.vue"
import Footer from "@/components/frontend/Footer.vue"
import {getTagDetail} from "@/api/view/tag";

import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import {getBlogData} from "@/api/view/blog";

const router = useRouter();
const tagId = ref(router.currentRoute.value.params.id)
const queryBlogDto = ref({
  tagId: tagId.value,
  pageSize: 2
});
const tagName = ref('')
const getTag = async () => {
  const res: any = await getTagDetail(tagId.value)
  tagName.value = res.body.name
}

onMounted(() => {
  getTag()
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
