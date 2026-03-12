// 로그인 API (가정)
// function login(userId, callback) {
//   setTimeout(() => {
//     console.log('로그인 성공');
//     callback({ userId: userId });
//   }, 1000);
// }

// Promise 객체로 만들기
// Promise
// 비동기 처리 함수(resolve, reject)를 제공해준다
// 비동기 함수를 실행 시 성공, 실패의 처리가 용이하다
// Promise의 상태
// pending : 대기 상태
// fulfilled : 작업이 성공적으로 완료된 상태
// rejected : 작업이 실패한 상태
function login(userId) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      // 실패 시
      if (!userId) {
        reject('로그인 실패 : userId 없음');
        return;
      }

      // 성공 시
      console.log('로그인 성공');
      resolve({ userId: userId });
    }, 1000);
  });
}

// 사용자 정보 조회
function getUserInfo(user) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      if (user.userId !== 'user1') {
        reject('사용자 정보를 찾을 수 없음');
        return;
      }

      console.log('사용자 정보 조회 완료');
      resolve({ userId: user.userId, name: 'kim' });
    }, 1000);
  });
}

// 주문 조회
function getOrders(user) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const orders = [{ orderId: 1 }, { orderId: 2 }];

      if (orders.length === 0) {
        reject('주문 내역 없음');
        return;
      }

      console.log('주문 목록 조회 완료');
      resolve(orders);
    }, 1000);
  });
}

// 배송 조회
function getDelivery(order) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      if (!order) {
        reject('주문 정보 없음');
        return;
      }

      console.log('배송 상태 조회 완료');
      resolve({ orderId: order.orderId, status: '배송중' });
    }, 1000);
  });
}

login('user1')
  .then((user) => {
    // then을 이용해 성공 시 반환된 데이터를 받아올 수 있음.
    console.log(user);
    return getUserInfo(user);
  })
  .then((userInfo) => {
    console.log(userInfo);
    return getOrders(userInfo);
  })
  .then((orders) => {
    console.log(orders);
    return getDelivery(orders[0]);
  })
  .then((delivery) => {
    console.log('최종 배송 상태:', delivery.status);
  })
  .catch((error) => {
    console.error('에러 발생:', error);
  });
