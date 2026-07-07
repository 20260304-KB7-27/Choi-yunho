import { isAuthenticated } from '@/utils/guards.js';

export default [
  {
    path: '/board/list',
    name: 'board/list',
    component: () => import('../pages/board/BoardListPage.vue'),
  },
  {
    path: '/board/detail/:no',
    name: 'board/detail',
    component: () => import('../pages/board/BoardDetailPage.vue'),
  },
  {
    path: '/board/create',
    name: 'board/create',
    component: () => import('../pages/board/BoardCreatePage.vue'),
    beforeEnter: isAuthenticated, // 라우터로 이동하기 전 동작할 함수 지정
  },
  {
    path: '/board/update/:no',
    name: 'board/update',
    component: () => import('../pages/board/BoardUpdatePage.vue'),
    beforeEnter: isAuthenticated, // 라우터로 이동하기 전 동작할 함수 지정
  },
];
