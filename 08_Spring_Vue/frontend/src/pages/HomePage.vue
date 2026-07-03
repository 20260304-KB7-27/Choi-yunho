<template>
  <h1>홈페이지</h1>
  <button @click="test">테스트</button>
  <div v-if="testData">
    <h2>테스트 데이터</h2>
    <pre>{{ testData }}</pre>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import testApi from '@/api/testApi';

const testData = ref(null);

const test = async () => {
  const res = await testApi.getList();
  testData.value = res;
};
</script>

<style scoped></style>
