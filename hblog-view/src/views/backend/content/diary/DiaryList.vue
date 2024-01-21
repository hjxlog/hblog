<template>
  <!--  搜索按钮区域-->
  <el-row :gutter="2">
    <el-col :span="12">
      <el-button type="primary" @click="goEditDiaryPage(undefined)" style="margin-left: 2px;">新增</el-button>
    </el-col>
  </el-row>
  <!--  列表区域-->
  <el-row style="margin-top: 10px;">
    <el-col :span="24">
      <el-table :data="tableData">
        <el-table-column fixed type="index" :index="indexMethod" label="#" width="50"/>
        <el-table-column prop="mdContent" label="内容" show-overflow-tooltip/>
        <el-table-column prop="status" label="状态" width="100">
          <template #default="scope">
            {{ scope.row.status === '1' ? '已发布' : '未发布' }}
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="修改时间" width="170"/>
        <el-table-column label="操作" width="130">
          <template v-slot="scope">
            <el-button type="primary" :icon="Edit" @click="goEditDiaryPage(scope.row.id)"/>
            <el-popconfirm title="确认删除？" @confirm="handleDeleteDiary(scope.row.id)">
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
          :layout="paginationLayout"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {getDiaryData, deleteDiary} from "@/api/admin/diary";
import {Delete, Edit} from '@element-plus/icons-vue'
import router from "@/router";
import {ElMessage} from "element-plus";
import {menuItem} from "@/store/store";

const queryForm = ref<PageDto>({
  pageNum: 1,
  pageSize: 10
})

// 表格数据
const tableData = ref<Diary[]>([]);
const total = ref(0)
const getTableData = async () => {
  const res: any = await getDiaryData(queryForm.value)
  tableData.value = res.body.data
  total.value = res.body.total
}
const indexMethod = (index: number) => {
  return (queryForm.value.pageNum! - 1) * queryForm.value.pageSize! + index + 1;
}
// 处理分页操作
const layout = "total, prev, pager, next";
const paginationLayout = menuItem.isMobile ? layout : layout + ', sizes, jumper'
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
  getTableData()
});

// 跳转写博客页面
const goEditDiaryPage = (id: any) => {
  const path = id === undefined ? "/dashboard/content/diary/editor" : `/dashboard/content/diary/editor/${id}`
  router.push(path);
}

// 删除博客
const handleDeleteDiary = async (id: number) => {
  try {
    const res: any = await deleteDiary(id);
    await getTableData()
    ElMessage.success(res.msg)
  } catch (error) {
  }
}
</script>

<style scoped>
</style>
