<template>
  <div>
    <el-menu
      :default-active="$route.path"
      router
      mode="horizontal"
      background-color="#1F2D3D"
      text-color="white"
      active-text-color="red"
      style="min-width: 1200px">
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
        {{ item.navItem }}
      </el-menu-item>
      <span style="color: white; position: absolute;padding-top: 16px;right: 50%;font-size: 25px;font-weight: bold">SUSTech - Github</span>
      <el-input v-model="searchValue" placeholder="Key"
                suffix-icon="el-icon-search"
                size="medium"
                style="width: 300px;position:absolute;right: 20%;margin-top: 12px"></el-input>

      <el-button size="small" style="position:absolute;right: 14%;margin-top: 13px" @click.native="searchName">
        Search
      </el-button>

    </el-menu>
    <el-container>
      <el-aside width="150px">
        <el-menu
          :default-active="$route.path"
          router
          background-color="white"
          text-color="#222"
          active-text-color="red">
          <br>
          <br>
          <el-menu-item @click.native="searchTag(item)" v-for="(item,i) in tagList" :key="i">
            {{ item.tagItem }}
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <div>
          <el-button @click="drawChart">Projects & Created Time</el-button>
          &nbsp;&nbsp;&nbsp;
          <el-button @click="drawStars">Number of Stars</el-button>
          &nbsp;&nbsp;&nbsp;
          <el-button @click="drawIssues">Number of Issues</el-button>
          &nbsp;&nbsp;&nbsp;
          <el-button @click="drawForks">Number of Forks</el-button>
          &nbsp;&nbsp;&nbsp;
          <el-button @click="relationship">Relationship</el-button>

          <br>
          <br>
          <div id="chartLineBox" style="width: 90%;height: 70vh;">
          </div>
          <div id="mywordcloud" :style="{width: '100%', height: '300px'}"></div>
        </div>
      </el-main>

    </el-container>
  </div>
</template>


<script>
import axios from "axios";

import * as echarts from 'echarts';
import "echarts-wordcloud/dist/echarts-wordcloud";
import "echarts-wordcloud/dist/echarts-wordcloud.min";
import data from "../assets/1.json";

