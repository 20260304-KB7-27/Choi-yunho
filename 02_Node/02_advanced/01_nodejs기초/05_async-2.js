let orderpizza = true;

const pizza = new Promise((resolve, reject) => {
  if (orderpizza) {
    resolve('피자를 주문했습니다.');
  } else {
    reject('피자를 주문하지 않습니다.');
  }
});

pizza
  .then((result) => console.log(result))
  .catch((error) => console.log(error));
