// 화살표 함수

let getTriangle = (base, height) => {
  (base * height) / 2;
  // 추가 코드 작성
};

console.log(`삼각형의 면적 : ${getTriangle(5, 2)}`);

//
const person = {
  name: '최윤호',

  // 일반 함수
  hello1: function () {
    console.log('일반 함수 : ', this);
  },
  // 화살표 함수

  hello2: () => {
    console.log('화살표 함수 : ', this);
  },
};

person.hello1();
person.hello2();
