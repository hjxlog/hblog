<template>
  <el-row>
    <el-col :span="24">
      <el-menu mode="horizontal" :ellipsis="false">
        <BreadCrumb class="breadcrumb"/>
        <div class="flex-grow"/>
        <el-sub-menu index="1">
          <template #title>{{ nickname }}</template>
          <el-menu-item index="1-1" @click="handleLogout">退出登陆</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import BreadCrumb from './BreadCrumb.vue'
import router from "@/router";
import {ElMessage} from "element-plus";
import {logout} from "@/api/admin/login";
import {onMounted, ref} from "vue";
import {getCurUser} from "@/utils/storage";

// 设置信息
const nickname = ref('');
const getUserInfo = () => {
  const user = getCurUser()
  if (!user) {
    handleLogout()
  } else {
    nickname.value = user.nickname;
  }
}

// 退出登录
const handleLogout = async () => {
  try {
    const res = await logout();
    localStorage.removeItem('token')
    ElMessage.success(res.msg)
    await router.push("/login");
  } catch (error) {
  }
}

onMounted(() => {
  getUserInfo()
});
</script>

<style scoped>
.flex-grow {
  flex-grow: 1;
}

.breadcrumb {
  line-height: 60px;
  padding-left: 20px;
}
</style>
