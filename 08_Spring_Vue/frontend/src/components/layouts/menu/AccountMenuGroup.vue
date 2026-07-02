<!-- 계정 메뉴 그룹 컴포넌트 -->
<script setup>
import { computed } from "vue";
import MenuItem from "./MenuItem.vue";
import AccountMenuItem from "./AccountMenuItem.vue";
import LogoutMenuItem from "./LogoutMenuItem.vue";
import config from "@/config";        // 폴더를 import 하면 index.js를 가져옴

const { login, join } = config.accountMenus;

const islogin = computed(() => {
  false;
});

const username = computed(() => {
  return "null";
});
</script>

<template>
  <ul class="navbar-nav ms-auto">
    <template v-if="islogin">
      <AccountMenuItem :username="username" />
      <LogoutMenuItem />
    </template>
    <template v-else>
      <MenuItem :menu="login" />
      <MenuItem :menu="join" />
    </template>
  </ul>
</template>

<style scoped></style>
