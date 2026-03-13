// const path = require('path');
import path from 'path'; // ESM 방식

/*
 path 모듈
 - 파일 경로나 디렉토리 경로를 다루는 모듈
 - OS 간의 경로를 구분하는 구분자가 다른데 -> 통일 가능
 */
const fullPath = path.join('some', 'work', 'ex.txt');

console.log(fullPath);
