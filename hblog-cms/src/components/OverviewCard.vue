<template>
  <el-card class="card" shadow="hover">
    <el-avatar :size="120" :src="avatarImg"/>
    <h3>HJX</h3>
    <p>to be better</p>
    <el-row justify="center">
      <el-col :span="8">
        <p>文章</p>
        <p>{{ overviewVo.blogNum }}</p>
      </el-col>
      <el-col :span="8">
        <p>分类</p>
        <p>{{ overviewVo.categoryNum }}</p>
      </el-col>
      <el-col :span="8">
        <p>标签</p>
        <p>{{ overviewVo.tagNum }}</p>
      </el-col>
    </el-row>
    <el-button type="primary" round class="follow-btn" @click="goToGithub">Follow Me</el-button>
  </el-card>
</template>

<script lang="ts" setup>
import avatarImg from "@/assets/avatar.jpg"
import {getOverview} from "@/api/view/common";
import {onMounted, ref} from "vue";

const githubUrl = "https://github.com/hjxlog";
const overviewVo = ref<OverviewVo>({})
const getOverviewVo = async () => {
  const res: any = await getOverview()
  overviewVo.value = res.body
}
const goToGithub = () => {
  window.open(githubUrl, '_blank');
}

onMounted(() => {
  getOverviewVo()
})

</script>

<style scoped>
.card {
  text-align: center;
}

.follow-btn {
  width: 80%;
}
</style>
