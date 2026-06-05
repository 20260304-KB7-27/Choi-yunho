// use relation_db

// 컬렉션 초기화
db.users.drop()
db.orders.drop()
db.products.drop()

db.users.insertMany([
    {_id: 1, username: "alice", email: "alice@example.com"},
    {_id: 2, username: "bob", email: "bob@example.com"},
    {_id: 3, username: "carol", email: "carol@example.com"}
])

db.products.insertMany([
    {_id: 101, name: "노트북", price: 1200000},
    {_id: 102, name: "마우스", price: 35000},
    {_id: 103, name: "키보드", price: 85000},
    {_id: 104, name: "모니터", price: 450000}
])


db.orders.insertMany([
    {
        _id: 1001,
        userId: 1,
        productId: 101,
        quantity: 1,
        status: "배송완료",
        orderDate: new Date("2025-01-10")
    },
    {
        _id: 1002,
        userId: 1,
        productId: 102,
        quantity: 2,
        status: "배송중",
        orderDate: new Date("2025-02-05")
    },
    {
        _id: 1003,
        userId: 2,
        productId: 103,
        quantity: 1,
        status: "주문완료",
        orderDate: new Date("2025-02-10")
    },
    {
        _id: 1004,
        userId: 3,
        productId: 104,
        quantity: 1,
        status: "배송완료",
        orderDate: new Date("2025-01-20")
    }
])

db.orders.find({userId: 1}, {_id: 1, status: 1});

// userId가 1인 사람이 주문한 내용의
// 주문 ID, 사용자 ID, 상품명, 상품가격

db.orders.aggregate([

    // 1. 필터링
    {
        $match: {
            userId: 1
        }
    },
    // 2. products 컬렉션과 조인
    {
        $lookup: {
            from: "products", // 조인할 대상의 컬렉션
            localField: "productId", // 기준(orders) 컬렉션의 필드
            foreignField: "_id", // 외래(products) 컬렉션의 필드
            as: "product" // 조인결과를 담을 필드명
        }
    },
    {
        $unwind: "$product"
    },
    {
        $project: {
            _id: 1, // 주문 ID
            userId: 1, // 사용자 ID
            productName: "$product.name", // 상품명 꺼내오기
            price: "$product.price" // 상품가격 꺼내오기
        }
    }
])

/*
배송완료가 아닌 주문들의
주문한 날자, 주문한 사람 이름, 주문 상태, 수량, 제품 ID 조회
 */

db.users.find();
db.orders.find();
db.products.find();

db.orders.aggregate([

    // 1. 필터링
    {
        $match: {
            status: {$ne: "배송완료"}
        }
    },
    // 2. products 컬렉션과 조인
    {
        $lookup: {
            from: "users", // 조인할 대상의 컬렉션
            localField: "userId", // 기준(orders) 컬렉션의 필드
            foreignField: "_id", // 외래(products) 컬렉션의 필드
            as: "product" // 조인결과를 담을 필드명
        }
    },
    {
        $unwind: "$product"
    },
    {
        $project: {
            orderDate: 1,
            productName: "$product.username", // 상품명 꺼내오기
            status: 1,
            quantity: 1,
            productId: 1,
        }
    }
])