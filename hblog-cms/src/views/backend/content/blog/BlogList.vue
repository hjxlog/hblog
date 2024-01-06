<template>
  <!--  搜索按钮区域-->
  <el-row :gutter="2">
    <el-col :span="8">
      <el-input v-model="queryForm.title" placeholder="请输入标题" clearable @clear="getTableData">
        <template #prepend>
          <el-select v-model="queryForm.categoryId" placeholder="请选择分类" clearable @clear="getTableData" style="width: 140px">
            <el-option v-for="item in categoryList" :key="item.id" :value="item.id" :label="item.name"/>
          </el-select>
        </template>
      </el-input>
    </el-col>
    <el-col :span="12">
      <el-button type="primary" @click="getTableData">搜索</el-button>
      <el-button type="primary" @click="goEditBlogPage(undefined)" style="margin-left: 2px;">新增</el-button>
    </el-col>
  </el-row>
  <!--  列表区域-->
  <el-row style="margin-top: 10px;">
    <el-col :span="24">
      <el-table :data="tableData">
        <el-table-column fixed type="index" :index="indexMethod" label="#" width="50"/>
        <el-table-column prop="title" label="标题" show-overflow-tooltip width="260"/>
        <el-table-column prop="status" label="状态" width="100">
          <template #default="scope">
            {{ scope.row.status === '1' ? '已发布' : '未发布' }}
          </template>
        </el-table-column>
        <el-table-column prop="category.name" label="分类" width="100">
          <template #default="scope">
            <el-tag>{{ scope.row.category.name }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="tags" label="标签">
          <template #default="scope">
            <el-tag v-for="tag in scope.row.tags" :key="tag.id" :style="'margin-right:4px;'" type="info">{{ tag.name }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="views" label="浏览量" width="100"/>
        <el-table-column prop="isRecommend" label="是否推荐" width="100">
          <template #default="scope">
            {{ scope.row.isRecommend ? '是' : '-' }}
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="修改时间" width="170"/>
        <el-table-column fixed="right" label="操作" width="200">
          <template v-slot="scope">
            <el-button type="primary" :icon="Edit" @click="goEditBlogPage(scope.row.id)"/>
            <el-popconfirm title="确认删除？" @confirm="handleDeleteBlog(scope.row.id)">
              <template #reference>
                <el-button type="danger" :icon="Delete"/>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
  </el-row>
  <!--  分页区域-->
  <el-row style="margin-top: 10px;">
    <el-col :span="24">
      <el-pagination
          v-model:current-page="queryForm.pageNum"
          v-model:page-size="queryForm.pageSize"
          :page-sizes="[5, 10, 50]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {getCategoryList} from "@/api/admin/category";
import {getBlogData, deleteBlog} from "@/api/admin/blog";
import {Delete, Edit} from '@element-plus/icons-vue'
import router from "@/router";
import {ElMessage} from "element-plus";

// 查询表单
const queryForm = ref<BlogDto>({
  title: '',
  status: '',
  categoryId: undefined,
  pageNum: 1,
  pageSize: 10
})

// 分类数据
const categoryList = ref<Category[]>([])
const categoryParam: CategoryDto = {
  name: "",
  status: "",
  pageNum: 1,
  pageSize: 1000
};
const loadCategoryList = async () => {
  const res: any = await getCategoryList(categoryParam);
  categoryList.value = res.body.data
}

// 表格数据
const tableData = ref<Blog[]>([]);
const total = ref(0)
const getTableData = async () => {
  const res: any = await getBlogData(queryForm.value)
  tableData.value = res.body.data
  total.value = res.body.total
}
const indexMethod = (index: number) => {
  return (queryForm.value.pageNum! - 1) * queryForm.value.pageSize! + index + 1;
}
// 处理分页操作
const handleSizeChange = (pageSize: number) => {
  queryForm.value.pageNum = 1
  queryForm.value.pageSize = pageSize
  getTableData()
}
const handleCurrentChange = (pageNum: number) => {
  queryForm.value.pageNum = pageNum
  getTableData()
}

// 初始化页面时加载列表
onMounted(() => {
  loadCategoryList()
  getTableData()
});

// 跳转写博客页面
const goEditBlogPage = (id: any) => {
  const path = id === undefined ? "/content/blog/editor" : `/content/blog/editor/${id}`
  router.push(path);
}

// 删除博客
const handleDeleteBlog = async (id: number) => {
  try {
    const res: any = await deleteBlog(id);
    await getTableData()
    ElMessage.success(res.msg)
  } catch (error) {
  }
}
</script>

<style scoped>
</style>
