<!-- 메뉴 그룹 컴포넌트 -->
<template>
  <ul class="navbar-nav">
    <MenuItem v-for="menu in menus" :key="menu.id" :menu="menu" />
  </ul>
</template>

<script setup>
import MenuItem from './MenuItem.vue';
const props = defineProps({
  menus: { type: Array, required: true },
});
</script>

<style scoped></style>
