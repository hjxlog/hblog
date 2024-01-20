<template>
  <div style="min-height: 100vh;">
    <PageCover>
      {{ blog.title }}
    </PageCover>
    <div class="content">
      <el-row style="width:100%">
        <el-col :span="7" :xs="0">
          <div class="sidebar">
            <div class="affixContainer">
              <el-affix target=".affixContainer" :offset-top="0">
                <Catalog v-if="isLoaded" :titlesData="previewRef"/>
                <RecommendBlogCard/>
              </el-affix>
            </div>
          </div>
        </el-col>
        <el-col :span="17" :xs="24">
          <el-card id="blogContentCard" ref="blogContentCardRef">
            <v-md-preview :text="blog.content" ref="previewRef"></v-md-preview>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>

  <Footer/>
</template>

<script lang="ts" setup>
import PageCover from "@/components/frontend/PageCover.vue"
import OverviewCard from "@/components/frontend/OverviewCard.vue"
import Catalog from "@/components/frontend/Catalog.vue"
import RecommendBlogCard from "@/components/frontend/RecommendBlogCard.vue"
import Footer from "@/components/frontend/Footer.vue"
// 博客数据
import {onMounted, ref, nextTick, onBeforeMount, reactive, watchEffect} from "vue";
import {getBlogDetail} from "@/api/view/blog";
import {useRouter} from "vue-router";

const router = useRouter();
const id: number = router.currentRoute.value.params.id;

const blog = ref<BlogServeVo>({})
const previewRef = ref(null);
const titles = ref([]);


const getBlogDetailData = async () => {
  const res: any = await getBlogDetail(id)
  blog.value = res.body
}

const isLoaded = ref(false);

onMounted(() => {
  getBlogDetailData().then(() => {
        isLoaded.value = true
      }
  )
})


</script>

<style lang="less" scoped>
.content {
  padding: 2% 2%;
  height: 100%;
  max-width: 1300px;
  margin: 0 auto;
  display: flex;
  animation: fadeInUp 1s;
}

.affixContainer {
  flex: 1;
  border-radius: 4px;
}

.catalog-card {
  position: sticky;
  top: 20px;
}

.card-title {
  font-size: var(--card-title-size);
  margin-bottom: 10px;
}

.sticky-layout {
  position: sticky;
  top: 20px;
}


.sidebar {
  display: flex;
  flex-direction: column;
  height: 100%;
  margin-right: 10px;
}

@media screen and (max-width: 900px) {
  .el-card ::v-deep .el-card__body {
    padding: 0px;
  }
}

</style>
