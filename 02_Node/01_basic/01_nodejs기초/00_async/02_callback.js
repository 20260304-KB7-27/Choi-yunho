// callback 함수를 통해 

// 로그인 API (가정)
function login(userId, callback) {
  setTimeout(() => {
    console.log('로그인 성공');
    callback({ userId: userId });
  }, 1000);
}

// 사용자 정보 조회 API (가정)
function getUserInfo(user, callback) {
  setTimeout(() => {
    console.log('사용자 정보 조회 완료');
    callback({ userId: user.userId, name: 'kim' });
  }, 1000);
}

// 주문 조회 API (가정)
function getOrders(user, callback) {
  setTimeout(() => {
    console.log('주문 목록 조회 완료');
    callback([{ orderId: 1 }, { orderId: 2 }]);
  }, 1000);
}

// 배송 조회 API (가정)
function getDelivery(order, callback) {
  setTimeout(() => {
    console.log('배송 상태 조회 완료');
    callback({ orderId: order.orderId, status: '배송중' });
  }, 1000);
}


// 비동기 처리
// login -> userinfo -> orders -> delivery 순서대로 동작하게
// 가독성이 안 좋음, 유지 보수도 힘듦
login('userId', function (user) {
  getUserInfo(user, function (userInfo) {
    getOrders(userInfo, function (orders) {
      getDelivery(orders[0], function (delivery) {
        console.log(`최종 배송 상태 : `, delivery.status);
      });
    });
  });
});
