var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "13910",
        "ok": "13910",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "3002",
        "ok": "3002",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3046",
        "ok": "3046",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "3005",
        "ok": "3005",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "3",
        "ok": "3",
        "ko": "-"
    },
    "percentiles1": {
        "total": "3004",
        "ok": "3004",
        "ko": "-"
    },
    "percentiles2": {
        "total": "3005",
        "ok": "3005",
        "ko": "-"
    },
    "percentiles3": {
        "total": "3010",
        "ok": "3010",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3017",
        "ok": "3017",
        "ko": "-"
    },
    "group1": {
    "name": "t < 3020 ms",
    "htmlName": "t < 3020 ms",
    "count": 13820,
    "percentage": 99
},
    "group2": {
    "name": "3020 ms <= t < 10000 ms",
    "htmlName": "t >= 3020 ms <br> t < 10000 ms",
    "count": 90,
    "percentage": 1
},
    "group3": {
    "name": "t >= 10000 ms",
    "htmlName": "t >= 10000 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "190.548",
        "ok": "190.548",
        "ko": "-"
    }
},
contents: {
"req_webflux-from-50-5659e": {
        type: "REQUEST",
        name: "WebFlux from 50 to 350 ups",
path: "WebFlux from 50 to 350 ups",
pathFormatted: "req_webflux-from-50-5659e",
stats: {
    "name": "WebFlux from 50 to 350 ups",
    "numberOfRequests": {
        "total": "13910",
        "ok": "13910",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "3002",
        "ok": "3002",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3046",
        "ok": "3046",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "3005",
        "ok": "3005",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "3",
        "ok": "3",
        "ko": "-"
    },
    "percentiles1": {
        "total": "3004",
        "ok": "3004",
        "ko": "-"
    },
    "percentiles2": {
        "total": "3005",
        "ok": "3005",
        "ko": "-"
    },
    "percentiles3": {
        "total": "3010",
        "ok": "3010",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3017",
        "ok": "3017",
        "ko": "-"
    },
    "group1": {
    "name": "t < 3020 ms",
    "htmlName": "t < 3020 ms",
    "count": 13820,
    "percentage": 99
},
    "group2": {
    "name": "3020 ms <= t < 10000 ms",
    "htmlName": "t >= 3020 ms <br> t < 10000 ms",
    "count": 90,
    "percentage": 1
},
    "group3": {
    "name": "t >= 10000 ms",
    "htmlName": "t >= 10000 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "190.548",
        "ok": "190.548",
        "ko": "-"
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
