<!-- 메뉴 아이템 컴포넌트 -->
<template>
  <li class="nav-item">
    <router-link class="nav-link" :to="menu.url">
      <i :class="menu.icon"></i>
      {{ menu.title }}
    </router-link>
  </li>
</template>

<script setup>
const props = defineProps({
  menu: {
    type: Object,
    required: true,
  },
});
</script>

<style scoped></style>
