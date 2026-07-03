// import api from 'axios';
import api from '@/api';

const BASE_URL = '/api/member';
const headers = { 'Content-Type': 'multipart/form-data' }; // 파일 업로드용 헤더

export default {
  // 중복 체크
  async checkUsername(username) {
    const { data } = await api.get(`${BASE_URL}/checkusername/${username}`);
    console.log(`🥞AUTH GET CHECKUSERNAME : `, data);
    return data;
  },

  //   회원가입 요청 API 호출
  async create(member) {
    // formdata 객체로 전달해야함.
    const formdata = new FormData();
    formdata.append('username', member.username);
    formdata.append('email', member.email);
    formdata.append('password', member.password);

    // 아바타 파일이 있을때만
    if (member.avatar) {
      formdata.append('avatar', member.avatar);
    }

    const { data } = await api.post(BASE_URL, formdata, headers);
    console.log(`🥞 AUTH POST : `, data);

    return data;
  },
};
