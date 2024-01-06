<template>
  <div id="archive-list">
    <PageCover>
      归档
    </PageCover>
    <div class="content">
      <el-row>
        <el-col :span="7">
          <ViewSideBar/>
        </el-col>
        <el-col :span="17">
          <div class="archive-container">
            <el-timeline class="timeline">
              <el-timeline-item center :timestamp="`归档文章 - ${total}篇`" placement="top" class="root-item">
              </el-timeline-item>
              <el-timeline-item v-for="archive in archiveList" :key="archive.year" :timestamp="archive.year + ''" class="year-item" placement="top">
                <div v-for="blog in archive.blogBriefVos" :key="blog.id" class="article-item">
                  <router-link :to="`/blog/${blog.id}`" class="article-thumbail-link">
                    <img :src="blog.thumbnail" class="article-thumbnail"/>
                  </router-link>
                  <div class="article-info">
                    <router-link :to="`/blog/${blog.id}`" class="article-title">
                      {{ blog.title }}
                    </router-link>
                    <div class="article-meta-data">
                      <span>发表于{{ blog.createTime }}</span>
                      <span>{{ blog.views }}次围观</span>
                    </div>
                  </div>
                </div>
              </el-timeline-item>
            </el-timeline>
          </div>
          <!--  分页区域-->
          <el-pagination
              v-model:current-page="queryForm.pageNum"
              v-model:page-size="queryForm.pageSize"
              layout="prev, pager, next"
              :total="total"
              @current-change="handleCurrentChange"
              style="justify-content: center"
          />
        </el-col>
      </el-row>
    </div>
    <Footer/>
  </div>
</template>

<script lang="ts" setup>
import PageCover from "@/views/frontend/layout/PageCover.vue"
import Footer from "@/views/frontend/layout/Footer.vue"
import ViewSideBar from "@/layout/view/ViewSideBar.vue"

import {getArchiveList} from "@/api/view/blog";
import {onMounted, ref} from "vue";

const archiveList = ref<ArchiveVo[]>([])
const total = ref(0)
const queryForm = ref<PageDto>({
  pageNum: 1,
  pageSize: 15
})
// 处理分页操作
const handleCurrentChange = (pageNum: number) => {
  queryForm.value.pageNum = pageNum
  getArchiveData()
}
const getArchiveData = async () => {
  const res: any = await getArchiveList(queryForm.value)
  archiveList.value = res.body.data
  total.value = res.body.total
  console.log("archiveList", res.body.data)
}

onMounted(() => {
  getArchiveData()
})

</script>

<style lang="less" scoped>
.content {
  padding: 2% 6%;
  animation: fadeInUp 1s;
}

.archive-container {
  margin-top: 25px;
}

:deep(.timeline) {
  .root-item {
    // 根节点
    .el-timeline-item__node {
      border: 5px solid var(--theme-color);
      background: transparent;
      width: 20px;
      height: 20px;
      left: -5px;
      top: -7px;
    }

    .el-timeline-item__tail {
      top: calc(50% - 15px);
      height: calc(50% + 15px);
      border-left: 2px solid #4ba9fc;
    }

    .el-timeline-item__wrapper {
      transform: translateY(-11px);

      .el-timeline-item__timestamp {
        font-size: 24px;
        color: #4c4948;
      }
    }
  }

  .year-item {
    .el-timeline-item__node {
      border: 3px solid #ff7242;
      background: white;
      width: 12px;
      height: 12px;
      left: -1px;
      top: 0;
    }

    .el-timeline-item__wrapper {
      transform: translateY(-6px);

      .el-timeline-item__timestamp {
        font-size: 20px;
        color: #4c4948;
      }
    }

    .el-timeline-item__tail {
      display: inline;
      border-left: 2px solid #9eccf5;
    }
  }

  .year-item:nth-last-child(2) {
    padding-bottom: 0;
  }

}

.article-item {
  display: flex;
  justify-content: center;
  align-content: center;
  padding: 10px 0;

  .article-thumbail-link {
    height: 80px;
    width: 80px;
    overflow: hidden;
    border-radius: 6px;

    .article-thumbnail {
      width: 100%;
      height: 100%;
      object-fit: cover;
      transition: all 0.4s ease;
    }
  }

  .article-thumbnail:hover {
    transform: scale(1.1);
  }

  .article-info {
    flex: 1;
    padding-left: 16px;
    word-break: break-all;
    display: inline-block;
    align-self: center;

    .article-title {
      color: #262e3d;
      font-size: 15px;
      text-decoration: none;
      transition: color 0.4s;
      overflow: hidden;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      line-height: 1.5;
      -webkit-line-clamp: 2;

      &:hover {
        color: var(--theme-color);
      }
    }

    .article-meta-data {
      font-size: 13px;
      color: rgb(133, 133, 133);
      box-sizing: border-box;
      line-height: 30px;
      overflow: hidden;
      -webkit-line-clamp: 1;
      display: -webkit-box;
      -webkit-box-orient: vertical;

      .article-meta-data-icon {
        margin-right: 5px;
      }

      span {
        margin-right: 20px;
      }
    }
  }
}

@media screen and (max-width: 900px) {
  .archive-body {
    width: 100%;
  }
}
</style>
