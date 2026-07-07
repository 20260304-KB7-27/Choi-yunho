import { useAuthStore } from '@/stores/auth';

export const isAuthenticated = (to, from) => {
  const auth = useAuthStore();

  // 로그인 상태인지 확인
  if (!auth.isLogin) {
    console.log('로그인 필요!!');

    // 로그인 페이지로 리다이렉트 -> 원래 접근하려던 페이지를 담아서 제공
    return { name: 'login', query: { next: to.name } };
  }
};
