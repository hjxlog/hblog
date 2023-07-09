<template>
  <el-row style="margin-bottom: 30px;">
    <el-col :span="24">
      <el-text class="name">分类</el-text>
    </el-col>
  </el-row>
  <el-link
      @click="goToCategoryDetail(category)"
      :underline="false"
      v-for="category in categoryList"
      :key="category.id"
      :style="'margin-right:16px;'">
    <el-icon style=" margin-right: 5px">
      <FolderOpened/>
    </el-icon>
    {{ category.name }}
  </el-link>
</template>


<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {getCategoryData} from "@/api/category";
import router from "@/router";

// 分类数据
const categoryList = ref<CategoryVo[]>([])

const getCategoryList = async () => {
  const res: any = await getCategoryData()
  console.log(res)
  categoryList.value = res.body
}

const goToCategoryDetail = (category: CategoryVo) => {
  router.push({name: 'categoryDetail', params: {id: category.id}})
}

onMounted(() => {
  getCategoryList()
})

</script>

<style scoped>
.name {
  font-size: 1.4em;
  margin-bottom: 40px;
}
</style>