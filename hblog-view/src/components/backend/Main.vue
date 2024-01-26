<template>
  <router-view :key="key"></router-view>
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
