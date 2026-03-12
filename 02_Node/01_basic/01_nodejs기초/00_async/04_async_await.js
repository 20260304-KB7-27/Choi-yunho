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

async function processOrder() {
  try {

    // await : Promise가 종료될 때까지 기다린 뒤 결과를 꺼내온다.
    // reject가 발생할 경우 error를 발생시킴 -> catch
    const user = await login('user1');
    console.log(user);

    const userInfo = await getUserInfo(user);
    console.log(userInfo);

    const orders = await getOrders(userInfo);
    console.log(orders);

    const delivery = await getDelivery(orders);
    console.log(delivery);
  } catch (error) {
    console.log(`에러 발생 : `, error);
  }
}

processOrder();
