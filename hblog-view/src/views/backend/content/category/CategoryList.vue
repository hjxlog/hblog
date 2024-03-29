<template>
  <!--  搜索框按钮区域-->
  <el-row :gutter="2">
    <el-col :span="7" :xs="12">
      <el-input placeholder="请输入分类" v-model="queryForm.name" clearable @clear="getTableData"/>
    </el-col>
    <el-col :span="12">
      <el-button type="primary" @click="getTableData">搜索</el-button>
      <el-button type="primary" @click="showDialog" style="margin-left: 2px;">新增</el-button>
    </el-col>
  </el-row>
  <!--  列表区域-->
  <el-row style="margin-top: 10px;">
    <el-col :span="24">
      <el-table :data="tableData">
        <el-table-column fixed type="index" :index="indexMethod" label="#" width="50"/>
        <el-table-column prop="name" label="名称"/>
        <el-table-column prop="status" label="状态" width="120">
          <template #default="scope">
            {{ scope.row.status === '1' ? '已发布' : '未发布' }}
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="320"/>
        <el-table-column prop="updateTime" label="修改时间" width="320"/>
        <el-table-column label="操作" width="130">
          <template v-slot="scope">
            <el-button type="primary" :icon="Edit" @click="showDialog(scope.row)"/>
            <el-popconfirm title="确认删除？" @confirm="handleDeleteEntity(scope.row.id)">
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
  <!-- 新增/编辑分类对话框 -->
  <el-dialog :model-value="dialogVisible" :title="dialogTitle" :width="dialogWidth" @close="dialogVisible = false">
    <el-form :model="form" :rules="rules" ref="formRef" label-width="93px">
      <el-form-item label="分类名称：" prop="name">
        <el-input v-model="form.name"/>
      </el-form-item>
      <el-form-item label="是否发布：" prop="status">
        <el-switch active-value="1" inactive-value="0" v-model="form.status"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSaveEntity(formRef)">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import {reactive, ref, onMounted, computed} from "vue";
import {getCategoryList, deleteCategory, addCategory, updateCategory} from "@/api/admin/category";
import {Delete, Edit} from '@element-plus/icons-vue'
import {FormInstance, FormRules, ElMessage} from 'element-plus'
import {menuItem} from "@/store/store";

// 规则
const rules = reactive<FormRules>({
  name: [
    {required: true, message: '请输入分类名称', trigger: 'blur'},
    {max: 10, message: '长度不得超过10', trigger: 'blur'}
  ]
})

// 查询表单
const queryForm = ref<CategoryDto>({
  name: '',
  status: '',
  pageNum: 1,
  pageSize: 10
})

// 表格数据
const tableData = ref<Category[]>([]);
const total = ref(0)
const getTableData = async () => {
  const res: any = await getCategoryList(queryForm.value);
  tableData.value = res.body.data
  total.value = res.body.total
}
// 初始化页面时加载列表
onMounted(getTableData)
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

// 新增/编辑分类
const dialogWidth = menuItem.isMobile ? '90%' : '30%'
const dialogVisible = ref(false)
const form = reactive<CategoryDto>({
  name: '',
  status: ''
})
const dialogTitle = computed(() => form.id === undefined ? '新增分类' : '编辑分类');
const formRef = ref()
const showDialog = (row: CategoryDto) => {
  form.id = row?.id
  form.name = row?.name || ''
  form.status = row?.status || '1'
  dialogVisible.value = true
}
const handleSaveEntity = async (formEl: FormInstance) => {
  if (!formEl) return
  try {
    const valid = await formEl.validate();
    if (!valid) return false;
    const res: any = form.id === undefined ? await addCategory(form) : await updateCategory(form)
    await getTableData()
    ElMessage.success(res.msg)
    dialogVisible.value = false;
  } catch (error) {
  }
}

// 删除分类
const handleDeleteEntity = async (id: number) => {
  try {
    const res: any = await deleteCategory(id);
    await getTableData()
    ElMessage.success(res.msg)
  } catch (error) {
  }
}
</script>

<style scoped>
</style>
