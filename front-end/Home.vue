<template>
  <div>
    <!--    <el-header height="63px">-->
    <el-menu
      :default-active="$route.path"
      router
      mode="horizontal"
      background-color="#1F2D3D"
      text-color="white"
      active-text-color="red">
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
    <!--    </el-header>-->

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
<!--                <el-image :src="localImage" style="height: 380px; width: 900px; position: relative;padding-left: 10px">-->
<!--                </el-image>-->

        <div>
<!--          <el-tabs v-model="activeName">-->
<!--            <el-tab-pane label="Projects & Created Time" name="first" @click="drawChart">-->
<!--              <div id="chartLineBox" style="width: 90%;height: 70vh;">-->
<!--              </div>-->
<!--            </el-tab-pane>-->
<!--            <el-tab-pane label="Number of Stars" name="second">Number of Stars</el-tab-pane>-->
<!--            <el-tab-pane label="Number of Issues" name="third">Number of Issues</el-tab-pane>-->
<!--            <el-tab-pane label="Number of Forks" name="fourth">Number of Forks</el-tab-pane>-->
<!--            <el-tab-pane label="Project Topics" name="fifth">Project Topics</el-tab-pane>-->
<!--          </el-tabs>-->
          <el-button @click="drawChart">Projects & Created Time</el-button>
          &nbsp;&nbsp;&nbsp;
          <el-button @click="drawStars">Number of Stars</el-button>
          &nbsp;&nbsp;&nbsp;
          <el-button @click="drawIssues">Number of Issues</el-button>
          &nbsp;&nbsp;&nbsp;
          <el-button @click="drawForks">Number of Forks</el-button>
          &nbsp;&nbsp;&nbsp;
          <el-button @click="drawTopics">Project Topics</el-button><!--          词云-->


          <br>
          <br>
          <div id="chartLineBox" style="width: 90%;height: 70vh;">

          </div>
<!--          <div id="chartLineBox1" style="width: 90%;height: 70vh;">-->
<!--            </div>-->
        </div>

        <!--        <div style="position: relative;font-size: 25px;font-weight: bold;top: 10px">Recommend project：</div>-->
        <!--        <div style="position: relative;top: 10px;left:20px;">-->
        <!--          <el-row>-->
        <!--            <el-col :span="4" v-for="(item, index) in recommend" :key="item.id" :offset="1">-->
        <!--              <el-card shadow="hover" style="width: 230px;height: 220px;" @click.native="checkDetails(item)">-->
        <!--                <div style="padding: 10px;height: 310px;">-->

        <!--                  <div>Name: {{ item.name }}</div>-->
        <!--                  <br>-->
        <!--                  <br>-->
        <!--                  <div>star: {{ item.star }}</div>-->
        <!--                </div>-->
        <!--              </el-card>-->
        <!--            </el-col>-->
        <!--          </el-row>-->
        <!--        </div>-->
      </el-main>


    </el-container>
  </div>
</template>


<script>
import axios from "axios";
import Vue from "vue";
import * as echarts from 'echarts';

export default {
  name: 'home',
  data() {
    return {
      activeName:'first',
      starVisible: false,
      localImage: require("../assets/home.jpg"),
      searchValue: '',
      passwordVisible: false,
      p1: 'https://img-u-3.51miz.com/Templet/00/19/52/19/195219_c3e8071348f03e0b00338825d8d4992d.jpg-0.jpg',
      p2: 'http://p.moimg.net/project/project_20180502_1525258786_7271.jpg?imageMogr2/auto-orient/strip',
      navList: [
        {name: '/home', navItem: 'Home'},
        {name: '/collection', navItem: 'Collection'},
        // {name: '/personal', navItem: '个人中心'}
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
    drawForks() {
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
          data: ['<200', '200-400', '400-600', '600-800', '800-1000', '1000-2000',  '>=2000'],
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
          data: ['<10', '10-20', '20-50', '50-100', '100-200', '200-500',  '>=500'],
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
    drawStars(){
      this.chartLine = echarts.init(document.getElementById('chartLineBox'));

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
          data: ['0-1000', '1000-2000', '2000-3000','3000-4000','4000-5000','5000-6000','6000-7000','7000-8000','8000-9000','9000-10000','>=10000'],
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
      this.$router.push({path: '/result', query: {value: item.value, tag: 1}});
    },
    searchName() {
      this.$router.push({path: '/result', query: {value: this.searchValue, tag: 0}});
    }
  },
  mounted() {
    this.searchItems = this.loadAll();
  },


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
