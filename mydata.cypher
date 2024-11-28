CREATE (a:Person {name: 'Alice', age: 30}),
        (b:Person {name: 'Bob', age: 25}),
        (c:Person {name: 'Charlie', age: 35});
            
CREATE (a:Person {name: 'Alice'})-[:FRIENDS_WITH]->(b:Person {name: 'Bob'}),
        (b)-[:FRIENDS_WITH]->(c:Person {name: 'Charlie'});

MATCH (a)-[r]->(b)
RETURN a, r, b;

MATCH (p:Person)
WHERE p.age <= 30
RETURN p;

MATCH (n)
DETACH DELETE n;


MATCH (n:Person)
CALL example.mySimpleModel(n) YIELD value
RETURN n.name AS name, value AS randomDouble;

EXPLAIN
MATCH (n:Person)
CALL example.mySimpleModel(n) YIELD value AS simpleValue
CALL example.myComplexModel(n) YIELD value AS complexValue
RETURN n.name AS name, simpleValue AS randomDoubleFromSimple, complexValue AS randomDoubleFromComplex

EXPLAIN
MATCH (n:Person)
CALL example.myComplexModel(n) YIELD value AS complexValue
CALL example.mySimpleModel(n) YIELD value AS simpleValue
RETURN n.name AS name, simpleValue AS randomDoubleFromSimple, complexValue AS randomDoubleFromComplex

// SentenceTransformer 这个模型的作用是判断给定的两个文本的相似度
// 比如用户输入的查询是判断当前的这个用户是否支持特朗普，我可以检索这个用户发过的推文，判断文字内容和“support trump”高概率的

// 1. 查找支持特朗普的相关推文
MATCH (u:User)-[:POSTED]->(t:Tweet)
WHERE u.username = 'someUser'
WITH collect(t.text) as tweets
CALL twitter.find_similar_tweets('support trump', tweets, 0.6) YIELD value
RETURN value.tweet, value.similarity
ORDER BY value.similarity DESC;

// 2. 或者查找特定时间范围内的相关推文
MATCH (u:User)-[:POSTED]->(t:Tweet)
WHERE u.username = 'someUser' 
AND t.created_time > datetime('2023-01-01')
WITH collect(t.text) as tweets
CALL twitter.find_similar_tweets('support trump', tweets, 0.6) YIELD value
RETURN value.tweet, value.similarity
ORDER BY value.similarity DESC
LIMIT 10;
