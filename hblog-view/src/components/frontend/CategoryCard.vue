<template>
  <el-card>
    <div class="card-title">分类</div>
    <router-link :to="`/category/${categoryInfo.id}`" class="item"
                 v-for="categoryInfo in categoryInfoList" :key="categoryInfo.id">
      <span>{{ categoryInfo.name }}</span>
      <span>{{ categoryInfo.blogNum }}</span>
    </router-link>
  </el-card>
</template>

<script lang="ts" setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getCategoryInfo} from "@/api/view/category";

const router = useRouter();
const categoryInfoList = ref<CategoryInfoVo[]>([])
const getCategoryList = async () => {
  const res: any = await getCategoryInfo()
  categoryInfoList.value = res.body
}

onMounted(() => {
  getCategoryList()
})

</script>

<style scoped>
.card-title {
  font-size: var(--card-title-size);
  margin-bottom: 10px;
}

.item {
  display: flex;
  justify-content: space-between;
  text-decoration: none;
  padding: 10px 7px;
  font-size: 14px;
  color: var(--el-text-color-primary);
  border-radius: 4px;
  transition: all 0.4s;
}

.item:hover {
  background-color: var(--theme-color);
  color: white;
  padding: 10px 17px;
}
</style>
