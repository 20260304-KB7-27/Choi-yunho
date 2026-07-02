<!-- 계정 메뉴 아이템 컴포넌트 -->
<script setup>
const props = defineProps({ username: String });
</script>

<template>
  <li class="nav-item">
    <router-link class="nav-link" to="/auth/profile">
      <i class="fa-solid fa-user"></i>
      <img :src="displayImageUrl" class="avatar avatar-sm" />
      {{ username }}
    </router-link>
  </li>
</template>
