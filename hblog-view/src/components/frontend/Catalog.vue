<template>
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
</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";

const props = defineProps(['titlesData'])

let currentLineIndex = ref(null);
const hasToc = ref(false);
const titles = ref([]);
const titlesData = props.titlesData

const loadToc = () => {
  const anchors = titlesData.$el.querySelectorAll('h1,h2,h3,h4,h5,h6');
  const titlesArray = Array.from(anchors).filter((title) => !!title.innerText.trim());
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
  const preview = titlesData;
  const {lineIndex} = anchor;
  const heading = preview.$el.querySelector(`[data-v-md-line="${lineIndex}"]`);
  currentLineIndex.value = anchor.lineIndex

  if (heading) {
    preview.scrollToTarget({
      target: heading,
      scrollContainer: window,
      top: 60,
    });
  }
};

onMounted(() => {
  loadToc()
})

</script>

<style lang="less" scoped>
.card-title {
  font-size: var(--card-title-size);
  margin-bottom: 10px;
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

.hide-toc {
  display: none;
}

</style>
