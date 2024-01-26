<template>
  <router-view :key="key"></router-view>
  <el-backtop :right="25" :bottom="50"/>
</template>

<script lang="ts" setup>
import {computed} from "vue";
import {useRoute} from "vue-router";

const route = useRoute()
const key = computed(() => {
  return route.path
})
</script>

<style>
:root {
  --theme-color: #5788b6;
  --text-color: #eeeeee;
  --text-hover-color: white;
  --text-shadow: 0 3px 6px rgba(0, 0, 0, 0.3);
  --card-shadow: 0 3px 8px 6px rgba(7, 17, 27, 0.05);
  --card-title-size: 20px;
}
</style>
