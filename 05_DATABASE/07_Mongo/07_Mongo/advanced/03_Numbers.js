/*
insertOne
insertMany
 */

for (let i = 0; i < 20000; i++) {
    db.numbers.insertOne({
        num : i,
    })
}

const docs = [];

for (let i = 0; i < 20000; i++){
    docs.push({
        num : i
    })
}
db.numbers_2.insertMany(docs);

db.numbers.find();
db.numbers_2.find();

// 컬렉션의 문서 갯수 조회
db.numbers.countDocuments();
db.numbers_2.countDocuments();