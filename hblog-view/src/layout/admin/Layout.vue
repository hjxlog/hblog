<template>
  <div class="container">
    <div class="left" ref="leftContainer">
      <SideBar/>
    </div>
    <div class="right" :style="{ width: rightContainerWidth + 'px','padding-left':paddingLeftWidth + 'px'}">
      <div class="header" :style="{ width: rightContainerWidth + 'px'}">
        <HeaderBar/>
      </div>
      <div class="main">
        <Main/>
      </div>
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
let paddingLeftWidth = ref(0);

const resizeObserver = new ResizeObserver(() => {
  if (leftContainer.value) {
    rightContainerWidth.value = window.innerWidth - leftContainer.value.offsetWidth - 5;
    paddingLeftWidth.value = leftContainer.value.offsetWidth
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
}

.left {
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  background-color: white;
}

.right {
  height: 100vh;
  overflow-y: auto;
  transition: margin-left 0.3s;
}

.header {
  position: fixed;
  top: 0;
  z-index: 1000;
}

.main {
  padding: 70px 14px 0 14px;
}

</style>
