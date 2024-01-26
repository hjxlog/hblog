<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column type="expand">
      <template #default="props" style="margin-right: 100px">
        <div m="4" style="margin-left: 40px;">
          <p m="t-0 b-2">操作模块：{{ props.row.module }}</p>
          <p m="t-0 b-2">请求接口：{{ props.row.uri }}</p>
          <p m="t-0 b-2">请求参数：{{ props.row.param }}</p>
          <p m="t-0 b-2">错误信息：{{ props.row.errorMsg }}</p>
          <p m="t-0 b-2">ip：{{ props.row.ip }}</p>
          <p m="t-0 b-2">ip所属地址：{{ props.row.ipAddress }}</p>
          <p m="t-0 b-2">操作系统：{{ props.row.os }}</p>
          <p m="t-0 b-2">浏览器：{{ props.row.browser }}</p>
          <p m="t-0 b-2">用户代理：{{ props.row.userAgent }}</p>
          <p m="t-0 b-2">开始时间：{{ props.row.startTime }}</p>
          <p m="t-0 b-2">结束时间：{{ props.row.endTime }}</p>
        </div>
      </template>
    </el-table-column>
    <el-table-column type="index" :index="indexMethod" label="#" width="50"/>
    <el-table-column label="访问模块" prop="module"/>
    <el-table-column label="开始时间" prop="startTime"/>
    <el-table-column label="结束时间" prop="endTime"/>
  </el-table>
  <!--  分页区域-->
  <el-pagination
      class="pagination"
      v-model:current-page="queryForm.pageNum"
      v-model:page-size="queryForm.pageSize"
      :page-sizes="[5, 10, 50]"
      :layout="paginationLayout"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />
</template>

<script setup lang="ts">

import {onMounted, ref} from "vue";
import {getLogData} from "@/api/admin/appLog";
import {menuItem} from "@/store/store";

// 查询表单
const queryForm = ref<AppLogQueryDto>({
  logType: 'error',
  pageNum: 1,
  pageSize: 10
})

// 表格数据
const tableData = ref([])
const total = ref(0)
const getTableData = async () => {
  const res: any = await getLogData(queryForm.value);
  tableData.value = res.body.data
  total.value = res.body.total
}

// 分页
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

onMounted(() => {
  getTableData()
});

</script>

<style scoped>
.pagination {
  flex-wrap: wrap;
}
</style>
