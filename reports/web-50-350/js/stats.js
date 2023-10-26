var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "21059",
        "ok": "6837",
        "ko": "14222"
    },
    "minResponseTime": {
        "total": "1573",
        "ok": "3003",
        "ko": "1573"
    },
    "maxResponseTime": {
        "total": "68331",
        "ok": "59986",
        "ko": "68331"
    },
    "meanResponseTime": {
        "total": "40588",
        "ok": "26068",
        "ko": "47568"
    },
    "standardDeviation": {
        "total": "22442",
        "ok": "17800",
        "ko": "21056"
    },
    "percentiles1": {
        "total": "51078",
        "ok": "23880",
        "ko": "60013"
    },
    "percentiles2": {
        "total": "60216",
        "ok": "41411",
        "ko": "61122"
    },
    "percentiles3": {
        "total": "63179",
        "ok": "56410",
        "ko": "63710"
    },
    "percentiles4": {
        "total": "65256",
        "ok": "59350",
        "ko": "65689"
    },
    "group1": {
    "name": "t < 3020 ms",
    "htmlName": "t < 3020 ms",
    "count": 617,
    "percentage": 3
},
    "group2": {
    "name": "3020 ms <= t < 10000 ms",
    "htmlName": "t >= 3020 ms <br> t < 10000 ms",
    "count": 1212,
    "percentage": 6
},
    "group3": {
    "name": "t >= 10000 ms",
    "htmlName": "t >= 10000 ms",
    "count": 5008,
    "percentage": 24
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 14222,
    "percentage": 68
},
    "meanNumberOfRequestsPerSecond": {
        "total": "103.739",
        "ok": "33.68",
        "ko": "70.059"
    }
},
contents: {
"req_web-from-50-to--9cd34": {
        type: "REQUEST",
        name: "Web from 50 to 350 ups",
path: "Web from 50 to 350 ups",
pathFormatted: "req_web-from-50-to--9cd34",
stats: {
    "name": "Web from 50 to 350 ups",
    "numberOfRequests": {
        "total": "21059",
        "ok": "6837",
        "ko": "14222"
    },
    "minResponseTime": {
        "total": "1573",
        "ok": "3003",
        "ko": "1573"
    },
    "maxResponseTime": {
        "total": "68331",
        "ok": "59986",
        "ko": "68331"
    },
    "meanResponseTime": {
        "total": "40588",
        "ok": "26068",
        "ko": "47568"
    },
    "standardDeviation": {
        "total": "22442",
        "ok": "17800",
        "ko": "21056"
    },
    "percentiles1": {
        "total": "51122",
        "ok": "23836",
        "ko": "60013"
    },
    "percentiles2": {
        "total": "60215",
        "ok": "41429",
        "ko": "61122"
    },
    "percentiles3": {
        "total": "63181",
        "ok": "56414",
        "ko": "63710"
    },
    "percentiles4": {
        "total": "65255",
        "ok": "59350",
        "ko": "65689"
    },
    "group1": {
    "name": "t < 3020 ms",
    "htmlName": "t < 3020 ms",
    "count": 617,
    "percentage": 3
},
    "group2": {
    "name": "3020 ms <= t < 10000 ms",
    "htmlName": "t >= 3020 ms <br> t < 10000 ms",
    "count": 1212,
    "percentage": 6
},
    "group3": {
    "name": "t >= 10000 ms",
    "htmlName": "t >= 10000 ms",
    "count": 5008,
    "percentage": 24
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 14222,
    "percentage": 68
},
    "meanNumberOfRequestsPerSecond": {
        "total": "103.739",
        "ok": "33.68",
        "ko": "70.059"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
