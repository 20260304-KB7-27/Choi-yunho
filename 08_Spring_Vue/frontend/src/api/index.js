import axios from 'axios';

const instance = axios.create({
  timeout: 1000, // 요청 타임아웃
});
// 공통 응답 처리 (예외같은 공통으로 처리될 응답)
instance.interceptors.response.use(
  (response) => {
    if (response.status == 200) {
      console.log('✅ 응답 정상 : ', response.config.url);
      return response;
    }
    if (response.status == 404) {
      return Promise.reject('❌ 페이지 없음 : ' + response.config.url);
    }
    return response;
  },
  async (error) => {
    if (error.response?.status === 403) {
      console.log('접근 권한 없음 (403) - 인증은 되었으나 권한 부족');
    }

    console.log('API 요청 실패:', error.response?.status, error.message);
    return Promise.reject(error);
  },
);

// 공통 요청 처리

export default instance;