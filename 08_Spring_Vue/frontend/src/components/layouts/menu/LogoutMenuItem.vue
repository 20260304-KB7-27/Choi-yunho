<!-- 로그아웃 버튼 컴포넌트 -->
<template>
  <a href="#" class="nav-link" @click.prevent="logout">
    <i class="fa-solid fa-right-from-bracket"></i>
    로그아웃
  </a>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';

const auth = useAuthStore();
const router = useRouter();

const logout = (e) => {
  auth.logout();
  router.push('/');
};
</script>

<style scoped></style>
