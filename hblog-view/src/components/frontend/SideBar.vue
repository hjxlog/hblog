<template>
  <div class="sidebar-container">
    <OverviewCard/>
    <Catalog/>
    <RecommendBlogCard/>
    <CategoryCard/>
    <TagCard/>
  </div>
</template>

<script lang="ts" setup>
import OverviewCard from "@/components/frontend/OverviewCard.vue"
import Catalog from "@/components/frontend/Catalog.vue"
import RecommendBlogCard from "@/components/frontend/RecommendBlogCard.vue"
import CategoryCard from "@/components/frontend/CategoryCard.vue"
import TagCard from "@/components/frontend/TagCard.vue"

</script>

<style scoped>
.sidebar-container div {
  margin-bottom: 20px;
}
</style>
