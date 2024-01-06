<template>
  <el-breadcrumb :separator-icon="ArrowRight">
    <el-breadcrumb-item v-for="(item,index) in breadcrumb" :key="index">
      {{ item.meta.title }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script lang="ts" setup>
import {ArrowRight} from '@element-plus/icons-vue'
import {computed} from 'vue';
import {useRoute} from "vue-router";

const route = useRoute()
const breadcrumb = computed(() => {
  let matched = route.matched.filter((item) => item.meta && item.meta.title && item.children.length !== 1)
  const first = matched[0]
  if (first.path !== '/index') {
    matched = [{path: '/', meta: {title: '首页'}} as any].concat(matched)
  }
  return matched
})

</script>