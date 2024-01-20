<template>
  <el-row justify="center">
    <el-col :span="18">
      <router-view :key="key"></router-view>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {computed} from "vue";
import {useRoute} from "vue-router";

const route = useRoute()
const key = computed(() => {
  return route.path
})
</script>

<style scoped>
</style>
