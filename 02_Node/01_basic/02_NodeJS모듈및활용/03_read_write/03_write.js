const fs = require('fs');
const path = require('path');

const filePath = path.join(__dirname, 'example.txt');
const savePath = path.join(__dirname, './text-1.txt');

const data = fs.readFileSync(filePath, 'utf-8');

fs.writeFileSync(savePath, data, 'utf-8');
console.log(`작성이 완료되었습니다.`);
