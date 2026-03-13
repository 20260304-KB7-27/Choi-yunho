// const fs = require('fs').promises;
const fs = require('fs/promises');

// promise 방식
// fs.promises
//   .readdir('./')
//   .then((files) => console.log(files))
//   .catch((err) => console.log(err));

// async 방식
async function readDir() {
  try {
    const files = await fs.readdir('./');
    console.log(files);
  } catch (err) {
    console.error(err);
  }
}

readDir();
