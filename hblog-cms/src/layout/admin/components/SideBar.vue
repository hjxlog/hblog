<template>
  <el-row class="container">
    <el-col :span="24">
      <div class="logo">HBlog CMS</div>
      <el-menu class="menu">
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <FolderOpened/>
            </el-icon>
            <span>内容管理</span>
          </template>
          <el-menu-item index="1-1" @click="this.$router.push('/dashboard/content/blog')">博客管理</el-menu-item>
          <el-menu-item index="1-2" @click="this.$router.push('/dashboard/content/category')">分类管理</el-menu-item>
          <el-menu-item index="1-3" @click="this.$router.push('/dashboard/content/tag')">标签管理</el-menu-item>
        </el-sub-menu>
      </el-menu>
      <el-menu default-active="2-1" class="menu">
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <Document/>
            </el-icon>
            <span>日志管理</span>
          </template>
          <el-menu-item index="1-1" @click="this.$router.push('/dashboard/log/operate')">操作日志</el-menu-item>
          <el-menu-item index="1-2" @click="this.$router.push('/dashboard/log/visit')">访问日志</el-menu-item>
          <el-menu-item index="1-3" @click="this.$router.push('/dashboard/log/error')">异常日志</el-menu-item>
        </el-sub-menu>
      </el-menu>
      <el-menu default-active="3-1" class="menu">
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <Setting/>
            </el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item index="1-1" @click="this.$router.push('/dashboard/system/account')">账号管理</el-menu-item>
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
