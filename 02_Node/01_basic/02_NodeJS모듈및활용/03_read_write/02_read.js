const fs = require('fs');

const path = require('path');
const filePath = path.join(__dirname, 'example.txt');

// encoding 설정
const data = fs.readFileSync(filePath, 'utf-8');
console.log(data);
