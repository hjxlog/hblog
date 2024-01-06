<template>
  <PageCover>
    分类
  </PageCover>
  <div class="charts-container">
    <div class="charts" ref="chartContainer"></div>
  </div>
  <Footer/>
</template>


<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {getCategoryInfo} from "@/api/view/category";
import {useRouter} from "vue-router";
import PageCover from "@/views/frontend/layout/PageCover.vue"
import Footer from "@/views/frontend/layout/Footer.vue"
import * as echarts from 'echarts';

const router = useRouter();
const chartContainer = ref(null);
let chartsData = ref(null);
const initChart = () => {
  const chart = echarts.init(chartContainer.value);
  const options = {
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: 'middle',
      right:'10%',
      orient:'vertical'
    },
    label: {
      show: true,
      formatter(param) {
        return param.name + ' (' + param.percent * 2 + '%)';
      }
    },
    series: [
      {
        type: 'pie',
        radius: [25, 150],
        center: ['50%', '50%'],
        roseType: 'area',
        itemStyle: {
          borderRadius: 7
        },
        data: chartsData
      }
    ]
  };
  chart.setOption(options);
  chart.on('click', handleChartClick);
};

let categoryInfoList = ref<CategoryInfoVo[]>([])
const getCategoryInfoList = async () => {
  const res: any = await getCategoryInfo()
  categoryInfoList = res.body
  chartsData = categoryInfoList.map(item => ({name: item.name, value: item.blogNum}))
  initChart();
}

const handleChartClick = (params) => {
  const categoryName = params.data.name
  const matchedItem = categoryInfoList.find(item => item.name === categoryName)
  if (matchedItem) {
    const categoryId = matchedItem.id
    router.push('/category/' + categoryId);
  }
}

onMounted(() => {
  getCategoryInfoList()
})

</script>

<style scoped>
.charts-container {
  display: flex;
  /*background-color: #eeeeee;*/
}

.charts {
  position: relative;
  width: 100%;
  height: 410px;
  padding: 0px;
  margin: 0px;
  border-width: 0px;
  cursor: default;
}

.name {
  font-size: 1.4em;
  margin-bottom: 40px;
}
</style>
