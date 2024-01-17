<template>
  <router-view :key="key"></router-view>
  <el-backtop :right="25" :bottom="50"/>
</template>

<script lang="ts" setup>
import {computed} from "vue";
import {useRoute} from "vue-router";

const route = useRoute()
const key = computed(() => {
  return route.path
})

// 解决 ElTable 自动宽度高度导致的「ResizeObserver loop limit exceeded」问题
const debounce = (fn: Function, delay: number) => {
  let timer: ReturnType<typeof setTimeout> | null = null;
  return function (this: any, ...args: any[]) {
    const context = this;
    clearTimeout(timer as ReturnType<typeof setTimeout>);
    timer = setTimeout(function () {
      fn.apply(context, args);
    }, delay);
  };
};
const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
  constructor(callback: ResizeObserverCallback) {
    const debouncedCallback = debounce(callback, 16);
    const instance = Reflect.construct(_ResizeObserver, [debouncedCallback]);
    Object.setPrototypeOf(instance, ResizeObserver.prototype);
    return instance;
  }
};

</script>

<style>
:root {
  --theme-color: #5788b6;
  --text-color: #eeeeee;
  --text-hover-color: white;
  --text-shadow: 0 3px 6px rgba(0, 0, 0, 0.3);
  --card-shadow: 0 3px 8px 6px rgba(7, 17, 27, 0.05);
  --card-title-size: 20px;
}
</style>
