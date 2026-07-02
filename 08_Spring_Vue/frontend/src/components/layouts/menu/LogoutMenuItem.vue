<!-- 로그아웃 버튼 컴포넌트 -->
<template>
  <a href="#" class="nav-link" @click.prevent="logout">
    <i class="fa-solid fa-right-from-bracket"></i>
    로그아웃
  </a>
</template>

<script setup>
const logout = (e) => {
  // 로그아웃 처리 필요
  router.push("/");
};
</script>

<style scoped></style>
