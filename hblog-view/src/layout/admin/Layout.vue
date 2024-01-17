<template>
  <div class="container">
    <div class="left" ref="leftContainer">
      <SideBar/>
    </div>
    <div class="right" :style="{ width: rightContainerWidth + 'px' }">
      <HeaderBar/>
      <Main/>
    </div>
  </div>
</template>

<script lang="ts" setup>
import SideBar from "./components/AdminSideBar.vue"
import HeaderBar from "./components/HeaderBar.vue"
import Main from "./components/Main.vue"
import {ref, onMounted, onBeforeUnmount} from 'vue';

const leftContainer = ref(null);
const rightContainerWidth = ref(0);

const resizeObserver = new ResizeObserver(() => {
  if (leftContainer.value) {
    rightContainerWidth.value = window.innerWidth - leftContainer.value.offsetWidth - 5;
  }
});

onMounted(() => {
  if (leftContainer.value) {
    resizeObserver.observe(leftContainer.value);
  }
});

onBeforeUnmount(() => {
  resizeObserver.disconnect();
});

</script>

<style scoped>
.container {
  display: flex;
  justify-content: flex-start;
}
</style>
