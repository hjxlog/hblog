<template>
  <el-row style="margin-bottom: 30px;">
    <el-col :span="24">
      <el-text class="name">标签</el-text>
    </el-col>
  </el-row>
  <el-link
      @click="goToTagDetail(tag)"
      :underline="false"
      v-for="tag in tagList"
      :key="tag.id"
      :style="'margin-right:16px;'">
    <el-icon style=" margin-right: 5px">
      <FolderOpened/>
    </el-icon>
    {{ tag.name }}
  </el-link>
</template>


<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {getTagData} from "@/api/tag";
import router from "@/router";

// 标签数据
const tagList = ref<TagVo[]>([])

const getTagList = async () => {
  const res: any = await getTagData()
  tagList.value = res.body
}

const goToTagDetail = (tag: TagVo) => {
  router.push({name: 'tagDetail', params: {id: tag.id}})
}

onMounted(() => {
  getTagList()
})

</script>

<style scoped>
.name {
  font-size: 1.4em;
  margin-bottom: 40px;
}
</style>