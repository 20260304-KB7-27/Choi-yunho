const path = require('path');
const { parseEnv } = require('util');

//파일 확장자
const ext = path.extname(__filename);

console.log('확장자 :', ext);

const parsedPath = path.parse(__filename);

console.log('parsedPath : ', parsedPath);

console.log('파일 이름 :', parsedPath.base);
console.log(`파일 이름 (확장자 제외) : `, parsedPath.name);
