<template>
  <div class="sidebar-container">
    <OverviewCard/>
    <RecommendBlogCard/>
    <CategoryCard/>
    <TagCard/>
  </div>
</template>

<script lang="ts" setup>
import OverviewCard from "@/components/OverviewCard.vue"
import RecommendBlogCard from "@/components/RecommendBlogCard.vue"
import CategoryCard from "@/components/CategoryCard.vue"
import TagCard from "@/components/TagCard.vue"
</script>

<style scoped>
.sidebar-container div {
  margin-right: 12px;
  margin-bottom: 20px;
  box-shadow: var(--card-shadow);
}
</style>
