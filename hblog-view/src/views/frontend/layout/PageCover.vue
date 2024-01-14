<template>
  <HeaderBar/>
  <div id="page-cover">
    <div class="page-info">
      <slot></slot>
    </div>
  </div>
</template>

<script lang="ts" setup>
import HeaderBar from "@/views/frontend/layout/HeaderBar.vue"
</script>

<style scoped>
#page-cover {
  display: flex;
  align-items: center;
  justify-content: center;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-image: url('../../../assets/images/bg.png');
  height: 240px;
  width: 100%;
  animation: fadeUpInCover 1s;
}

#page-cover::before {
  width: 100%;
  height: 240px;
  background-color: rgba(0, 0, 0, 0.3);
  content: "";
  position: absolute;
}

.page-info {
  position: absolute;
  text-shadow: 0 3px 6px rgba(0, 0, 0, 0.3);
  font-size: 40px;
  color: white;
}

@keyframes fadeUpInCover {
  0% {
    -webkit-transform: translateY(-70px);
    transform: translateY(-70px);
    opacity: 0%;
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    opacity: 100%;
  }
}

@media screen and (max-width: 900px) {
  .page-info {
    padding: 7px;
    font-size: 30px;
    text-align: center;
  }
}

</style>
