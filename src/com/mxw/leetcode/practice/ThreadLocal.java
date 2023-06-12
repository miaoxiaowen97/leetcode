package com.mxw.leetcode.practice;

public class ThreadLocal {
    /**
     GET /索引名/_search
     查询所有：
     {
         "query": {
            "match_all": {}
         }
     }

     精确匹配查询：
     {
         "query": {
             "match": {
                 "id": "230306167807101121043636089"
             }
         }
     }

     多条件精确查询：bool关键字+must{}数组=and
     {
         "query": {
             "bool": {
                 "must": [
                     {
                         "match": {
                             "id": "230306167807101121043636089"
                         }
                     },
                     {
                         "match": {
                            "logistics_status": "10"
                         }
                     }
                 ]
            }
         }
     }
     多条件精确查询：bool关键字+should{}数组=or
     {
         "query": {
             "bool": {
                 "should": [
                     {
                         "match": {
                             "id": "230306167807101121043636089"
                         }
                     },
                    {
                         "match": {
                            "id": "221220167504607218160801570"
                         }
                     }
                 ]
             }
         }
     }

     范围查询：bool关键字+filter{}数组+range{}+gte、lte=>= ,<=
     {
         "query": {
             "bool": {
                 "filter": [
                     {
                         "range": {
                             "real_price": {
                                 "gte": 100000,
                                 "lte": 1000000
                             }
                         }
                     }
                 ]
             }
         }
     }

     指定返回字段：
     {
         "query": {
             "match": {
                "id": "230306167807101121043636089"
             }
         },
         "_source": ["order_tags"]
     }

     分页查询：
     {
         "query": {
                "match_all": {}
         },
         "from": 0,
         "size": 2
     }

     排序：sort是数组，可以多个排序规则
     {
         "query": {
             "match_all": {}
         },
         "sort": [
             {
             "real_price": {
                "order": "asc"
             }
             }
         ],
         "from": 0,
         "size": 3
     }

     分组查询：aggs+分组名+terms关键字
     {
         "aggs": {
             "real_price": {
                 "terms": {
                     "field": "real_price"
                 }
             }
         },
         "size": 0
     }

     聚合查询：aggs+聚合名+聚合函数
     {
         "aggs": {
                 "price_avg": {
                     "avg": {
                         "field": "real_price"
                     }
                 }
             },
         "size": 0
     }
     {
         "properties": {
             "name":{
                 "type": "text",
                 "index": true
             },
             "sex":{
                 "type": "keyword",
                 "index": true
             },
             "tel":{
                 "type": "keyword",
                 "index": false
             }
         }
     }
     去重查询：
     {
         "size": 0,
         "aggs": {
             "gh_count": {
                 "cardinality": {
                    "field": "gh_id"
                 }
             }
         }
     }

     */
}
