import api from '@/api';

const BASE_URL = '/api/board';

export default {
  // 테스트용 board 목록 가져오기
  async getList() {
    const { data } = await api.get(`${BASE_URL}/list`);
    return data;
  },
};
