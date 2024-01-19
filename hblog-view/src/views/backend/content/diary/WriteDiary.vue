<template>
  <el-form :model="form" class="container">
    <!--    按钮区域-->
    <el-row :gutter="20">
      <el-col :span="7">
        <el-form-item label="是否发布：" prop="status">
          <el-switch active-value="1" inactive-value="0" v-model="form.status"/>
        </el-form-item>
      </el-col>
      <el-col :span="6" :offset="0">
        <el-button type="primary" :style="'float:right'" @click="saveDiary">保存日记</el-button>
        <el-button type="info" :style="'float:right;margin-right:5px;'" @click="handleCancel">取消</el-button>
      </el-col>
    </el-row>
    <el-form-item prop="mdContent" style="height: 100%">
      <v-md-editor @upload-image="handleUploadImage" :disabled-menus="[]" v-model="form.mdContent"></v-md-editor>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import {ref, reactive, onMounted, onBeforeUnmount} from "vue";
import {uploadImage} from "@/api/admin/file";
import {addDiary, getDiaryDetail, updateDiary} from "@/api/admin/diary";
import {ElMessage, UploadProps} from "element-plus";
import {useRouter} from 'vue-router';
import VueMarkdownEditor, {xss} from '@kangc/v-md-editor';

// 表单数据
const form = reactive<DiarySaveDto>({
  id: undefined,
  content: '',
  mdContent: '',
  status: ''
})

// 加载博客数据
const router = useRouter();
const goEditDiaryPage = async () => {
  const id: number | undefined = router.currentRoute.value.params.id;
  if (id == undefined) return
  try {
    const res: any = await getDiaryDetail(id)
    const data = res.body;
    form.id = data.id
    form.mdContent = data.mdContent
    form.status = data.status
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
const saveDiary = async () => {
  try {
    form.content = xss.process(VueMarkdownEditor.vMdParser.themeConfig.markdownParser.render(form.mdContent))
    const res = form.id === undefined ? await addDiary(form) : await updateDiary(form)
    ElMessage.success(res.msg);
  } catch (error) {
  }
  await router.push('/dashboard/content/diary')
}

// 处理取消
const handleCancel = () => {
  router.push('/dashboard/content/diary')
}

onMounted(async () => {
  await goEditDiaryPage()
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
</style>
