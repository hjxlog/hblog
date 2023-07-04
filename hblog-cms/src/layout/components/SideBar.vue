<template>
  <el-row class="container">
    <el-col :span="24">
      <div class="logo">HBlog CMS</div>
      <el-menu default-active="1-1" class="menu">
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <FolderOpened/>
            </el-icon>
            <span>内容管理</span>
          </template>
          <el-menu-item index="1-1" @click="this.$router.push('/content/blog')">博客管理</el-menu-item>
          <el-menu-item index="1-2" @click="this.$router.push('/content/category')">分类管理</el-menu-item>
          <el-menu-item index="1-3" @click="this.$router.push('/content/tag')">标签管理</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>

</script>

<style scoped>
.container {
  border-right: solid 1px var(--el-menu-border-color);
  height: 100vh;
}

.menu {
  border-right: 0px;
}

.logo {
  height: 60px;
  text-align: center;
  line-height: 60px;
}
</style>