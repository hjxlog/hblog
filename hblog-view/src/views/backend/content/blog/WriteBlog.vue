<template>
  <el-form :model="form" class="container">
    <el-form-item label="文章标题：" prop="title">
      <el-input v-model="form.title" placeholder="请输入文章标题"/>
    </el-form-item>
    <el-form-item label="摘要内容：" prop="summary">
      <el-input v-model="form.summary" type="textarea" placeholder="请输入摘要内容" :autosize="{ minRows: 2, maxRows: 2 }"/>
    </el-form-item>
    <!--    分类、标签、按钮区域-->
    <el-row :gutter="20">
      <el-col :span="5" :xs="24">
        <el-form-item label="分类：" prop="categoryId">
          <el-select v-model="form.categoryId" clearable placeholder="请选择分类">
            <el-option v-for="item in categoryList" :key="item.id" :value="item.id" :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="是否推荐：" prop="isRecommend">
          <el-switch v-model="form.isRecommend"/>
        </el-form-item>
      </el-col>
      <el-col :span="7" :xs="24">
        <el-form-item label="标签：" prop="tagIds">
          <el-select v-model="form.tagIds" multiple clearable placeholder="请选择标签" style="width:100%">
            <el-option v-for="item in tagList" :key="item.id" :value="item.id" :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="是否发布：" prop="status">
          <el-switch active-value="1" inactive-value="0" v-model="form.status"/>
        </el-form-item>
      </el-col>
      <el-col :span="6" :xs="24">
        <div style="float:left;">
          <el-text>缩略图：</el-text>
        </div>
        <el-upload
            class="avatar-uploader"
            action="#"
            :show-file-list="false"
            :http-request="handleUploadThumbnail"
            :on-success="handleUploadThumbnailSuccess"
            :before-upload="beforeThumbnailUpload"
            name="file"
        >
          <img v-if="thumbnailUrl" :src="thumbnailUrl" class="avatar"/>
          <el-icon v-else class="avatar-uploader-icon">
            <Plus/>
          </el-icon>
        </el-upload>
      </el-col>
      <el-col :span="6" :xs="24" :offset="0">
        <el-button type="primary" :style="'float:right'" @click="saveBlog">保存文章</el-button>
        <el-button type="info" :style="'float:right;margin-right:5px;'" @click="handleCancel">取消</el-button>
      </el-col>
    </el-row>
    <el-form-item prop="mdContent" class="editor-container">
      <v-md-editor @upload-image="handleUploadImage" :disabled-menus="[]" v-model="form.mdContent" height="600px"></v-md-editor>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import {ref, reactive, onMounted, onBeforeUnmount} from "vue";
import {getCategoryList} from "@/api/admin/category";
import {getTagList} from "@/api/admin/tag";
import {uploadImage} from "@/api/admin/file";
import {addBlog, getBlogDetail, updateBlog} from "@/api/admin/blog";
import {ElMessage, UploadProps} from "element-plus";
import {useRouter} from 'vue-router';
import VueMarkdownEditor, {xss} from '@kangc/v-md-editor';

// 表单数据
const form = reactive<BlogDto>({
  id: undefined,
  title: '',
  thumbnail: '',
  summary: '',
  content: '',
  mdContent: '',
  status: '',
  isRecommend: false,
  categoryId: null,
  tagIds: []
})

// 缩略图
const thumbnailUrl = ref('')

// 加载博客数据
const router = useRouter();
const getEditBlogData = async () => {
  const id: number | undefined = router.currentRoute.value.params.id;
  if (id == undefined) return
  try {
    const res: any = await getBlogDetail(id)
    const data = res.body;
    form.id = data.id
    form.title = data.title
    form.thumbnail = data.thumbnail
    thumbnailUrl.value = data.thumbnail
    form.summary = data.summary
    form.mdContent = data.mdContent
    form.status = data.status
    form.isRecommend = data.isRecommend
    form.categoryId = data.category.id
    form.tagIds = data.tags.map((x: any) => x.id)
  } catch (error) {
  }
}

// 加载分类数据
const categoryList = ref([])
const loadCategoryList = async () => {
  try {
    const res: any = await getCategoryList({})
    categoryList.value = res.body.data
  } catch (error) {
  }
}

// 加载标签数据
const tagList = ref([])
const loadTagList = async () => {
  try {
    const res: any = await getTagList({})
    tagList.value = res.body.data
  } catch (error) {
  }
}

const handleUploadImage = async (event, insertImage, files) => {
  const res: any = await uploadImage(files[0])
  insertImage({
    url: res.body
  })
}

// 缩略图上传
const beforeThumbnailUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {
    ElMessage.error('Avatar picture must be JPG or PNG format!');
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}

const handleUploadThumbnail = async (param) => {
  const res: any = await uploadImage(param.file)
  form.thumbnail = res.body
}

const handleUploadThumbnailSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  thumbnailUrl.value = URL.createObjectURL(uploadFile.raw!)
  ElMessage.success('上传成功。')
}

// 保存文章
const saveBlog = async () => {
  try {
    form.content = xss.process(VueMarkdownEditor.vMdParser.themeConfig.markdownParser.render(form.mdContent))
    const res = form.id === undefined ? await addBlog(form) : await updateBlog(form)
    ElMessage.success(res.msg);
  } catch (error) {
  }
  await router.push('/dashboard/content/blog')
}

// 处理取消
const handleCancel = () => {
  router.push('/dashboard/content/blog')
}

onMounted(async () => {
  await getEditBlogData()
  await loadCategoryList()
  await loadTagList()
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.avatar-uploader {
  margin-left: 12px;
  margin-bottom: 10px;
  float: left;
}

.avatar-uploader .avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  text-align: center;
}

.editor-container {
  margin-top: 7px;
}
</style>
