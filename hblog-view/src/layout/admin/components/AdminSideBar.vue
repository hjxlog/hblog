<template>
  <el-menu
      :default-active="activeMenu"
      class="el-menu-vertical"
      :collapse="isCollapse"
      @select="handleMenuSelect"
  >
    <el-menu-item style="pointer-events: none;">
      <el-icon>
        <Menu/>
      </el-icon>
      <span>HBlog CMS</span>
    </el-menu-item>
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
      <el-menu-item index="1-4" @click="this.$router.push('/dashboard/content/diary')">日记管理</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="2">
      <template #title>
        <el-icon>
          <Document/>
        </el-icon>
        <span>日志管理</span>
      </template>
      <el-menu-item index="2-1" @click="this.$router.push('/dashboard/log/operate')">操作日志</el-menu-item>
      <el-menu-item index="2-2" @click="this.$router.push('/dashboard/log/visit')">访问日志</el-menu-item>
      <el-menu-item index="2-3" @click="this.$router.push('/dashboard/log/error')">异常日志</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="3">
      <template #title>
        <el-icon>
          <Setting/>
        </el-icon>
        <span>系统管理</span>
      </template>
      <el-menu-item index="3-1" @click="this.$router.push('/dashboard/system/account')">账号管理</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script lang="ts" setup>
import {ref, watch, onMounted, computed} from 'vue'
import {menuItem} from "@/store/store"

const isCollapse = computed(() => menuItem.isCollapse);

const activeMenu = ref('');

const handleMenuSelect = (selectedMenu: string) => {
  activeMenu.value = selectedMenu;
  localStorage.setItem('selectedMenu', selectedMenu);
};

onMounted(() => {
  const storedMenu = localStorage.getItem('selectedMenu');
  if (storedMenu) {
    activeMenu.value = storedMenu;
  }
});

watch(activeMenu, (newValue) => {
  localStorage.setItem('selectedMenu', newValue);
});

</script>

<style>
.el-menu-vertical:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
