<template>
  <el-row>
    <el-col :span="24">
      <el-menu mode="horizontal" :ellipsis="false" class="menu">
        <div class="switch-btn">
          <el-button @click="updateSwitchStatus">
            <el-icon :size="18">
              <Fold/>
            </el-icon>
          </el-button>
        </div>
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
import {menuItem} from "@/store/store"

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

const isCollapse = ref(true)
const updateSwitchStatus = () => {
  menuItem.isCollapse = !menuItem.isCollapse
}

onMounted(() => {
  getUserInfo()
});
</script>

<style scoped>
.menu {
  align-items: center;
}

.flex-grow {
  flex-grow: 1;
}

.breadcrumb {
  line-height: 60px;
}

.switch-btn {
  line-height: 60px;
}

.switch-btn button {
  border: none;
  outline: none;
}

.switch-btn button:hover,
.switch-btn button:active,
.switch-btn button:focus {
  background-color: transparent;
}
</style>
