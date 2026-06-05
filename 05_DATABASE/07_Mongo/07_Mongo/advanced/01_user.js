// use tutorial_v2;
//
// // 기존 컬렉션 초기화
// db.members.drop();
//
// /*
// * members DB - member 컬렉션 데이터 세팅
// *
// * 필드 구성
// *   name   : 이름
// *   age    : 나이
// *   office : 근무지
// *   phone  : 전화번호
// * */
// db.members.insertMany([
//     { name: "Baby",    age: 1,  office: "busan",   phone: "010-0001-0001" },
//     { name: "Grace",   age: 5,  office: "seoul",   phone: "010-1111-1111" },
//     { name: "George",  age: 8,  office: "busan",   phone: "010-2222-2222" },
//     { name: "Alice",   age: 12, office: "daejeon", phone: "010-3333-3333" },
//     { name: "Bob",     age: 18, office: "seoul",   phone: "010-4444-4444" },
//     { name: "Charlie", age: 22, office: "busan",   phone: "010-5555-5555" },
//     { name: "Diana",   age: 28, office: "seoul",   phone: "010-6666-6666" },
//     { name: "Eve",     age: 30, office: "daejeon", phone: "010-7777-7777" },
//     { name: "Frank",   age: 38, office: "busan",   phone: "010-8888-8888" },
//     { name: "Gloria",  age: 45, office: "seoul",   phone: "010-9999-9999" },
//     { name: "Henry",   age: 55, office: "busan",   phone: "010-1010-1010" },
//     { name: "Irene",   age: 62, office: "seoul",   phone: "010-1212-1212" },
// ]);

/*
insertOne()
- 하나의 문서를 컬렉션에 추가
- db.컬렉션명.insertOne({키 : 밸류, ...})

insertMany()
- 여러 개의 문서
- db.컬렉션명.insertMany([{키 : 밸류, ...}, {키 : 밸류, ...}, ...])
 */

db.users.insertOne({username: "smith"});
db.users.insertOne({username: "jones"});
db.users.insertOne({food: "cake"});

db.users.find();

/*
updateOne() // updateMany
- 조건에 맞는 첫 번재 문서를 수정
- db.컬렉션명.updateOne({조건}, {$set : { 수정할 필드 }})
 */
db.users.updateOne({username: "smith"},
    {
        $set: {
            favorites: {
                cities: ['Chicago', 'Seoul'],
                movies: ['Casablanca', 'For a few Dollars More', 'The String']
            }
        }
    }
)

db.users.updateOne({username: "jones"},
    {
        $set: {
            favorites: {
                movies: ['Casablanca', 'rocky']
            }
        }
    }
)

/*
find() / findOne()
- 컬렉션에서 문서 조회
- db.컬렉션명.find({조건} , {프로젝션})
 */

// 카사블랑카 영화를 좋아하는 사람들
db.users.find({"favorites.movies": "Casablanca"});


// 맨 처음 조건에 맞게 찾은 1개의 문서만 반환
db.users.findOne({"favorites.movies": "Casablanca"});

// 원하는 필드만 조회
db.users.findOne({"favorites.movies": "Casablanca"}, {username: 1});

db.users.updateMany({"favorites.movies": "Casablanca"},

    {
        // addToSet : 중복 방지용 문법, 이미 있으면 무시하고 없으면 업데이트
        $addToSet: {"favorites.movies": "rocky"}
    }
)

/*
replaceOne()
- 조건에 맞는 문서를 새 문서로 교체
- 기존 문서의 필드가 모두 삭제되고, 새 필드로 대체됨
 */
db.users.replaceOne({username: "smith"},
    {
        country: "Canada"
    }
)

db.users.updateOne({country: "Canada"},
    {
        $set: {username: "smith"}
    }
)

db.users.find();

db.users.updateOne({username: "smith"},
    {
        // unset : 해당 필드를 제거
        $unset: {country: ""}
    }
)

/*
deleteOne() / deleteMany()
- 조건에 맞는 문서 삭제
 */

db.users.deleteOne({username: "smith"})
db.users.find();

// 모든 문서 삭제
db.users.deleteMany({});

/*
drop()
- 컬렉션을 삭제
 */

db.users.drop();