<template>

  <PageCover>
    标签
  </PageCover>
  <div class="tag-container">
    <el-link
        @click="router.push('/tag/'+tag.id)"
        :underline="false"
        v-for="tag in tagList"
        :key="tag.id"
        class="tag-item"
        :style="getRandomStyle()">
      {{ tag.name }}
    </el-link>
  </div>
  <Footer/>
</template>


<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {getTagData} from "@/api/view/tag";
import {useRouter} from "vue-router";
import PageCover from "@/views/frontend/layout/PageCover.vue"
import Footer from "@/views/frontend/layout/Footer.vue"

const router = useRouter();

const tagList = ref<TagVo[]>([])
const getTagList = async () => {
  const res: any = await getTagData()
  tagList.value = res.body
}

const getRandomStyle = () => {
  const randomColor = getRandomColor();
  const randomSize = getRandomSize();
  return {
    color: randomColor,
    fontSize: randomSize,
  };
};

const getRandomSize = () => {
  const minSize = 15;
  const maxSize = 35;
  return Math.floor(Math.random() * (maxSize - minSize + 1) + minSize) + 'px';
}

const getRandomColor = () => {
  const letters = '0123456789ABCDEF';
  let color = '#';
  for (let i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
}

onMounted(() => {
  getTagList()
})

</script>

<style scoped>
.tag-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.tag-item {
  margin: 10px;
  padding: 10px;
  border-radius: 50%;
  text-align: center;
}
</style>
