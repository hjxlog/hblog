<template>
  <div id="homeCover">
    <div class="content">
      <h1 class="slogan">花有重开日</h1>
      <p class="sub-slogan">人无再少年</p>
      <el-icon size="50px" class="arrow-down" @click="scrollToContent">
        <ArrowDown/>
      </el-icon>
    </div>
  </div>
</template>

<script lang="ts" setup>
const scrollToContent = () => {
  const homeCoverElement = (document.getElementById("homeCover") as HTMLElement)?.scrollHeight;
  window.scrollTo({top: homeCoverElement, behavior: "smooth"});
}
</script>

<style scoped>
#homeCover {
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  animation: fadeUpInCover 1s;
  background-image: url('../../../assets/images/bg.png');
  background-size: cover;
}

.content {
  margin: 0 auto;
  text-align: center;
}

.slogan {
  color: white;
  font-size: 65px;
  margin: 0px;
  text-shadow: var(--text-shadow);
  animation: fadeUpIn 1.5s;
}

.sub-slogan {
  color: white;
  font-size: 24px;
  text-shadow: var(--text-shadow);
  animation: fadeUpInSlow 1.5s;
}

.arrow-down {
  position: absolute;
  bottom: 6%;
  cursor: pointer;
  color: var(--text-color);
  transition: color 0.2s;
  animation: bounce 4s infinite;
}

.arrow-down:hover {
  color: var(--text-hover-color);
  transition: color 0.2s;
}

@-webkit-keyframes bounce {
  0%,
  10%,
  25%,
  40%,
  50% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }

  20% {
    -webkit-transform: translateY(-10px);
    transform: translateY(-10px);
  }

  30% {
    -webkit-transform: translateY(-5px);
    transform: translateY(-5px);
  }
}

@keyframes bounce {
  0%,
  10%,
  25%,
  40%,
  50% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }

  20% {
    -webkit-transform: translateY(-10px);
    transform: translateY(-10px);
  }

  30% {
    -webkit-transform: translateY(-5px);
    transform: translateY(-5px);
  }
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

@keyframes fadeUpIn {
  0% {
    -webkit-transform: translateY(-50px);
    transform: translateY(-50px);
    opacity: 0%;
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    opacity: 100%;
  }
}

@keyframes fadeUpInSlow {
  0% {
    -webkit-transform: translateY(-100px);
    transform: translateY(-100px);
    opacity: 0%;
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    opacity: 100%;
  }
}
</style>
