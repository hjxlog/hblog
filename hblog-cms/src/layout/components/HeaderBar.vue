<template>
  <el-row>
    <el-col :span="24">
      <el-menu mode="horizontal" :ellipsis="false">
        <BreadCrumb class="breadcrumb"/>
        <div class="flex-grow"/>
        <el-sub-menu index="1">
          <template #title>管理员</template>
          <el-menu-item index="2-1">更改密码</el-menu-item>
          <el-menu-item index="2-2" @click="handleLogout">退出登陆</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import BreadCrumb from '../components/BreadCrumb'
import router from "@/router";
import {ElMessage} from "element-plus";
import {logout} from "@/api/login";

const handleLogout = async () => {
  try {
    const res = await logout();
    localStorage.removeItem('token')
    ElMessage.success(res.msg)
    await router.push("/login");
  } catch (error) {
  }
}

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