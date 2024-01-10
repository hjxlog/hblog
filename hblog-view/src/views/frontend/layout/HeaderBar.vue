<template>
  <div class="header">
    <router-link class="logo" to="/">HJXLOG</router-link>
    <div class="menu">
      <div class="header-menu">
        <router-link v-for="route in routes" :key="route.path" :to="route.path">
          {{ route.label }}
        </router-link>
      </div>
      <div class="header-menu-drawer" @click="table = !table">
        <el-icon>
          <Operation/>
        </el-icon>
      </div>
    </div>
  </div>


  <el-drawer
      v-model="table"
      :with-header="false"
      direction="ltr"
      size="70%"
  >
    <div class="sidebar">
      <OverviewCard/>
      <RecommendBlogCard/>
      <CategoryCard/>
      <TagCard/>
    </div>
  </el-drawer>

</template>

<script lang="ts" setup>
import {ref} from "vue";
import OverviewCard from "@/components/OverviewCard.vue"
import RecommendBlogCard from "@/components/RecommendBlogCard.vue"
import CategoryCard from "@/components/CategoryCard.vue"
import TagCard from "@/components/TagCard.vue"

const table = ref(false)

const routes = [
  {path: '/', label: '首页'},
  {path: '/category', label: '分类'},
  {path: '/tag', label: '标签'},
  {path: '/archive', label: '归档'}
];
</script>

<style scoped>
.header {
  position: absolute;
  height: 60px;
  width: 100%;
  z-index: 99;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 3%;
  box-sizing: border-box;
  animation: fadeIn 1s;
}

.logo {
  color: var(--text-color);
  font-size: 24px;
  text-decoration: none;
}

.menu a {
  color: var(--text-color);
  text-decoration: none;
  font-size: 14px;
  margin-left: 25px;
  position: relative;
  padding-bottom: 5px;
  transition: color 0.5s ease-in-out;
}

.logo:hover,
.menu a:hover {
  color: var(--text-hover-color);
}

.menu a:after {
  content: "";
  width: 0;
  height: 3px;
  background: #80c8f8;
  position: absolute;
  bottom: 0;
  left: 50%;
  transition: all 0.4s ease-in-out;
  border-radius: 2px;
}

.menu a:hover:after {
  left: 0%;
  width: 100%;
}

.header-menu-drawer {
  display: none;
  color: var(--text-color);
  cursor: pointer;
  position: relative;
  font-size: 20px;
  margin: 0;
}

.sidebar div {
  margin: 10px 0px;
}

@media screen and (max-width: 900px) {
  .header-menu {
    display: none;
  }

  .header-menu-drawer {
    display: inline-block;
  }
}
</style>
