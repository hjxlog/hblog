<template>
  <div style="min-height: 100vh;">
    <PageCover>
      {{ blog.title }}
    </PageCover>
    <div class="content">
      <el-row style="width:100%">
        <el-col :span="7" :xs="0">
          <div class="sidebar">
            <div class="sidebar-item">
              <OverviewCard/>
            </div>
            <div class="affixContainer">
              <el-affix target=".affixContainer" :offset-top="0">
                <el-card :class="{ 'hide-toc': hasToc }" style="margin-bottom: 14px;">
                  <div class="card-title">目录</div>
                  <div
                      v-for="anchor in titles"
                      :style="{ 'padding-left': `${anchor.indent * 20}px` }"
                      :class="[
                    'catalog-item',
                    anchor.lineIndex === currentLineIndex ? 'active' : 'not-active',
                ]"
                      @click="handleAnchorClick(anchor)"
                  >
                    <a style="cursor: pointer">{{ anchor.title }}</a>
                  </div>
                </el-card>
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
import PageCover from "@/views/frontend/layout/PageCover.vue"
import OverviewCard from "@/components/OverviewCard.vue"
import RecommendBlogCard from "@/components/RecommendBlogCard.vue"
import Footer from "@/views/frontend/layout/Footer.vue"
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

let currentLineIndex = ref(null);

const hasToc = ref(false);
const loadToc = () => {
  const preview = previewRef.value;
  const anchors = preview.$el.querySelectorAll('h1,h2,h3,h4,h5,h6');
  const titlesArray = Array.from(anchors).filter((title) => !!title.innerText.trim());
  console.log("titlesArray", titlesArray)
  if (!titlesArray.length) {
    titles.value = [];
    hasToc.value = true;
    return;
  }
  const hTags = Array.from(new Set(titlesArray.map((title) => title.tagName))).sort();
  titles.value = titlesArray.map((el) => ({
    title: el.innerText,
    lineIndex: el.getAttribute('data-v-md-line'),
    indent: hTags.indexOf(el.tagName),
  }));
}

const handleAnchorClick = (anchor) => {
  console.log("anchor", anchor)
  const preview = previewRef.value;
  const {lineIndex} = anchor;
  const heading = preview.$el.querySelector(`[data-v-md-line="${lineIndex}"]`);
  console.log("lineIndex", lineIndex)
  currentLineIndex.value = anchor.lineIndex
  console.log("currentLineIndex", currentLineIndex.value)

  if (heading) {
    preview.scrollToTarget({
      target: heading,
      scrollContainer: window,
      top: 60,
    });
  }
};

onMounted(() => {
  getBlogDetailData().then(() => {
        loadToc()
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

.catalog-item {
  color: #666261;
  margin: 5px 0;
  line-height: 28px;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  font-size: 14px;
  padding: 2px 6px;
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;

  &:hover {
    color: var(--theme-color);
  }
}

.active {
  background-color: var(--theme-color);
  color: white;

  &:hover {
    background-color: #0c82e9;
    color: white;
  }
}

.sidebar {
  display: flex;
  flex-direction: column;
  height: 100%;
  margin-right: 10px;
}

.sidebar-item {
  margin-bottom: 14px;
}

.hide-toc {
  display: none;
}

</style>