export default {
  name: 'home',
  data() {
    return {
      chart: null,
      worddata: [],
      activeName: 'first',
      starVisible: false,
      localImage: require("../assets/home.jpg"),
      searchValue: '',
      passwordVisible: false,
      p1: 'https://img-u-3.51miz.com/Templet/00/19/52/19/195219_c3e8071348f03e0b00338825d8d4992d.jpg-0.jpg',
      p2: 'http://p.moimg.net/project/project_20180502_1525258786_7271.jpg?imageMogr2/auto-orient/strip',
      navList: [
        {name: '/home', navItem: 'Home'}
      ],
      searchItems: [],
      state2: '',
      recommend: [],
      tagList: [
        {url: '/result', tagItem: 'Android', value: 'android'},
        {url: '/result', tagItem: 'Spring-boot', value: 'spring-boot'},
        {url: '/result', tagItem: 'Spring', value: 'spring'},
        {url: '/result', tagItem: 'Hacktoberfest', value: 'hacktoberfest'},
        {url: '/result', tagItem: 'Kotlin', value: 'kotlin'},
        {url: '/result', tagItem: 'Android-library', value: 'android-library'},
        {url: '/result', tagItem: 'Database', value: 'database'},
        {url: '/result', tagItem: 'Javascript', value: 'javascript'},
        {url: '/result', tagItem: 'Mysql', value: 'mysql'}
      ]
    };
  },
  created() {

    axios.get('http://localhost:8443/projectTop8').then(res => {
      this.recommend = res.data;
    })
  },
  methods: {
    relationship() {
      this.drawTopics();
      this.chartLine = echarts.init(document.getElementById('chartLineBox'));
      this.chartLine.clear();

      // 指定图表的配置项和数据
      var option = {
        tooltip: {              //设置tip提示
          trigger: 'axis'
        },

        legend: {               //设置区分（哪条线属于什么）
          data: ['Stars','Forks', 'Watch']
        },
        color: ['green','blue', '#FA6F53'],       //设置区分（每条线是什么颜色，和 legend 一一对应）
        xAxis: {                //设置x轴
          type: 'category',
          boundaryGap: false,     //坐标轴两边不留白
          data:[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,331,332,333,334,335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,351,352,353,354,355,356,357,358,359,360,361,362,363,364,365,366,367,368,369,370,371,372,373,374,375,376,377,378,379,380,381,382,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417,418,419,420,421,422,423,424,425,426,427,428,429,430,431,432,433,434,435,436,437,438,439,440,441,442,443,444,445,446,447,448,449,450,451,452,453,454,455,456,457,458,459,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487,488,489,490,491,492,493,494,495,496,497,498,499,500,501,502,503,504,505,506,507,508,509,510,511,512,513,514,515,516,517,518,519,520,521,522,523,524,525,526,527,528,529,530,531,532,533,534,535,536,537,538,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,554,555,556,557,558,559,560,561,562,563,564,565,566,567,568,569,570,571,572,573,574,575,576,577,578,579,580,581,582,583,584,585,586,587,588,589,590,591,592,593,594,595,596,597,598,599,600,601,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,644,645,646,647,648,649,650,651,652,653,654,655,656,657,658,659,660,661,662,663,664,665,666,667,668,669,670,671,672,673,674,675,676,677,678,679,680,681,682,683,684,685,686,687,688,689,690,691,692,693,694,695,696,697,698,699,700,701,702,703,704,705,706,707,708,709,710,711,712,713,714,715,716,717,718,719,720,721,722,723,724,725,726,727,728,729,730,731,732,733,734,735,736,737,738,739,740,741,742,743,744,745,746,747,748,749,750,751,752,753,754,755,756,757,758,759,760,761,762,763,764,765,766,767,768,769,770,771,772,773,774,775,776,777,778,779,780,781,782,783,784,785,786,787,788,789,790,791,792,793,794,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,813,814,815,816,817,818,819,820,821,822,823,824,825,826,827,828,829,830,831,832,833,834,835,836,837,838,839,840,841,842,843,844,845,846,847,848,849,850,851,852,853,854,855,856,857,858,859,860,861,862,863,864,865,866,867,868,869,870,871,872,873,874,875,876,877,878,879,880,881,882,883,884,885,886,887,888,889,890,891,892,893,894,895,896,897,898,899,900,901,902,903,904,905,906,907,908,909,910,911,912,913,914,915,916,917,918,919,920,921,922,923,924,925,926,927,928,929,930,931,932,933,934,935,936,937,938,939,940,941,942,943,944,945,946,947,948,949,950,951,952,953,954,955,956,957,958,959,960,961,962,963,964,965,966,967,968,969,970,971,972,973,974,975,976,977,978,979,980,981,982,983,984,985,986,987,988,989,990,991,992,993,994,995,996,997,998,999,1000,1001,1002,1003,1004,1005,1006,1007,1008,1009,1010,1011,1012,1013,1014,1015,1016,1017,1018,1019,1020],
          name: 'ID',           //X轴 name
          nameTextStyle: {        //坐标轴名称的文字样式
            color: 'black',
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
          axisLine: {             //坐标轴轴线相关设置。
            lineStyle: {
              color: 'black',
            }
          }
        },
        yAxis: {
          name: 'Number',
          nameTextStyle: {
            color: 'black',
            fontSize: 16,
            padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: 'black',
            }
          },
          type: 'value'
        },
        series: [
          {
            name: 'Stars',
            data: [513,516,518,521,521,522,522,523,525,526,526,527,528,529,531,531,532,532,533,534,534,534,534,535,535,537,538,539,540,540,540,542,542,544,544,544,545,545,546,546,547,548,548,548,548,549,549,549,550,550,551,552,552,554,554,555,557,557,557,558,560,561,562,563,563,565,566,567,567,568,568,570,571,571,571,571,572,573,574,574,575,575,576,577,577,579,579,580,580,581,581,582,582,582,584,584,585,585,586,587,589,590,590,591,592,592,592,592,593,593,593,596,597,597,598,598,599,599,601,602,603,603,604,604,604,606,606,606,606,607,607,614,615,616,616,618,620,621,621,621,621,622,623,623,623,625,626,626,627,628,630,630,630,631,632,634,634,635,636,637,637,638,639,639,639,639,640,640,642,643,644,645,645,645,647,648,649,649,650,650,651,652,656,656,657,658,658,658,658,660,660,661,661,664,665,666,668,670,670,671,671,672,674,674,675,676,676,676,677,677,678,679,682,684,686,687,688,689,690,692,692,692,694,694,694,696,700,708,708,709,712,713,714,715,716,717,717,720,720,721,726,727,728,730,730,731,734,734,735,736,737,738,738,739,741,743,744,744,744,746,747,747,748,749,749,751,753,753,753,754,754,756,757,758,758,760,760,763,767,767,768,768,769,771,771,777,780,781,782,784,788,789,789,790,790,790,790,792,795,795,796,797,799,800,802,804,805,806,808,812,812,812,813,814,814,815,816,818,820,821,821,822,822,823,826,828,829,833,836,837,838,839,840,842,842,843,849,850,852,854,859,861,862,864,865,868,868,872,873,874,877,882,885,885,888,891,892,892,892,893,895,897,899,899,900,902,903,903,906,907,907,912,913,915,917,925,925,927,927,929,930,936,939,944,944,944,948,949,950,950,954,955,957,959,960,961,968,968,970,971,971,972,972,976,977,979,979,980,983,987,987,989,990,992,994,1001,1003,1005,1005,1008,1008,1009,1010,1011,1017,1026,1027,1030,1045,1046,1050,1053,1054,1056,1058,1058,1059,1060,1066,1068,1068,1070,1072,1075,1077,1080,1088,1094,1094,1097,1098,1100,1100,1106,1108,1108,1109,1110,1113,1113,1116,1117,1122,1129,1132,1133,1135,1136,1137,1139,1140,1142,1145,1146,1148,1150,1150,1152,1155,1156,1164,1171,1173,1173,1173,1183,1188,1193,1202,1207,1208,1224,1226,1226,1226,1227,1227,1242,1254,1254,1258,1258,1261,1261,1263,1265,1266,1270,1280,1282,1283,1286,1286,1293,1293,1299,1304,1304,1308,1314,1315,1317,1320,1324,1335,1344,1348,1354,1359,1360,1364,1365,1376,1376,1378,1378,1379,1381,1395,1397,1397,1399,1401,1405,1409,1409,1411,1411,1415,1416,1421,1422,1426,1427,1427,1427,1429,1434,1436,1438,1439,1441,1443,1444,1445,1447,1453,1466,1478,1479,1480,1488,1490,1494,1498,1502,1504,1507,1514,1516,1519,1528,1528,1536,1548,1554,1572,1574,1576,1580,1582,1589,1590,1594,1598,1601,1608,1623,1623,1626,1633,1636,1638,1640,1643,1646,1648,1651,1651,1656,1656,1665,1669,1670,1672,1673,1673,1674,1677,1681,1684,1686,1688,1697,1702,1706,1717,1721,1724,1725,1725,1731,1736,1740,1751,1755,1757,1765,1765,1778,1778,1789,1793,1799,1806,1821,1823,1824,1829,1831,1837,1842,1843,1851,1853,1856,1862,1866,1869,1872,1887,1892,1895,1901,1905,1908,1911,1915,1919,1936,1940,1949,1958,1966,1966,1976,1990,1992,1998,2004,2005,2007,2028,2039,2040,2047,2065,2067,2068,2075,2079,2084,2095,2099,2108,2117,2120,2123,2127,2137,2143,2147,2162,2166,2174,2179,2185,2185,2191,2192,2200,2201,2213,2220,2221,2226,2227,2229,2240,2243,2265,2272,2272,2280,2283,2295,2298,2300,2322,2326,2338,2352,2354,2359,2398,2440,2441,2445,2447,2450,2450,2451,2455,2460,2467,2485,2486,2486,2507,2508,2510,2527,2528,2545,2561,2563,2574,2577,2580,2585,2590,2600,2619,2620,2621,2629,2631,2633,2638,2645,2647,2656,2662,2678,2684,2704,2710,2712,2725,2728,2737,2756,2759,2789,2798,2799,2812,2814,2820,2837,2844,2848,2873,2885,2890,2922,2927,2939,2975,2984,2986,2991,3000,3007,3041,3041,3071,3076,3081,3096,3103,3107,3117,3130,3134,3146,3169,3171,3192,3193,3205,3223,3225,3227,3228,3268,3273,3279,3328,3336,3366,3381,3384,3386,3386,3411,3487,3489,3497,3512,3517,3534,3548,3549,3556,3578,3579,3601,3634,3644,3647,3678,3680,3717,3767,3798,3799,3813,3822,3843,3843,3852,3892,3931,3937,3957,3960,3962,3973,3982,4015,4024,4040,4052,4139,4195,4196,4235,4239,4277,4300,4331,4367,4386,4392,4421,4463,4464,4545,4571,4575,4584,4723,4747,4763,4774,4780,4780,4784,4811,4845,4868,4897,4909,4916,4935,4946,4952,5005,5064,5166,5170,5180,5188,5192,5213,5248,5284,5321,5324,5400,5461,5462,5490,5504,5507,5611,5623,5635,5645,5664,5707,5741,5778,5854,5868,5882,5887,5951,5953,5953,5993,6040,6050,6121,6123,6218,6349,6430,6534,6789,6980,7028,7057,7238,7254,7266,7279,7382,7448,7533,7544,7825,8047,8178,8207,8269,8316,8327,8443,8564,8564,8788,8817,9058,9425,9439,9637,9885,9931,9940,10438,10458,10528,10650,10867,11161,11168,11291,11801,12464,12875,12970,13072,13257,13468,13750,14059,14640,14816,15151,15675,16675,17020,17180,18868,18903,18970,19068,19311,22331,23375,25376,25619,29535,30133,46024,58228,59593,74799],
            type: 'line',
            lineStyle: {
              normal: {
                color: 'green',
              }
            },
          },
          {
            name: 'Forks',
            data: [99,112,89,213,484,105,65,212,126,311,69,204,926,256,80,189,177,192,155,79,621,369,62,178,121,47,245,112,317,235,507,178,207,289,558,152,75,101,134,256,533,202,70,285,247,186,366,145,203,83,310,208,142,108,187,45,143,85,75,81,336,170,65,231,239,163,74,179,47,70,53,187,142,218,95,457,212,44,63,56,75,288,204,84,95,107,117,107,102,62,266,344,136,536,284,185,92,300,384,427,110,635,562,1178,347,60,116,73,326,76,300,276,310,314,2147,139,171,32,242,104,102,139,233,224,311,412,300,120,158,590,339,64,252,137,203,158,84,1254,165,293,264,16,152,255,649,99,356,230,274,193,89,266,296,63,157,293,318,238,144,183,143,306,254,219,394,1122,124,203,459,97,192,112,586,297,51,64,154,460,61,187,203,78,130,317,129,120,187,137,194,493,197,68,161,260,67,415,289,205,153,75,338,513,269,106,171,41,128,331,325,304,499,343,242,451,162,73,253,423,282,158,112,199,298,187,113,138,279,376,141,270,109,135,141,362,121,96,377,139,183,206,122,162,171,287,140,421,233,113,204,260,335,120,563,206,123,209,596,252,224,275,188,190,233,241,218,200,61,514,310,349,156,100,180,141,205,1323,477,112,88,338,66,126,117,156,204,96,155,347,219,146,758,153,104,104,211,162,808,506,93,266,377,197,229,573,208,100,317,84,73,145,336,383,227,418,151,141,208,103,500,285,87,149,376,133,110,425,79,392,316,131,268,141,110,413,186,829,121,446,237,406,143,92,56,288,823,417,147,247,223,210,451,368,337,145,127,552,563,119,236,107,243,384,127,266,613,115,255,266,519,427,227,365,450,149,147,540,500,114,323,135,946,247,329,373,353,154,1003,239,289,170,187,270,392,133,223,284,230,360,152,121,259,215,275,107,502,320,683,533,367,191,105,1051,102,285,149,668,157,633,474,138,395,151,225,104,689,233,225,375,435,150,158,1858,113,505,73,349,263,224,594,370,259,513,326,200,295,89,33,158,319,3058,702,194,354,395,398,498,117,64,166,678,153,389,121,516,521,248,740,509,403,253,550,293,98,326,207,289,527,405,354,72,240,608,154,55,362,188,379,183,260,913,170,138,2515,538,389,164,311,185,117,208,272,200,75,537,448,501,201,188,870,135,968,1010,237,1089,154,516,428,266,586,112,147,292,367,283,339,192,368,117,366,620,287,2413,429,559,473,506,252,491,238,167,961,654,130,1438,330,593,188,253,166,242,553,273,223,691,1966,314,990,2601,559,94,280,270,307,274,240,980,240,660,1087,571,431,224,180,547,242,431,412,503,858,219,902,407,1174,349,697,566,359,373,246,728,157,128,261,517,568,286,270,322,1419,295,1034,233,299,225,348,3207,486,296,496,402,337,682,287,959,393,689,913,162,802,167,266,891,739,144,588,324,484,317,593,315,733,931,92,677,96,297,744,649,215,249,838,1193,285,413,259,426,1111,749,213,185,573,351,274,217,688,264,681,422,134,283,319,143,529,429,229,394,228,820,442,463,313,576,628,616,757,556,1096,959,821,235,304,293,1337,267,293,425,632,1010,335,277,470,254,696,518,744,391,80,1340,1106,703,621,567,669,365,684,324,372,494,322,508,708,264,465,1142,374,566,416,640,2963,571,1496,328,445,232,962,876,735,366,393,510,485,416,874,1167,108,250,1154,1943,757,972,134,116,394,1391,439,1656,1300,513,396,405,729,326,432,406,313,542,285,1858,739,580,846,1197,436,444,575,347,806,451,370,400,736,473,461,436,1150,2343,811,1865,787,722,515,1160,815,255,480,1385,386,511,888,552,1814,799,628,1395,1324,1149,466,522,379,639,211,768,1062,884,911,626,980,962,1677,258,244,246,2213,2230,809,637,435,1058,1512,388,1682,1046,471,939,476,1185,841,1043,1309,660,1788,774,494,714,796,587,603,657,677,2689,646,317,2052,312,1430,274,670,704,459,1274,925,1118,1532,280,545,2188,564,332,534,1291,1292,876,741,473,768,886,422,1204,1223,988,1679,282,942,1246,725,1464,966,1468,2274,4037,1475,4513,1796,1093,2236,567,762,3000,827,2362,739,579,836,1060,1451,1502,857,908,2243,557,1624,859,765,1077,627,1364,3108,809,1245,901,858,1374,1179,596,1535,716,1689,1488,3707,911,1755,2345,1308,853,1011,3486,1519,2770,778,443,2621,2259,1554,1314,1657,1556,1045,1415,203,677,1178,735,4140,1362,2327,1717,1527,4094,2391,1275,5015,2104,870,8270,1462,1468,1475,3115,615,760,1401,1792,2964,1675,3401,1512,1211,1204,1954,1566,3983,6724,1632,1676,3757,3744,2018,1408,4063,1812,1513,3671,6624,2367,4226,2889,2055,2427,1783,1233,4927,2269,1010,3508,2106,4621,2994,2710,2042,3605,3353,3847,5163,1361,11599,7418,10734,4636,6861,5656,6937,6930,7730,9056,9216,3801,15054,24704,21691,23291],
            type: 'line',
            lineStyle: {
              normal: {
                color: 'blue',
              }
            },
          },
          {
            name: 'Watch',
            data:[34,32,14,37,53,14,23,60,54,93,15,64,79,39,21,53,22,23,38,54,44,73,23,45,20,29,63,10,56,103,91,14,28,85,88,30,20,38,58,53,49,33,13,101,27,49,78,21,20,48,23,52,51,43,102,18,39,23,14,24,136,34,17,29,45,53,19,33,18,45,26,46,29,47,35,45,43,14,28,17,18,29,19,28,19,12,47,34,41,26,72,35,42,105,21,39,25,68,101,84,10,79,69,77,31,40,27,16,34,15,80,46,37,32,54,24,50,7,30,34,24,37,69,43,52,63,48,37,18,49,50,36,58,40,20,44,21,64,58,96,49,15,47,71,63,20,61,55,79,28,31,47,48,43,49,40,142,101,30,37,55,103,44,38,41,119,60,68,77,38,45,21,96,73,43,15,50,80,28,20,54,34,21,59,16,64,21,51,29,97,74,43,29,31,43,91,66,40,117,28,61,92,39,56,71,27,37,43,63,53,159,71,86,49,34,23,60,52,60,39,16,58,57,11,25,33,52,117,94,42,71,45,38,239,67,39,63,39,35,21,13,44,42,86,31,51,67,23,63,62,30,67,150,18,39,70,110,48,33,60,31,90,74,70,56,32,38,94,51,59,28,12,61,33,23,88,110,39,31,75,26,43,26,40,50,15,31,108,75,30,115,47,37,46,65,43,59,125,32,60,25,44,58,67,92,40,48,41,23,58,165,72,13,102,36,51,57,72,97,88,48,25,51,29,54,76,26,72,60,24,46,57,60,58,30,74,35,91,67,87,62,20,35,59,139,74,20,46,35,56,30,98,45,55,44,75,63,21,46,34,123,60,28,22,48,60,12,65,132,91,56,154,51,36,74,97,36,66,206,38,83,38,97,193,20,46,134,39,67,40,88,53,87,89,50,67,34,94,62,27,36,37,53,44,85,78,161,45,27,75,81,144,47,67,30,133,50,84,57,23,83,50,104,65,184,51,77,33,98,53,79,87,20,40,46,62,74,29,152,70,25,119,113,78,137,40,32,28,53,138,83,66,101,71,93,94,28,33,34,28,29,53,37,116,38,64,126,47,73,76,127,45,60,119,34,43,101,90,66,27,73,94,69,25,51,57,86,34,19,141,51,96,101,198,104,28,105,172,46,83,62,48,30,62,216,206,103,43,132,30,130,110,80,117,52,119,67,37,95,27,35,54,186,61,68,30,119,18,44,105,144,196,67,108,121,48,25,148,56,68,175,94,68,282,105,76,50,45,95,74,74,71,154,178,161,132,138,133,91,57,122,86,136,56,57,153,103,106,140,47,100,49,64,115,83,48,169,119,100,38,113,48,99,34,103,120,466,77,40,133,96,41,59,81,76,95,91,105,194,36,173,38,77,128,118,214,95,77,194,112,104,88,126,127,78,233,161,108,124,54,41,189,96,51,131,68,120,42,68,48,144,181,33,72,34,129,107,133,56,117,201,191,97,214,78,21,158,84,173,74,121,94,58,78,156,60,187,85,53,31,96,43,208,71,61,88,19,143,24,86,88,121,104,89,157,125,162,239,25,138,95,88,199,58,66,124,121,96,54,57,105,90,214,118,119,83,16,105,74,100,119,68,135,44,216,87,65,80,49,96,181,86,61,94,162,233,61,108,148,131,221,147,114,57,103,195,181,78,68,46,84,109,146,208,68,101,125,217,106,270,77,31,114,218,54,353,246,106,83,80,254,59,70,43,43,154,30,97,108,59,154,144,204,123,146,90,59,136,155,60,89,181,116,97,168,292,221,201,97,201,138,164,131,77,224,117,121,74,130,107,517,51,154,172,175,335,186,144,87,81,82,134,204,164,177,159,68,298,164,84,69,90,218,187,148,133,150,76,136,70,102,267,100,136,104,178,229,138,202,167,272,110,171,146,163,195,86,77,115,226,162,49,310,76,156,68,166,348,193,180,202,217,69,102,165,227,174,158,181,160,139,219,270,150,104,236,129,326,267,104,268,105,205,263,176,190,144,385,323,328,135,272,207,106,291,294,140,411,214,376,325,278,217,195,108,258,202,227,126,178,303,156,101,156,163,205,318,137,140,343,163,267,274,99,291,131,312,215,178,172,148,375,209,214,221,257,273,311,185,104,327,220,197,203,331,165,227,251,47,170,138,356,485,302,668,331,428,600,99,183,429,319,329,219,296,393,501,462,157,165,369,386,727,420,394,271,312,271,246,240,257,645,117,488,450,614,411,204,599,250,185,699,658,369,786,678,261,667,359,337,778,428,369,296,373,869,608,222,581,1285,855,1012,1154,472,1180,871,929,888,774,858,980,1315,1394,987,1696,777,2264,2181,2719,3888],
            type: 'line',
            lineStyle: {
              normal: {
                color: '#FA6F53',
              }
            },
          }
        ]
      }

      // 使用刚指定的配置项和数据显示图表。

      this.chartLine.setOption(option)
    },
    drawTopics() {
      var worddata = JSON.parse(JSON.stringify(data))
      // this.$axios.get('../assets/data.json').then(res=>{
      //   console.log(res)
      //   worddata=res.data
      // }).catch(
      //   alert('fail')
      // )
      console.log(worddata);
      this.chart = echarts.init(document.getElementById("mywordcloud"));
      const option = {
        series: [
          {
            type: "wordCloud",
            //用来调整词之间的距离
            gridSize: 10,
            //用来调整字的大小范围
            // Text size range which the value in data will be mapped to.
            // Default to have minimum 12px and maximum 60px size.
            sizeRange: [14, 60],
            // Text rotation range and step in degree. Text will be rotated randomly in range [-90,                                                                             90] by rotationStep 45
            //用来调整词的旋转方向，，[0,0]--代表着没有角度，也就是词为水平方向，需要设置角度参考注释内容
            // rotationRange: [-45, 0, 45, 90],
            // rotationRange: [ 0,90],
            rotationRange: [0, 0],
            //随机生成字体颜色
            // maskImage: maskImage,
            textStyle: {
              normal: {
                color: function () {
                  return (
                    "rgb(" +
                    Math.round(Math.random() * 255) +
                    ", " +
                    Math.round(Math.random() * 255) +
                    ", " +
                    Math.round(Math.random() * 255) +
                    ")"
                  );
                }
              }
            },
            //位置相关设置
            // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
            // Default to be put in the center and has 75% x 80% size.
            left: "center",
            top: "center",
            right: null,
            bottom: null,
            width: "200%",
            height: "200%",
            //数据
            data: worddata
          }
        ]

      };
      this.chart.setOption(option);
      console.log(worddata)
    },

    drawForks() {
      this.drawTopics();
      this.chartLine = echarts.init(document.getElementById('chartLineBox'));
      this.chartLine.clear()

      // 指定图表的配置项和数据
      var option = {
        tooltip: {              //设置tip提示
          trigger: 'axis'
        },

        legend: {               //设置区分（哪条线属于什么）
          data: ['Forks', '# of Projects']
        },
        color: ['blue', '#FA6F53'],       //设置区分（每条线是什么颜色，和 legend 一一对应）
        xAxis: {                //设置x轴
          type: 'category',
          boundaryGap: false,     //坐标轴两边不留白
          data: ['<200', '200-400', '400-600', '600-800', '800-1000', '1000-2000', '>=2000'],
          name: 'Forks',           //X轴 name
          nameTextStyle: {        //坐标轴名称的文字样式
            color: 'black',
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
          axisLine: {             //坐标轴轴线相关设置。
            lineStyle: {
              color: 'black',
            }
          }
        },
        yAxis: {
          name: '# of Projects',
          nameTextStyle: {
            color: 'black',
            fontSize: 16,
            padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: 'black',
            }
          },
          type: 'value'
        },
        series: [


          {
            name: '# of Projects',
            data: [257,///0-200
              293,
              148,
              77,
              55,
              112,///1000-2000
              78],
            type: 'line',
            lineStyle: {
              normal: {
                color: 'blue',
              }
            },
          }
        ]
      }

      // 使用刚指定的配置项和数据显示图表。

      this.chartLine.setOption(option)
    },
    drawIssues() {
      this.drawTopics();
      this.chartLine = echarts.init(document.getElementById('chartLineBox'));
      this.chartLine.clear()

      // 指定图表的配置项和数据
      var option = {
        tooltip: {              //设置tip提示
          trigger: 'axis'
        },

        legend: {               //设置区分（哪条线属于什么）
          data: ['Issues', '# of Projects']
        },
        color: ['blue', '#FA6F53'],       //设置区分（每条线是什么颜色，和 legend 一一对应）
        xAxis: {                //设置x轴
          type: 'category',
          boundaryGap: false,     //坐标轴两边不留白
          data: ['<10', '10-20', '20-50', '50-100', '100-200', '200-500', '>=500'],
          name: 'Issues',           //X轴 name
          nameTextStyle: {        //坐标轴名称的文字样式
            color: 'black',
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
          axisLine: {             //坐标轴轴线相关设置。
            lineStyle: {
              color: 'black',
            }
          }
        },
        yAxis: {
          name: '# of Projects',
          nameTextStyle: {
            color: 'black',
            fontSize: 16,
            padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: 'black',
            }
          },
          type: 'value'
        },
        series: [


          {
            name: '# of Projects',
            data: [243,///<10
              137,///10-20
              216,///20-50
              154,///50-100
              131,
              91,///200-500
              48],
            type: 'line',
            lineStyle: {
              normal: {
                color: 'blue',
              }
            },
          }
        ]
      }

      // 使用刚指定的配置项和数据显示图表。

      this.chartLine.setOption(option)
    },
    drawStars() {
      this.drawTopics();
      this.chartLine = echarts.init(document.getElementById('chartLineBox'));
      this.chartLine.clear();
      // 指定图表的配置项和数据
      var option = {
        tooltip: {              //设置tip提示
          trigger: 'axis'
        },

        legend: {               //设置区分（哪条线属于什么）
          data: ['# of Projects']
        },
        color: ['blue', '#FA6F53'],       //设置区分（每条线是什么颜色，和 legend 一一对应）
        xAxis: {                //设置x轴
          type: 'category',
          boundaryGap: false,     //坐标轴两边不留白
          data: ['0-1000', '1000-2000', '2000-3000', '3000-4000', '4000-5000', '5000-6000', '6000-7000', '7000-8000', '8000-9000', '9000-10000', '>=10000'],
          name: 'Star',           //X轴 name
          nameTextStyle: {        //坐标轴名称的文字样式
            color: 'black',
            fontSize: 16,
          },
          axisLine: {             //坐标轴轴线相关设置。
            lineStyle: {
              color: 'black',
            }
          }
        },
        yAxis: {
          name: '# of Projects',
          nameTextStyle: {
            color: 'black',
            fontSize: 16,
            // padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: 'black',
            }
          },
          type: 'value'
        },
        series: [
          {
            name: '# of projects',
            data: [415,
              264,
              123,
              68,
              38,
              34,
              10,
              11,
              11,
              7,
              39],

            type: 'line',               // 类型为折线图
            lineStyle: {                // 线条样式 => 必须使用normal属性
              normal: {
                color: 'blue',
              }
            },
          },
          {
            name: 'Total number',
            data: [],
            type: 'line',
            lineStyle: {
              normal: {
                color: '#FA6F53',
              }
            },
          }
        ]
      }
      // 使用刚指定的配置项和数据显示图表。
      this.chartLine.setOption(option)
    },
    drawChart() {
      this.drawTopics();
      this.starVisible = true;
      this.chartLine = echarts.init(document.getElementById('chartLineBox'));
      this.chartLine.clear()

      // 指定图表的配置项和数据
      var option = {
        tooltip: {              //设置tip提示
          trigger: 'axis'
        },

        legend: {               //设置区分（哪条线属于什么）
          data: ['Number of new projects', 'Total number']
        },
        color: ['blue', '#FA6F53'],       //设置区分（每条线是什么颜色，和 legend 一一对应）
        xAxis: {                //设置x轴
          type: 'category',
          boundaryGap: false,     //坐标轴两边不留白
          data: ['2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020', '2021', '2022'],
          name: 'DATE',           //X轴 name
          nameTextStyle: {        //坐标轴名称的文字样式
            color: 'black',
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
          axisLine: {             //坐标轴轴线相关设置。
            lineStyle: {
              color: 'black',
            }
          }
        },
        yAxis: {
          name: 'Number',
          nameTextStyle: {
            color: 'black',
            fontSize: 16,
            padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: 'black',
            }
          },
          type: 'value'
        },
        series: [
          {
            name: 'Number of new projects',
            data: [20,
              27,
              69,
              76,
              101,
              161,
              158,
              116,
              110,
              78,
              48,
              38,
              15,
              3],

            type: 'line',               // 类型为折线图
            lineStyle: {                // 线条样式 => 必须使用normal属性
              normal: {
                color: 'blue',
              }
            },
          },
          {
            name: 'Total number',
            data: [20,
              47,
              116,
              192,
              293,
              454,
              612,
              728,
              838,
              916,
              964,
              1002,
              1017,
              1020],
            type: 'line',
            lineStyle: {
              normal: {
                color: '#FA6F53',
              }
            },
          }
        ]
      }

      // 使用刚指定的配置项和数据显示图表。

      this.chartLine.setOption(option)
    },
    checkDetails(item) {
      this.$router.push({
        name: 'Detail', query: {
          goods_name: item.goods.goods_name,
          picture: item.goods.picture,
          goods_id: item.goods.goods_id,
          price: item.goods.price,
          detail: item.goods.detail,
          restNumber: item.goods.rest_num,
          sellerID: item.goods.seller_id,
          comment: item.comments,
          imgList: item.imgs
        }
      })
    },
    exit() {
      axios.post('/logout', {
        username: ''
      }).then(function (resp) {
        alert('successful')
      })
      this.$router.push('/login');
    },
    personalHomepage() {
      this.$router.push('/personal');
    },
    querySearch(queryString, cb) {
      var searchItems = this.searchItems;
      var results = queryString ? searchItems.filter(this.createFilter(queryString)) : searchItems;
      cb(results);
    },
    createFilter(queryString) {
      return (searchItem) => {
        return (searchItem.value.toLowerCase().indexOf(queryString.toLowerCase()) > -1);
      };
    },
    loadAll() {
      return [];
    },
    searchTag(item) {
      this.$router.push({path: '/result', query: {value: item.value, sort: 0}});
    },
    searchName() {
      this.$router.push({path: '/result', query: {value: this.searchValue, sort: 0}});
    }
  },
  mounted() {
    this.searchItems = this.loadAll();
  }
}
</script>


<style scoped>
a {
  text-decoration: none;
}

span {
  pointer-events: none;
}

.i {
  float: right;
}

</style>
