<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1 class="my-5">
      <i class="fa-solid fa-right-to-bracket"></i>
      로그인
    </h1>

    <form @submit.prevent="login">
      <!-- 사용자 ID 입력 -->
      <div class="mb-3 mt-3">
        <label for="username" class="form-label">
          <i class="fa-solid fa-user"></i> 사용자 ID:
        </label>
        <input
          type="text"
          class="form-control"
          placeholder="사용자 ID"
          v-model="member.username"
        />
      </div>

      <!-- 비밀번호 입력 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i> 비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="비밀번호"
          v-model="member.password"
        />
      </div>

      <!-- 에러 메시지 표시 -->
      <div v-if="error" class="text-danger">{{ error }}</div>

      <!-- 로그인 버튼 -->
      <button
        type="submit"
        class="btn btn-primary mt-4"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-right-to-bracket"></i> 로그인
      </button>
    </form>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { computed } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter, useRoute } from 'vue-router';

// 로그인 페이지 로그인 후 이동할 경로 가져오기 위해(쿼리)
const cr = useRoute();
const auth = useAuthStore();
const router = useRouter();

// 폼에 입력되는 데이터
const member = reactive({
  username: '',
  password: '',
});

const error = ref('');
const disableSubmit = computed(() => !(member.username && member.password));

const login = async () => {
  try {
    await auth.login(member);
    // router.push('/'); // 홈페이지로 이동하게
    if (cr.query.next) {
      // 로그인 후 이동할 페이지가 있으면
      router.push({ name: cr.query.next });
    } else {
      // 일반 로그인이면
      router.push('/'); // 홈페이지로 이동하게
    }
  } catch (e) {
    console.log('에러==== ', e);
    error.value = e.response.data; // 에러 메시지 표시
  }
};
</script>

<style scoped></style>
