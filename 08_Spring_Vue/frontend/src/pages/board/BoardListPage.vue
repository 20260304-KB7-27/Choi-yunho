<script setup>
import api from '@/api/boardApi';
import { ref, computed, reactive, watch } from 'vue';
import moment from 'moment';
import { useRoute, useRouter } from 'vue-router';

const cr = useRoute(); // 현재 라우트 정보
const router = useRouter();

// 백엔드에서 받아온 데이터
const page = ref({});
const articles = computed(() => page.value.list || []);

const pageRequest = reactive({
  page: parseInt(cr.query.page) || 1, // URL 쿼리에서 페이지 추출
  amount: parseInt(cr.query.amount) || 5, // URL 쿼리에서 데이터 수를 추출
});

// 버튼을 클릭하면 쿼리가 바뀌게 처리
const handlePageChange = async (pageNum) => {
  // 이동할 URL 쿼리만 변경
  // 쿼리만 변경하기 때문에 변경된 내용으로 마운트 되지않음
  router.push({
    query: {
      page: pageNum,
      amount: pageRequest.amount,
    },
  });
};

// pageRequest 값이 바뀌면 호출되도록
watch(cr, async (newValue) => {
  pageRequest.page = parseInt(cr.query.page) || 1;
  pageRequest.amount = parseInt(cr.query.amount) || 5;

  // 새로운 페이지 로드
  await load(pageRequest);
});

const load = async (params) => {
  try {
    page.value = await api.getList(params);
    console.log(page.value);
  } catch {}
};

load(pageRequest);
</script>
<template>
  <div>
    <h1 class="mb-3"><i class="fa-solid fa-paste"></i> 게시글 목록</h1>
    <div class="mt-5 text-end">총 {{ page.totalCount }}건</div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th style="width: 60px">No</th>
          <th>제목</th>
          <th style="width: 100px">작성자</th>
          <th style="width: 120px">작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="article in articles" :key="article.no">
          <td>{{ article.no }}</td>
          <td>
            <router-link
              :to="{
                name: 'board/detail',
                params: { no: article.no },
                query: cr.query,
              }"
            >
              {{ article.title }}
            </router-link>
          </td>
          <td>{{ article.writer }}</td>
          <td>{{ moment(article.regDate).format('YYYY-MM-DD') }}</td>
        </tr>
      </tbody>
    </table>
    <div class="my-5 d-flex">
      <div class="flex-grow-1 text-center">
        <vue-awesome-paginate
          :total-items="page.totalCount"
          :items-per-page="pageRequest.amount"
          :max-pages-shown="5"
          :show-ending-buttons="true"
          v-model="pageRequest.page"
          @click="handlePageChange"
        >
          <!-- 커스텀 아이콘 슬롯 -->
          <template #first-page-button>
            <i class="fa-solid fa-backward-fast"></i>
          </template>
          <template #prev-button>
            <i class="fa-solid fa-caret-left"></i>
          </template>
          <template #next-button>
            <i class="fa-solid fa-caret-right"></i>
          </template>
          <template #last-page-button>
            <i class="fa-solid fa-forward-fast"></i>
          </template>
        </vue-awesome-paginate>
      </div>
      <div>
        <router-link
          :to="{ name: 'board/create', query: cr.query }"
          class="btn btn-primary"
        >
          <i class="fa-solid fa-pen-to-square"></i> 글 작성</router-link
        >
      </div>
    </div>
  </div>
</template>
