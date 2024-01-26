<template>
  <el-card class="card">
    <router-link to="/diary">
      <el-avatar :size="120" :src="avatarImg"/>
    </router-link>
    <h3>HJX</h3>
    <p>to be better</p>
    <el-row>
      <el-col v-for="item in overviewList" :span="span">
        <p>{{ item.name }}</p>
        <p>
          <router-link :to="'/'+item.key">{{ item.num }}</router-link>
        </p>
      </el-col>
    </el-row>
    <el-button type="primary" round class="follow-btn" @click="goToGithub">Follow Me</el-button>
  </el-card>
</template>

<script lang="ts" setup>
import avatarImg from "@/assets/avatar.jpg"
import {getOverview} from "@/api/view/common";
import {onMounted, ref} from "vue";

const overviewList = ref([])
const span = ref(0)

const getOverviewData = async () => {
  const res: any = await getOverview()
  overviewList.value = res.body
  span.value = 24 / overviewList.value.length
}

const goToGithub = () => {
  window.open("https://github.com/hjxlog", '_blank');
}
onMounted(() => {
  getOverviewData()
})

</script>

<style scoped>
.card {
  text-align: center;
}

a {
  color: #262e3d;
  text-decoration: none;
}

.follow-btn {
  width: 80%;
}
</style>
