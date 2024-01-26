<template>
  <el-card>
    <div class="card-title">标签</div>
    <div class="tag">
      <router-link :to="`/tag/${tag.id}`" class="tag-item" v-for="tag in tagList" :key="tag.id">
        {{ tag.name }}
      </router-link>
    </div>
  </el-card>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {getTagData} from "@/api/view/tag";
import {useRouter} from "vue-router";

const router = useRouter();
const tagList = ref<TagVo[]>([])
const getTagList = async () => {
  const res: any = await getTagData()
  tagList.value = res.body
}

onMounted(() => {
  getTagList()
})

</script>

<style scoped>
.card-title {
  font-size: var(--card-title-size);
  margin-bottom: 10px;
}

.tag {
  padding: 10px 5px;
}

.tag i {
  margin-right: 5px;
}

.tag-item {
  display: inline-block;
  align-items: center;
  text-decoration: none;
  padding-right: 12px;
  font-size: 14px;
  color: rgb(153, 153, 153);
}

.tag-item:hover {
  color: var(--theme-color);
}

</style>
