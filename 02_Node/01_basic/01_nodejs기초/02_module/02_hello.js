// commonJS 방식
const c = require('ansi-colors');

// node_modules
// 프로젝트에서 사용하는 모든 npm 패키지가 저장되는 디렉토리

// package.json
// 프로젝트의 메타데이터 (정보)를 가지고 있는 파일
// npm init 명령어로 생성 가능
function hello(name) {
  console.log(c.green(name) + ' 님 안녕하세요');
}

hello('홍길동');
